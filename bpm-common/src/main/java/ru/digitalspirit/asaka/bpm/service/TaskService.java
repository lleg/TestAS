package ru.digitalspirit.asaka.bpm.service;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalspirit.asaka.bpm.dto.*;
import ru.digitalspirit.asaka.tasklist.model.TaskInfoList;
import ru.digitalspirit.asaka.tasklist.service.TaskListService;
import ru.digitalspirit.asaka.bpm.dto.*;
import ru.digitalspirit.asaka.bpm.model.ColumnCondition;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private NbuBpmService bpmService;

    @Autowired
    private UserInfoService userService;

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private TaskListService taskListService;

    public TaskDTO getTask(String taskId) {
        List<ColumnCondition> conditions = new ArrayList<>();
        conditions.add(ColumnCondition.getFilterCondition("taskId", "=", taskId));
        TaskInfoList taskList = taskListService.get(conditions);
        if (taskList != null && taskList.getTotal() > 0) {
            return mapperFacade.map(taskList.getTasks().get(0), TaskDTO.class);
        }
        return new TaskDTO();
    }

    public TaskListDTO getTasks(String userName, List<ColumnFilterDTO> filters) {
        return mapperFacade.map(taskListService.getByUser(userName, mapperFacade.mapAsList(filters, ColumnCondition.class)), TaskListDTO.class);
    }

    public TaskListDTO getTasks(String userName, List<ColumnFilterDTO> filters, Integer page, Integer size) {
        return mapperFacade.map(taskListService.getByUser(userName, mapperFacade.mapAsList(filters, ColumnCondition.class), page, size), TaskListDTO.class);
    }

    public TaskListDTO getTasksForChief(String userName, List<ColumnFilterDTO> filters, Integer page, Integer size) {
        return mapperFacade.map(taskListService.getByChiefUser(userName, mapperFacade.mapAsList(filters, ColumnCondition.class), page, size), TaskListDTO.class);
    }

    public TaskDTO getTaskByApplicationId(String applicationId, String bpCode) {
        List<ColumnCondition> conditions = new ArrayList<>();
        conditions.add(ColumnCondition.getFilterCondition("applicationId", "=", applicationId));
        conditions.add(ColumnCondition.getFilterCondition("bpCode", "=", bpCode));
        TaskInfoList taskList = taskListService.get(conditions);
        if (taskList != null && taskList.getTotal() > 0) {
            return mapperFacade.map(taskList.getTasks().get(0), TaskDTO.class);
        }
        return null;
    }

    public TaskInfoDTO assignTask(String taskId, String userName) {
        return mapperFacade.map(bpmService.assignNfoTaskToUserWithChecking(taskId, userName), TaskInfoDTO.class);
    }

    public TaskInfoDTO assignTaskByManager(String taskId, String userName) {
        return mapperFacade.map(bpmService.assignNfoTaskToUser(taskId, userName), TaskInfoDTO.class);
    }

    public ResponseResult checkPermissions(String taskId, String userName) {
        if (bpmService.isTaskAllowedToUser(taskId, userName)) {
            return getResponseResult(true, "");
        }
        return getResponseResult(false, "У пользователя " + userName + " нет прав на запуск задачи!");
    }

    public NfoUserDTO getAssignerFIO(String taskId) {
        return mapperFacade.map(bpmService.getAssignerFIO(taskId), NfoUserDTO.class);
    }

    public ResponseResult suspendTask(String taskId, String userName) {
        try {
            bpmService.suspendTask(taskId, userName);
            return getResponseResult(true, "");
        } catch (BpmServiceException ex) {
            return getResponseResult(false, "Задача была завершена ранее");
        }
    }

    public ResponseResult completeTask(String taskId, String decision, String userName) {
        try {
            bpmService.completeTask(taskId, decision, userName);
            return getResponseResult(true, "");
        } catch (BpmServiceException ex) {
            return getResponseResult(false, "Задача была завершена ранее");
        }
    }

    public List<NfoUserDTO> getManagedUsers(String login) {
        return mapperFacade.mapAsList(userService.getManagedUsersList(login), NfoUserDTO.class);
    }

    public TaskStatusDTO getStatus(String taskId) {
        TaskStatusDTO taskStatus = new TaskStatusDTO();
        taskStatus.setFinished(bpmService.isCompletedTask(taskId));
        taskStatus.setId(taskId);
        return taskStatus;
    }

    private ResponseResult getResponseResult(boolean success, String message) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setSuccess(success);
        responseResult.setMessage(message);
        return responseResult;
    }
}
