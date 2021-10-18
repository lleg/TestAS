package ru.digitalspirit.asaka.bpm.service;

import com.google.common.collect.ImmutableList;
import ru.digitalspirit.asaka.bpm.model.BpmTaskInfo;
import ru.digitalspirit.asaka.bpm.model.organisation.UserDataBody;
import ru.digitalspirit.asaka.bpm.model.process.ProcessDetailsBody;
import ru.digitalspirit.asaka.bpm.model.task.TaskDetailsBody;
import ru.digitalspirit.asaka.bpm.model.UserContextData;
import ru.digitalspirit.asaka.bpm.model.UserInfo;

import java.util.Collections;
import java.util.List;
import java.util.Map;


public class BpmServiceMock implements NbuBpmService {
    @Override
    public String startApplication(String processName) {
        return "";
    }

    @Override
    public String startApplication(String processName, Map<String, Object> inputVars) {
        return "";
    }

    @Override
    public String waitForTaskByName(String processId, String taskName) {
        return null;
    }

    @Override
    public String waitForFirstExternalTask(String processId) {
        return null;
    }

    @Override
    public String getUrlForExternalTask(String taskId) {
        return null;
    }

    @Override
    public String getUrlForBpmTask(String taskId) {
        return null;
    }

    @Override
    public boolean isTaskAllowedToUser(String taskId, String user) {
        return true;
    }

    @Override
    public boolean isTaskAssignedToUser(String taskId, String user) {
        return true;
    }

    @Override
    public boolean isUserBelongToRole(String user, String role) {
        return false;
    }

    @Override
    public void assignTaskToUser(String taskId, String user) {

    }

    @Override
    public void assignTaskToRole(String taskId, String role) {

    }

    @Override
    public void completeTask(String taskId) {

    }

    @Override
    public void completeTask(String taskId, Map<String, Object> outputVars) {

    }

    @Override
    public List<String> getAllRoles(String user) {
        return Collections.emptyList();
    }

    @Override
    public List<String> getMainRoles(String user) {
        return Collections.emptyList();
    }

    @Override
    public BpmTaskInfo startProcess(String processName, String firstTaskName, UserContextData userContextData) {
        return null;
    }

    @Override
    public BpmTaskInfo startProcess(String processName, String firstTaskName, UserContextData userContextData, Map<String, Object> additionalInputParameters) {
        return null;
    }

    @Override
    public BpmTaskInfo assignNfoTaskToUser(String taskId, String userName) {
        return null;
    }

    @Override
    public BpmTaskInfo assignNfoTaskToUserWithChecking(String taskId, String userName) {
        return null;
    }

    @Override
    public UserInfo getAssignerFIO(String taskId) {
        return null;
    }

    @Override
    public void completeTask(String taskId, String decision, String userName) {

    }

    @Override
    public void suspendTask(String taskId, String userName) {

    }

    @Override
    public boolean isCompletedTask(String taskId) {
        return false;
    }

    @Override
    public BpmTaskInfo getTaskInfo(String taskId) {
        return null;
    }

    @Override
    public UserDataBody getUser(String user, boolean includeInternalRoles) {
        UserDataBody mock = new UserDataBody();
        mock.setUserName(user);
        mock.setFullName("Иванов Иван Иванович");
        mock.setMemberships(ImmutableList.<String>of("ADMIN"));
        return mock;
    }

    @Override
    public List<UserInfo> getManagedUsersListService(String login, String userServiceId) {
        return Collections.emptyList();
    }

    @Override
    public List<String> getManagedRolesListService(String login, String managedRolesServiceId) {
        return Collections.emptyList();
    }

    @Override
    public ProcessDetailsBody getProcess(String processId) {
        return null;
    }

    @Override
    public TaskDetailsBody getTask(String taskId) {
        return null;
    }
}
