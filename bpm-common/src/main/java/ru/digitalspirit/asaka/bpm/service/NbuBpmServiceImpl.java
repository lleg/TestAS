package ru.digitalspirit.asaka.bpm.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import ru.digitalspirit.asaka.bpm.api.client.BpmClient;
import ru.digitalspirit.asaka.bpm.model.BpmTaskInfo;
import ru.digitalspirit.asaka.bpm.model.process.ExecutionTree;
import ru.digitalspirit.asaka.bpm.model.process.ExecutionTreeNode;
import ru.digitalspirit.asaka.bpm.model.process.ProcessDetailsBody;
import ru.digitalspirit.asaka.bpm.model.service.ServiceDataBody;
import ru.digitalspirit.asaka.bpm.model.service.ServiceStatus;
import ru.digitalspirit.asaka.bpm.model.task.AssignedType;
import ru.digitalspirit.asaka.bpm.model.task.TaskDetailsBody;
import ru.digitalspirit.asaka.bpm.model.UserContextData;
import ru.digitalspirit.asaka.bpm.model.UserInfo;

import java.io.IOException;
import java.util.*;

import static ru.digitalspirit.asaka.util.StringUtil.isEmpty;

/**
 * Implementation of NbuBpmService
 */
public class NbuBpmServiceImpl extends BpmServiceImpl implements NbuBpmService {

    @Autowired
    NbuBpmSendStatusHelper sendStatusHelper;

    @Autowired
    NbuBpmTaskListHelper taskListHelper;

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssX";

    private static final String BPM_ERROR_FIELD = "errorDescription";

    private static final String NULL_TASK_ID_ERROR = "TaskId can't be null!";

