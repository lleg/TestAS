package ru.digitalspirit.asaka.bpm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import ru.digitalspirit.asaka.config.enums.ParameterCode;
import ru.digitalspirit.asaka.config.service.ConfigParametersService;
import ru.digitalspirit.asaka.bpm.model.UpdateTaskListRequest;

import java.net.URI;

/**
 * Helper for call update task list service
 */
public class NbuBpmTaskListHelper {

    @Autowired
    private ConfigParametersService configParametersService;

    private RestTemplate restTemplate;

    public NbuBpmTaskListHelper(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Call update task list service
     * @param taskId Task id
     */
    public void updateTaskList(String taskId) {
        UpdateTaskListRequest request = new UpdateTaskListRequest();
        request.setTaskId(taskId);
        restTemplate.put(URI.create(getUpdateTaskListUrl()), request);
    }

    /**
     * Get update task list service url
     * @return Update task list service url
     */
    private String getUpdateTaskListUrl() {
        return configParametersService.getParameterValue(ParameterCode.UPDATE_TASK_LIST_URL);
    }

}
