package ru.digitalspirit.asaka.bpm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import ru.digitalspirit.asaka.bpm.model.ChangeStatusRequest;
import ru.digitalspirit.asaka.bpm.model.process.ProcessDetailsBody;
import ru.digitalspirit.asaka.bpm.model.task.TaskDetailsBody;
import ru.digitalspirit.asaka.config.enums.ParameterCode;
import ru.digitalspirit.asaka.config.service.ConfigParametersService;

import java.net.URI;
import java.util.Map;

/**
 * Helper for call change status service
 */
public class NbuBpmSendStatusHelper {

    @Autowired
    private BpmService bpmService;

    @Autowired
    private ConfigParametersService configParametersService;

    private RestTemplate restTemplate;

    private static final String TASK_STARTED_STATUS = "TASK_STARTED";

    private static final  String TASK_ASSIGNED_STATUS = "TASK_ASSIGNED";

    private static final  String TASK_SUSPENDED_STATUS = "TASK_SUSPENDED";

    private static final  String TASK_FINISHED_STATUS = "TASK_FINISHED";


    public NbuBpmSendStatusHelper(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Send task started status
     * @param taskId Task id
     * @param user User login
     */
    public void sendTaskStartedStatus(String taskId, String user) {
        sendTaskStatus(taskId, TASK_STARTED_STATUS, user);
    }

    /**
     * Send task assigned status
     * @param taskId Task id
     * @param user User login
     */
    public void sendTaskAssignedStatus(String taskId, String user) {
        sendTaskStatus(taskId, TASK_ASSIGNED_STATUS, user);
    }

    /**
     * Send task suspended status
     * @param taskId Task id
     * @param user User login
     */
    public void sendTaskSuspendedStatus(String taskId, String user) {
        sendTaskStatus(taskId, TASK_SUSPENDED_STATUS, user);
    }

    /**
     * Send task finished status
     * @param taskId Task id
     * @param user User login
     */
    public void sendTaskFinishedStatus(String taskId, String user) {
        sendTaskStatus(taskId, TASK_FINISHED_STATUS, user);
    }

    /**
     * Send task finished status
     * @param taskId Task id
     * @param status Task status
     * @param user User login
     */
    private void sendTaskStatus(String taskId, String status, String user) {
        ChangeStatusRequest request = createChangeStatusRequest(taskId, status, user);
        restTemplate.put(URI.create(getChangeStatusUrl()), request);
    }

    /**
     * Create change status request
     * @param taskId Task id
     * @param status Status
     * @param user User login
     * @return Change status request
     */
    private ChangeStatusRequest createChangeStatusRequest(String taskId, String status, String user) {
        ChangeStatusRequest changeStatusRequest = new ChangeStatusRequest();

        TaskDetailsBody task = bpmService.getTask(taskId);
        Map<String,Object> variables = (Map<String,Object>)task.getData().get("variables");
        String applicationId = variables != null ? (String)variables.get("applicationId") : null;
        ProcessDetailsBody processDetailsBody = bpmService.getProcess(task.getPiid());
        String appAcronym = processDetailsBody.getProcessAppAcronym();

        changeStatusRequest.setAppAcronym(appAcronym);
        changeStatusRequest.setApplicationId(applicationId);
        changeStatusRequest.setTaskName(task.getDisplayName());
        changeStatusRequest.setStatusCode(status);
        changeStatusRequest.setUser(user);
        return changeStatusRequest;
    }

    /**
     * Get change status service url
     * @return Change status service url
     */
    private String getChangeStatusUrl() {
        return configParametersService.getParameterValue(ParameterCode.CHANGE_STATUS_URL);
    }

}
