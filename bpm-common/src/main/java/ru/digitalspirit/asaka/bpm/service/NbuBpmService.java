package ru.digitalspirit.asaka.bpm.service;

import ru.digitalspirit.asaka.bpm.model.BpmTaskInfo;
import ru.digitalspirit.asaka.bpm.model.organisation.UserDataBody;
import ru.digitalspirit.asaka.bpm.model.UserContextData;
import ru.digitalspirit.asaka.bpm.model.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * Service for working with nfo process applications
 */
public interface NbuBpmService extends BpmService {

    /**
     * Start BPM process application
     *
     * @param processName   Process name
     * @param firstTaskName First task name
     * @param userContextData       Data of NFO
     * @return Info about first task (see {@link BpmTaskInfo})
     */
    BpmTaskInfo startProcess(String processName, String firstTaskName, UserContextData userContextData);

    /**
     * Start BPM process application with additionalInputParameters
     *
     * @param processName               Process name
     * @param firstTaskName             First task name
     * @param userContextData                   Data of NFO
     * @param additionalInputParameters Additional input parameters for business process
     * @return Info about first task (see {@link BpmTaskInfo})
     */
    BpmTaskInfo startProcess(String processName, String firstTaskName, UserContextData userContextData, Map<String, Object> additionalInputParameters);

    /**
     * Assign any active task to user
     *
     * @param taskId   Task identifier
     * @param userName User login
     * @return Info about first task (see {@link BpmTaskInfo})
     */
    BpmTaskInfo assignNfoTaskToUser(String taskId, String userName);

    /**
     * Assign active task to user (only tasks allowed to user)
     *
     * @param taskId   Task identifier
     * @param userName User login
     * @return Info about first task (see {@link BpmTaskInfo})
     */
    BpmTaskInfo assignNfoTaskToUserWithChecking(String taskId, String userName);

    /**
     * Get info about user to whom the task is assigned
     *
     * @param taskId Task identifier
     * @return Info about user (see {@link UserInfo})
     */
    UserInfo getAssignerFIO(String taskId);

    /**
     * Complete task
     *
     * @param taskId   Task identifier
     * @param decision Task execution result
     * @param userName User login
     */
    void completeTask(String taskId, String decision, String userName);

    /**
     * Suspend task
     *
     * @param taskId   Task identifier
     * @param userName User login
     */
    void suspendTask(String taskId, String userName);

    /**
     * Check whether task is completed
     *
     * @param taskId Task identifier
     * @return Is completed
     */
    boolean isCompletedTask(String taskId);

    /**
     * Get task info
     *
     * @param taskId Task id
     * @return Task info (see {@link BpmTaskInfo})
     */
    BpmTaskInfo getTaskInfo(String taskId);

    UserDataBody getUser(String user, boolean includeInternalRoles);

    List<UserInfo> getManagedUsersListService(String login, String userServiceId);

    List<String> getManagedRolesListService(String login, String managedRolesServiceId);
}
