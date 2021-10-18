package ru.digitalspirit.asaka.bpm.service;

import ru.digitalspirit.asaka.bpm.model.process.ProcessDetailsBody;
import ru.digitalspirit.asaka.bpm.model.task.TaskDetailsBody;

import java.util.List;
import java.util.Map;


/**
 * Common service for working with BPM REST API
 */
public interface BpmService {

    /**
     * Start BPM process application by process name
     * @param processName Process name
     * @return Process identifier
     */
    String startApplication(String processName);

    /**
     * Start BPM process application by process name with input variables
     * @param processName Process name
     * @param inputVars Map of input variables
     * @return Process identifier
     */
    String startApplication(String processName, Map<String, Object> inputVars);

    /**
     * Waiting for task by task name
     * @param processId Process identifier
     * @param taskName Task name
     * @return Task identifier
     */
    String waitForTaskByName(String processId, String taskName);

    /**
     * Waiting for first task implemented as "External implementation"
     * @param processId Process identifier
     * @return Task identifier
     */
    String waitForFirstExternalTask(String processId);

    /**
     * Get url of task implemented as "External implementation"
     * @param taskId Task identifier
     * @return Url of task
     */
    String getUrlForExternalTask(String taskId);

    /**
     * Get url of task implemented as "Human service"
     * @param taskId Task identifier
     * @return Url of task
     */
    String getUrlForBpmTask(String taskId);

    /**
     * Check whether the user is allowed to work with task
     * @param taskId Task identifier
     * @param user User login
     * @return Is allowed
     */
    boolean isTaskAllowedToUser(String taskId, String user);

    /**
     * Check whether task is assigned to user
     * @param taskId Task identifier
     * @param user User login
     * @return Is assigned
     */
    boolean isTaskAssignedToUser(String taskId, String user);

    /**
     * Check whether the user belongs to role
     * @param user User login
     * @param role Role name
     * @return User belongs
     */
    boolean isUserBelongToRole(String user, String role);

    /**
     * Assign task to user
     * @param taskId Task identifier
     * @param user User login
     */
    void assignTaskToUser(String taskId, String user);

    /**
     * Assign task to role
     * @param taskId Task identifier
     * @param role Role name
     */
    void assignTaskToRole(String taskId, String role);

    /**
     * Complete task
     * @param taskId Task identifier
     */
    void completeTask(String taskId);

    /**
     * Complete task with output variables
     * @param taskId Task identifier
     * @param outputVars Map of output variables
     */
    void completeTask(String taskId, Map<String, Object> outputVars);

    /**
     * Get all roles of user (including internal)
     * @param user User login
     * @return List of user role names
     */
    List<String> getAllRoles(String user);

    /**
     * Get roles of user (not including internal)
     * @param user User login
     * @return List of user role names
     */
    List<String> getMainRoles(String user);

    ProcessDetailsBody getProcess(String processId);

    TaskDetailsBody getTask(String taskId);
}
