package ru.digitalspirit.asaka.bpm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.digitalspirit.asaka.bpm.api.client.BpmClient;
import ru.digitalspirit.asaka.bpm.api.helper.JSessionIdHelper;
import ru.digitalspirit.asaka.bpm.api.impl.BpmClientFactory;
import ru.digitalspirit.asaka.bpm.model.organisation.GroupDataBody;
import ru.digitalspirit.asaka.bpm.model.organisation.UserDataBody;
import ru.digitalspirit.asaka.bpm.model.other.exposed.Item;
import ru.digitalspirit.asaka.bpm.model.other.exposed.ItemType;
import ru.digitalspirit.asaka.bpm.model.process.MessageEvent;
import ru.digitalspirit.asaka.bpm.model.process.ProcessDetails;
import ru.digitalspirit.asaka.bpm.model.process.ProcessDetailsBody;
import ru.digitalspirit.asaka.bpm.model.task.AssignedType;
import ru.digitalspirit.asaka.bpm.model.task.TaskClientSettings;
import ru.digitalspirit.asaka.bpm.model.task.TaskDetailsBody;
import ru.digitalspirit.asaka.config.enums.ParameterCode;
import ru.digitalspirit.asaka.config.service.ConfigParametersService;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static ru.digitalspirit.asaka.util.StringUtil.isEmpty;

/**
 * Implementation of BpmService
 */
public class BpmServiceImpl implements BpmService {

    private static final List<String> ACTIVE_STATUSES = Arrays.asList("Received", "New", "Replied", "Sent", " Actioned");
    private static final List<String> COMPLETE_STATUSES = Arrays.asList("Closed");

    private static final String EXTERNAL_TASK_SETTINGS_TYPE = "External";

    private static final String BPM_TASK_SETTINGS_TYPE = "IBM_WLE_Coach";

    private static final Logger logger = LoggerFactory.getLogger(BpmServiceImpl.class);

    private static final long RETRY_INTERVAL = 4500L;
    private static final long BUSINESS_TIMEOUT = 180000L;

    private static final String NULL_TASK_ID_ERROR = "TaskId can't be null!";
    private static final String NULL_USER_NAME_ERROR = "User name can't be null!";
    private static final String SUCCESSFUL = "successful";
    private static final String UNSUCCESSFUL = "unsuccessful";

    @Autowired
    private JSessionIdHelper sessionHelper;