    private static final Logger logger = LoggerFactory.getLogger(NbuBpmServiceImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public BpmTaskInfo startProcess(String processName, String firstTaskName, UserContextData userContextData) {
        return startProcess(processName, firstTaskName, userContextData, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BpmTaskInfo startProcess(String processName, String firstTaskName, UserContextData userContextData, Map<String, Object> additionalInputParameters) {
        if (isEmpty(processName)) {
            throw new IllegalArgumentException("ProcessName can't be null!");
        }
        if (userContextData == null) {
            throw new IllegalArgumentException("UserContextData can't be null!");
        }

        String processId = startApplication(processName, getApplicationInput(userContextData, firstTaskName, additionalInputParameters));
        String taskId = waitForTaskByName(processId, firstTaskName);
        if (taskId == null) {
            String error = getErrorFromBpmProcess(processId);
            if (error != null) {
                throw new BpmServiceException(error);
            }
            throw new BpmServiceException("TIMEOUT on waiting for task %s in %s", firstTaskName, processName);
        }

        String user = userContextData.getUserLogin();
        return assignNfoTaskToUserWithChecking(taskId, user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suspendTask(String taskId, String userName) {
        if (isEmpty(taskId)) {
            throw new IllegalArgumentException(NULL_TASK_ID_ERROR);
        }
        TaskDetailsBody task = getTask(taskId);
        if (!isAssignedToUser(task, userName)) {
            throw new BpmServiceException("Task %s is not assigned to user %s", taskId, userName);
        }
        if (isComplete(task)) {
            throw new BpmServiceException(BpmServiceException.Code.TASK_IS_ALREADY_CLOSED, "Task is already closed");
        }

        Map<String, Object> output = new HashMap<>();
        output.put("decision", "SUSPEND");

        sendStatusHelper.sendTaskSuspendedStatus(taskId, userName);

        completeTask(taskId, output);

        taskListHelper.updateTaskList(taskId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCompletedTask(String taskId) {
        if (isEmpty(taskId)) {
            throw new IllegalArgumentException(NULL_TASK_ID_ERROR);
        }
        TaskDetailsBody task = getTask(taskId);
        return isComplete(task);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void completeTask(String taskId, String decision, String userName) {
        if (isEmpty(taskId)) {
            throw new IllegalArgumentException(NULL_TASK_ID_ERROR);
        }
        TaskDetailsBody task = getTask(taskId);
        if (!isAssignedToUser(task, userName)) {
            throw new BpmServiceException("Task %s is not assigned to user %s", taskId, userName);
        }
        if (isComplete(task)) {
            throw new BpmServiceException(BpmServiceException.Code.TASK_IS_ALREADY_CLOSED, "Task is already closed");
        }

        Map<String, Object> output = new HashMap<>();
        output.put("decision", decision);

        sendStatusHelper.sendTaskFinishedStatus(taskId, userName);

        completeTask(taskId, output);

        taskListHelper.updateTaskList(taskId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BpmTaskInfo assignNfoTaskToUser(String taskId, String userName) {
        if (isEmpty(taskId)) {
            throw new IllegalArgumentException(NULL_TASK_ID_ERROR);
        }
        if (isEmpty(userName)) {
            throw new IllegalArgumentException("UserName can't be null!");
        }

        if (!isTaskAllowedToUser(taskId, userName)) {
            assignTaskToRole(taskId, "tw_allusers");
        }

        assignTaskToUser(taskId, userName);
        sendStatusHelper.sendTaskAssignedStatus(taskId, userName);
        taskListHelper.updateTaskList(taskId);
        return getTaskStartInfo(taskId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BpmTaskInfo assignNfoTaskToUserWithChecking(String taskId, String userName) {
        if (isEmpty(taskId)) {
            throw new IllegalArgumentException(NULL_TASK_ID_ERROR);
        }
        if (isEmpty(userName)) {
            throw new IllegalArgumentException("UserName can't be null!");
        }
        if (!isTaskAllowedToUser(taskId, userName)) {
            throw new BpmServiceException("Task %s is not allowed to user %s", taskId, userName);
        }

        assignTaskToUser(taskId, userName);
        sendStatusHelper.sendTaskStartedStatus(taskId, userName);
        taskListHelper.updateTaskList(taskId);
        return getTaskStartInfo(taskId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserInfo getAssignerFIO(String taskId) {
        if (isEmpty(taskId)) {
            throw new IllegalArgumentException(NULL_TASK_ID_ERROR);
        }

        TaskDetailsBody task = getTask(taskId);
        if (AssignedType.GROUP.equals(task.getAssignedToType())) {
            UserInfo user = new UserInfo();
            user.setLogin(task.getAssignedTo());
            return user;
        }
        UserInfo user = new UserInfo();
        String userLogin = task.getAssignedTo();
        user.setLogin(userLogin);
        user.setFullName(getUser(userLogin, false).getFullName());
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BpmTaskInfo getTaskInfo(String taskId) {
        if (isEmpty(taskId)) {
            throw new IllegalArgumentException(NULL_TASK_ID_ERROR);
        }
        return getTaskStartInfo(taskId);
    }

    /**
     * Create application input
     * @param data Data of NFO
     * @param firstTaskName First task name
     * @param additionalInputParameters Additional input parameters for business process
     * @return Input variables map
     */
    private Map<String, Object> getApplicationInput(UserContextData data, String firstTaskName, Map<String, Object> additionalInputParameters) {
        Map<String, Object> applicationInput = new HashMap<>();
        applicationInput.put("clientManagerLogin", data.getUserLogin());
        applicationInput.put("filial", data.getBranch());
//        applicationInput.put("operationDepartmentId", data.getDepartment());
//        applicationInput.put("clientDepartmentId", data.getCustomerDepartment());
//        applicationInput.put("crmClientId", data.getProspectID());
//        applicationInput.put("prospectFlag", data.getProspectFlag());
//        applicationInput.put("bisClientId", data.getBisID());
//        applicationInput.put("relatedPersonId", data.getRelatedPersonsID());
//        applicationInput.put("sessionId", data.getSessionId());
        //applicationInput.put("firstTaskName", firstTaskName);
        if (additionalInputParameters != null)
            applicationInput.put("additionalParameters", additionalInputParameters);
        Map<String, Object> inputData = new HashMap<>();
        inputData.put("applicationInput", applicationInput);
        return inputData;
    }

    /**
     * Get task info
     * @param taskId Task id
     * @return Task info (see {@link BpmTaskInfo})
     */
    private BpmTaskInfo getTaskStartInfo(String taskId) {
        TaskDetailsBody task = getTask(taskId);
        Map<String,Object> variables = (Map<String,Object>)task.getData().get("variables");
        String applicationId = variables != null ? (String)variables.get("applicationId") : null;
        BpmTaskInfo taskInfo = new BpmTaskInfo();
        taskInfo.setId(taskId);
        taskInfo.setApplicationId(applicationId);
        taskInfo.setName(task.getDisplayName());
        return taskInfo;
    }

    /**
     * Get application number from bpm task
     * @param taskId Task id
     * @return Application number
     */
    private String getApplicationNumber(String taskId) {
        return getTask(taskId).getDescription();
    }

    /**
     * Get error description from bpm process
     * @param processId Process ID
     * @return Error description
     */
    private String getErrorFromBpmProcess(String processId) {
        ProcessDetailsBody process = getProcess(processId);
        try {
            String error = getDataValueFromString(process.getData(), BPM_ERROR_FIELD);
            if (StringUtils.isEmpty(error)) {
                error = findErrorInExecutionTree(process.getExecutionTree());
            }
            return error;
        } catch (Exception ex) {
            logger.error("Exception on receiving BPM process error for processId %s", processId, ex);
        }
        return null;
    }

    /**
     * find error description in execution tree
     * @param executionTree Execution tree
     * @return Error description
     */
    private String findErrorInExecutionTree(ExecutionTree executionTree) {
        if (executionTree == null || executionTree.getRoot() == null) {
            return null;
        }
        Stack<ExecutionTreeNode> nodeStack = new Stack<>();
        nodeStack.push(executionTree.getRoot());
        while (!nodeStack.empty()) {
            ExecutionTreeNode node = nodeStack.pop();
            String error = getVariableValueFromMap(node.getVariables(), BPM_ERROR_FIELD);
            if (!StringUtils.isEmpty(error)) {
                return error;
            }
            for (ExecutionTreeNode childNode : node.getChildren()) {
                nodeStack.push(childNode);
            }
        }
        return null;
    }

    /**
     * Get value of variable from json
     * @param dataString json data
     * @param variableName Variable name
     * @return Variable value
     */
    private String getDataValueFromString(String dataString, String variableName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        dataString = dataString.replaceAll("\\\\\"", "\"");
        if (dataString.startsWith("\"")) {
            dataString = dataString.substring(1, dataString.length() - 1);
        }
        Map<String, Object> map = mapper.readValue(dataString, new TypeReference<Map<String, Object>>(){});
        if (!map.containsKey(variableName)) {
            return null;
        }
        return map.get(variableName).toString();
    }

    /**
     * Get value of variable from map
     * @param map Data map
     * @param variableName Variable name
     * @return Variable value
     */
    private String getVariableValueFromMap(Map<String, Object> map, String variableName) {
        if (!map.containsKey(variableName)) {
            return null;
        }
        return map.get(variableName).toString();
    }

    @Override
    public List<UserInfo> getManagedUsersListService(String login, String userServiceId) {
        List<UserInfo> managedUsersList = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        params.put("login", login);
        BpmClient bpmClient = null;
        try {
            bpmClient = getBpmClient();
            ServiceDataBody serviceData =
                    bpmClient.getServiceClient().startService(userServiceId, params).getPayload();

            if (serviceData.getServiceStatus().equals(ServiceStatus.END)) {
                List<String> users = ((Map<String, List<String>>)serviceData.getData().get("userList")).get("items");
                ObjectMapper maper = new ObjectMapper();
                for (Object user : users) {
                    UserInfo userInfo = maper.convertValue(user, UserInfo.class);
                    managedUsersList.add(userInfo);
                }
            }
        } finally {
            closeClient(bpmClient);
        }
        return managedUsersList;
    }


    @Override
    public List<String> getManagedRolesListService(String login, String managedRolesServiceId) {
        List<String> managedRoles = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        params.put("login", login);
        BpmClient bpmClient = null;
        try {
            bpmClient = getBpmClient();
            ServiceDataBody serviceData =
                    bpmClient.getServiceClient().startService(managedRolesServiceId, params).getPayload();

            if (serviceData.getServiceStatus().equals(ServiceStatus.END)) {
                managedRoles = ((Map<String, List<String>>)serviceData.getData().get("roles")).get("items");
            }
        } finally {
            closeClient(bpmClient);
        }
        return managedRoles;
    }

}