    @Autowired
    private ConfigParametersService configParametersService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String startApplication(String processName) {
        return startApplication(processName, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String startApplication(String processName, Map<String, Object> inputVars) {
        if (isEmpty(processName)) {
            throw new IllegalArgumentException("ProcessName can't be null");
        }

        String processId = null;

        try {
            logger.info("Start launching of application {}", processName);
            Item processItem = getProcessByName(processName);
            logger.info("Application found: {}", processItem.getName());

            ProcessDetails process = startApplication(processItem, inputVars);
            processId = process.getPayload().getPiid();
            logger.info("Application {} started successfully. ProcessId = {}", processName, processId);

        } catch (Exception ex) {
            logger.error("Exception on launching bpm application {}", processName, ex);
            throw new BpmServiceException("Exception on launching bpm application %s", processName);
        }

        return processId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String waitForFirstExternalTask(String processId) {
        if (isEmpty(processId)) {
            throw new IllegalArgumentException("ProcessId can't be null");
        }

        return waitForTask(processId, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String waitForTaskByName(String processId, String name) {
        if (isEmpty(processId)) {
            throw new IllegalArgumentException("ProcessId can't be null!");
        }
        if (isEmpty(name)) {
            throw new IllegalArgumentException("Task name can't be null!");
        }

        return waitForTask(processId, name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUrlForExternalTask(String taskId) {
        if (isEmpty(taskId)) {
            throw new IllegalArgumentException(NULL_TASK_ID_ERROR);
        }

        String url = null;

        try {
            logger.info("Start receiving url for external taskId {}", taskId);
            url = getUrlForTask(taskId, EXTERNAL_TASK_SETTINGS_TYPE);
            logger.info("Task url for external taskId = {} found: {}", taskId, url);
        } catch (Exception ex) {
            logger.error("Exception on receiving url for external taskId {}", taskId, ex);
            throw new BpmServiceException("Exception on receiving url for external taskId  %s", taskId);
        }

        return url;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUrlForBpmTask(String taskId) {
        if (isEmpty(taskId)) {
            throw new IllegalArgumentException(NULL_TASK_ID_ERROR);
        }

        String url = null;

        try {
            logger.info("Start receiving url for bpm taskId {}", taskId);
            url = getUrlForTask(taskId, BPM_TASK_SETTINGS_TYPE);
            logger.info("Task url for bpm taskId = {} found: {}", taskId, url);
        } catch (Exception ex) {
            logger.error("Exception on receiving url for bpm taskId {}", taskId, ex);
            throw new BpmServiceException("Exception on receiving url for bpm taskId  %s", taskId);
        }
        return url;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUserBelongToRole(String user, String role) {
        if (isEmpty(user)) {
            throw new IllegalArgumentException("User can't be null!");
        }
        if (isEmpty(role)) {
            throw new IllegalArgumentException("Role can't be null!");
        }

        boolean isBelong = false;

        try {
            logger.info("Start checking belonging user {} to role {}", user, role);
            isBelong = isBelongToRole(user, role);
            logger.info("User {} {} to role {}", user, (isBelong ? " belongs" : " doesn't belong"), role);
        } catch (Exception ex) {
            logger.error("Exception on checking belonging user {} to role {}", user, role, ex);
            throw new BpmServiceException("Exception on checking belonging user %s to role %s", user, role);
        }

        return isBelong;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTaskAllowedToUser(String taskId, String user) {
        if (isEmpty(taskId)) {
            throw new IllegalArgumentException(NULL_TASK_ID_ERROR);
        }
        if (isEmpty(user)) {
            throw new IllegalArgumentException(NULL_USER_NAME_ERROR);
        }

        try {
            TaskDetailsBody task = getTask(taskId);
            if (isAllowedToUser(task, user)) {
                return true;
            }
        } catch (Exception ex) {
            logger.error("Exception on checking allowing task {} to user {}", taskId, user, ex);
            throw new BpmServiceException("Exception on checking allowing task %s to user %s", taskId, user);
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTaskAssignedToUser(String taskId, String user) {
        if (isEmpty(taskId)) {
            throw new IllegalArgumentException(NULL_TASK_ID_ERROR);
        }
        if (isEmpty(user)) {
            throw new IllegalArgumentException(NULL_USER_NAME_ERROR);
        }

        try {
            TaskDetailsBody task = getTask(taskId);
            if (isAssignedToUser(task, user)) {
                return true;
            }
        } catch (Exception ex) {
            logger.error("Exception on checking assigning task {} to user {}", taskId, user, ex);
            throw new BpmServiceException("Exception on checking assigning task %s to user %s", taskId, user);
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void assignTaskToUser(String taskId, String user) {
        if (isEmpty(taskId)) {
            throw new IllegalArgumentException(NULL_TASK_ID_ERROR);
        }
        if (isEmpty(user)) {
            throw new IllegalArgumentException(NULL_USER_NAME_ERROR);
        }

        try {
            logger.info("Start assigning task {} to user {}", taskId, user);
            boolean isAssigned = assignTaskToUserAndGetTask(taskId, user).getAssignedTo().equalsIgnoreCase(user);
            logger.info("Assignment task {} to user {} was {}", taskId, user, (isAssigned ? SUCCESSFUL : UNSUCCESSFUL));
            if (!isAssigned) {
                throw new BpmServiceException("Unsuccessful assignment task %s to user %s", taskId, user);
            }
        } catch (Exception ex) {
            logger.error("Exception on assigning task {} to user {}", taskId, user, ex);
            throw new BpmServiceException("Exception on assigning task %s to user %s", taskId, user);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void assignTaskToRole(String taskId, String role) {
        if (isEmpty(taskId)) {
            throw new IllegalArgumentException(NULL_TASK_ID_ERROR);
        }
        if (isEmpty(role)) {
            throw new IllegalArgumentException("Role name can't be null!");
        }

        try {
            logger.info("Start assigning task {} to role {}", taskId, role);
            boolean isAssigned = assignTaskToRoleAndGetTask(taskId, role).getAssignedTo().equalsIgnoreCase(role);
            logger.info("Assignment task {} to role {} was {}", taskId, role, (isAssigned ? SUCCESSFUL : UNSUCCESSFUL));
            if (!isAssigned) {
                throw new BpmServiceException("Unsuccessful assignment task %s to role %s", taskId, role);
            }
        } catch (Exception ex) {
            logger.error("Exception on assigning task {} to role {}", taskId, role, ex);
            throw new BpmServiceException("Exception on assigning task %s to role %s", taskId, role);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void completeTask(String taskId) {
        completeTask(taskId, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void completeTask(String taskId, Map<String, Object> outputVars) {
        if (isEmpty(taskId)) {
            throw new IllegalArgumentException(NULL_TASK_ID_ERROR);
        }

        try {
            logger.info("Start completing task {}", taskId);
            if (isComplete(getTask(taskId))) {
                throw new BpmServiceException(BpmServiceException.Code.TASK_IS_ALREADY_CLOSED, "Task is already closed");
            }
            TaskDetailsBody task = finishTask(taskId, outputVars);
            boolean isCompleted = isComplete(task);
            logger.info("Completing task {} was {}", taskId, (isCompleted ? SUCCESSFUL : UNSUCCESSFUL));
            if (!isCompleted) {
                throw new BpmServiceException("Unsuccessful completing task %s", taskId);
            }
        } catch (BpmServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            logger.error("Exception on completing task {}", taskId, ex);
            throw new BpmServiceException("Exception on completing task %s", taskId);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getAllRoles(String user) {
        if (isEmpty(user)) {
            throw new IllegalArgumentException("user can't be null!");
        }

        try {
            logger.info("Start receiving all roles for user {}", user);
            List<String> roles = getRoles(user, true);
            logger.info("Roles received for user {}", user);
            return roles;
        } catch (Exception ex) {
            logger.error("Exception on receiving all roles for user: "+user, ex);
            throw new BpmServiceException("Exception on receiving all roles for user %s", user);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getMainRoles(String user) {
        if (isEmpty(user)) {
            throw new IllegalArgumentException("user can't be null!");
        }

        try {
            logger.info("Start receiving main roles for user {}", user);
            List<String> roles = getRoles(user, false);
            logger.info("Roles received for user {}", user);
            return roles;
        } catch (Exception ex) {
            logger.error("Exception on receiving main roles for user {}", user, ex);
            throw new BpmServiceException("Exception on receiving main roles for user %s", user);
        }
    }

    /**
     * Waiting for task by task name
     *
     * @param processId Process identifier
     * @param taskName  Task name
     * @return Task identifier
     */
    String waitForTask(String processId, String taskName) {
        String taskId = null;

        try {
            logger.info("Start waiting for task {} for processId {}", taskName, processId);
            TaskDetailsBody task = waitForTaskForProcess(processId, taskName);
            if (task != null) {
                taskId = task.getTkiid();
                logger.info("Task found, name = {} id = {} for processId {}", task.getDisplayName(), taskId, processId);
            } else {
                logger.info("Task not found, name = {} for processId {}", taskName, processId);
            }
        } catch (Exception ex) {
            logger.error("Exception on waiting for task {} for processId {}", taskName, processId, ex);
            throw new BpmServiceException("Exception on waiting for task %s for processId %s", taskName, processId);
        }

        return taskId;
    }

    /**
     * Create bpm client for working with BPM REST API
     *
     * @return BPM client
     */
    protected BpmClient getBpmClient() {
        return BpmClientFactory.getFactory().createClient(URI.create(getServerURL()), getUserName(), getPassword(), true, sessionHelper);
    }

    /**
     * Get process by name from BPM REST API
     *
     * @return Process item
     */
    Item getProcessByName(String processName) {
        BpmClient bpmClient = null;
        try {
            bpmClient = getBpmClient();
            return bpmClient.getExposedClient().getItemByName(ItemType.PROCESS, processName);
        } finally {
            closeClient(bpmClient);
        }
    }

    /**
     * Start application by process item with input varaibles
     *
     * @param processItem Process item
     * @param inputVars   Map of input variables
     * @return Process details
     */
    ProcessDetails startApplication(Item processItem, Map<String, Object> inputVars) {
        BpmClient bpmClient = null;
        try {
            bpmClient = getBpmClient();
            return bpmClient.getProcessClient().startProcess(processItem.getItemId(),
                    processItem.getProcessAppId(), null, null, inputVars);
        } finally {
            closeClient(bpmClient);
        }
    }

    /**
     * Assign task to user
     *
     * @param taskId Task identifier
     * @param user   User login
     * @return Task details
     */
    TaskDetailsBody assignTaskToUserAndGetTask(String taskId, String user) {
        BpmClient bpmClient = null;
        try {
            bpmClient = getBpmClient();
            return bpmClient.getTaskClient().assignTaskToUser(taskId, user).getPayload();
        } finally {
            closeClient(bpmClient);
        }
    }

    /**
     * Assign task to role
     *
     * @param taskId Task identifier
     * @param role   Role
     * @return Task details
     */
    TaskDetailsBody assignTaskToRoleAndGetTask(String taskId, String role) {
        BpmClient bpmClient = null;
        try {
            bpmClient = getBpmClient();
            return bpmClient.getTaskClient().assignTaskToGroup(taskId, role).getPayload();
        } finally {
            closeClient(bpmClient);
        }
    }

    /**
     * Complete task with output variables
     *
     * @param taskId     Task identifier
     * @param outputVars Map of output variables
     * @return Task details
     */
    TaskDetailsBody finishTask(String taskId, Map<String, Object> outputVars) {
        BpmClient bpmClient = null;
        try {
            bpmClient = getBpmClient();
            return bpmClient.getTaskClient().completeTask(taskId, outputVars).getPayload();
        } finally {
            closeClient(bpmClient);
        }
    }

    /**
     * Waiting for task by task name
     *
     * @param processId Process identifier
     * @param taskName  Task name
     * @return Task details
     */
    TaskDetailsBody waitForTaskForProcess(String processId, String taskName) throws InterruptedException {
        BpmClient bpmClient = null;
        try {
            bpmClient = getBpmClient();

            long initialTime = System.currentTimeMillis();
            long lastResponseTime = System.currentTimeMillis();

            do {

                TaskDetailsBody bpmTask;
                if (!isEmpty(taskName)) {
                    bpmTask = getActiveTaskByNameForProcess(processId, taskName);
                } else {
                    bpmTask = getExternalTaskForProcess(processId);
                }

                if (bpmTask != null) {
                    return bpmTask;
                }

                try {
                    Thread.sleep(RETRY_INTERVAL);
                } catch (InterruptedException ex) {
                    logger.error("Exception on call Thread.sleep() in waitForTask", ex);
                    throw ex;
                }
                lastResponseTime = System.currentTimeMillis();

            } while ((lastResponseTime - initialTime) < BUSINESS_TIMEOUT);

            return null;
        } finally {
            closeClient(bpmClient);
        }
    }

    /**
     * Check whether the user belongs to role
     *
     * @param user User login
     * @param role Role name
     * @return User belongs
     */
    boolean isBelongToRole(String user, String role) {
        BpmClient bpmClient = null;
        try {
            bpmClient = getBpmClient();
            List<String> members = bpmClient.getOrganisationClient().getGroupByName(role).getPayload().getMembers();
            for (String member : members) {
                if (member.equals(user)) {
                    return true;
                }
            }
            return false;
        } finally {
            closeClient(bpmClient);
        }
    }

    /**
     * Get roles of user
     *
     * @param user            User login
     * @param includeInternal Include internal roles
     * @return List of user role names
     */
    List<String> getRoles(String user, boolean includeInternal) {
        return getUser(user, includeInternal).getMemberships();
    }

    /**
     * Get user data
     *
     * @param user                 User login
     * @param includeInternalRoles Include internal roles
     * @return User data
     */
    public UserDataBody getUser(String user, boolean includeInternalRoles) {
        BpmClient bpmClient = null;
        try {
            bpmClient = getBpmClient();
            return bpmClient.getOrganisationClient().getUserByName(user, includeInternalRoles).getPayload();
        } finally {
            closeClient(bpmClient);
        }
    }

    /**
     * Get users by role
     *
     * @param role Role name
     * @return list of users data
     */
    List<UserDataBody> getUsersByRole(String role) {
        List<UserDataBody> users = new ArrayList<>();
        BpmClient bpmClient = null;
        try {
            bpmClient = getBpmClient();
            GroupDataBody group = bpmClient.getOrganisationClient().getGroupByName(role).getPayload();
            if (group.getMembers() != null && !group.getMembers().isEmpty()) {
                for (String user : group.getMembers()) {
                    users.add(bpmClient.getOrganisationClient().getUserByName(user, false).getPayload());
                }
            }
        } finally {
            closeClient(bpmClient);
        }
        return users;
    }

    /**
     * Get url for task from BPM REST API
     *
     * @param taskId Task id
     * @param type   Task settings type
     * @return URL
     */
    String getUrlForTask(String taskId, String type) {
        return getTaskClientSettings(taskId, type).getPayload().getUrl();
    }

    /**
     * Get task client settings from BPM REST API
     *
     * @param taskId Task id
     * @param type   Task settings type
     * @return Task client settings
     */
    TaskClientSettings getTaskClientSettings(String taskId, String type) {
        BpmClient bpmClient = null;
        try {
            bpmClient = getBpmClient();
            return bpmClient.getTaskClient().getTaskClientSettings(taskId, type);
        } finally {
            closeClient(bpmClient);
        }
    }

    /**
     * Get active task by name for process from BPM REST API
     *
     * @param processId Process id
     * @param taskName  Task name
     * @return Task details body
     */
    TaskDetailsBody getActiveTaskByNameForProcess(String processId, String taskName) {
        BpmClient bpmClient = null;
        try {
            bpmClient = getBpmClient();
            ProcessDetails process = bpmClient.getProcessClient().currentState(processId);
            List<TaskDetailsBody> tasks = process.getPayload().getTasks();
            for (TaskDetailsBody task : tasks) {
                if (isActive(task) && isMatchedTask(task, taskName)) {
                    return task;
                }
            }
            return null;
        } finally {
            closeClient(bpmClient);
        }
    }

    /**
     * Get process details from BPM REST API
     *
     * @param processId Process id
     * @return Process details
     */
    @Override
    public ProcessDetailsBody getProcess(String processId) {
        BpmClient bpmClient = null;
        try {
            bpmClient = getBpmClient();
            return bpmClient.getProcessClient().currentState(processId).getPayload();
        } finally {
            closeClient(bpmClient);
        }
    }


    /**
     * Get task details from BPM REST API
     *
     * @param taskId Task id
     * @return Task details
     */
    @Override
    public TaskDetailsBody getTask(String taskId) {
        BpmClient bpmClient = null;
        try {
            bpmClient = getBpmClient();
            return bpmClient.getTaskClient().getTask(taskId).getPayload();
        } finally {
            closeClient(bpmClient);
        }
    }

    /**
     * Get active external task for process from BPM REST API
     *
     * @param processId Process id
     * @return Task details
     */
    TaskDetailsBody getExternalTaskForProcess(String processId) {
        BpmClient bpmClient = null;
        try {
            bpmClient = getBpmClient();
            ProcessDetails process = bpmClient.getProcessClient().currentState(processId);
            List<TaskDetailsBody> tasks = process.getPayload().getTasks();
            for (TaskDetailsBody task : tasks) {
                if (isActive(task) && isExternalTask(task)) {
                    return task;
                }
            }
            return null;
        } finally {
            closeClient(bpmClient);
        }
    }

    /**
     * Send message event to BPM
     *
     * @param messageEvent Message event
     */
    void senMessageEvent(MessageEvent messageEvent) {
        BpmClient bpmClient = null;
        try {
            bpmClient = getBpmClient();
            bpmClient.getProcessClient().sendMessage(messageEvent);
        } finally {
            closeClient(bpmClient);
        }
    }

    /**
     * Is task matched to task name
     *
     * @param task     Task details
     * @param taskName Task name
     * @return Is matched
     */
    boolean isMatchedTask(TaskDetailsBody task, String taskName) {
        return !isEmpty(taskName) && task.getName().equalsIgnoreCase(taskName);
    }

    /**
     * Is task external
     *
     * @param task Task details
     * @return Is external task
     */
    boolean isExternalTask(TaskDetailsBody task) {
        return !isEmpty(task.getExternalActivityId());
    }

    /**
     * Is task bpm
     *
     * @param task Task details
     * @return Is bpm task
     */
    boolean isBpmTask(TaskDetailsBody task) {
        return !isEmpty(task.getServiceId());
    }

    /**
     * Is task active
     *
     * @param task Task details
     * @return Is active task
     */
    boolean isActive(TaskDetailsBody task) {
        return ACTIVE_STATUSES.contains(task.getStatus());
    }

    /**
     * Is task completed
     *
     * @param task Task details
     * @return Is completed task
     */
    boolean isComplete(TaskDetailsBody task) {
        return COMPLETE_STATUSES.contains(task.getStatus());
    }

    /**
     * Check whether the user is allowed to work with task
     *
     * @param task Task details
     * @param user User login
     * @return Is allowed
     */
    boolean isAllowedToUser(TaskDetailsBody task, String user) {
        return (task.getAssignedToType().equals(AssignedType.GROUP) && isBelongToRole(user, task.getAssignedTo())) ||
                (task.getAssignedToType().equals(AssignedType.USER) && user.equals(task.getAssignedTo()));
    }

    /**
     * Check whether task is assigned to user
     *
     * @param task Task details
     * @param user User login
     * @return Is assigned
     */
    boolean isAssignedToUser(TaskDetailsBody task, String user) {
        return task.getAssignedToType().equals(AssignedType.USER) && user.equals(task.getAssignedTo());
    }

    /**
     * Close bpm client
     *
     * @param bpmClient BPM client
     */
    protected void closeClient(BpmClient bpmClient) {
        try {
            bpmClient.close();
        } catch (Exception e) {
            logger.error("Can't close client", e);
        }
    }

    /**
     * Get Server URL
     *
     * @return Server URL
     */
    String getServerURL() {
        return configParametersService.getParameterValue(ParameterCode.BPM_SERVER_URL);
    }

    /**
     * Get server user name
     *
     * @return Server user name
     */
    String getUserName() {
        return configParametersService.getParameterValue(ParameterCode.BPM_SERVER_LOGIN);
    }

    /**
     * Get server user password
     *
     * @return Server user password
     */
    String getPassword() {
        return configParametersService.getParameterValue(ParameterCode.BPM_SERVER_PASSWORD);
    }

}
