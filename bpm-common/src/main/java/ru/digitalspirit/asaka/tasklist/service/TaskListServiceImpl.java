package ru.digitalspirit.asaka.tasklist.service;

import com.querydsl.core.types.dsl.EntityPathBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.util.StringUtils;
import ru.digitalspirit.asaka.tasklist.entity.QTaskEntity;
import ru.digitalspirit.asaka.tasklist.entity.TaskEntity;
import ru.digitalspirit.asaka.tasklist.model.TaskInfoList;
import ru.digitalspirit.asaka.tasklist.repository.TaskRepository;
import ru.digitalspirit.asaka.bpm.model.ColumnCondition;
import ru.digitalspirit.asaka.bpm.model.UserInfo;
import ru.digitalspirit.asaka.bpm.service.NbuBpmService;
import ru.digitalspirit.asaka.bpm.service.UserInfoService;
import ru.digitalspirit.asaka.util.QuerydslUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of TaskListService
 */
public class TaskListServiceImpl implements TaskListService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private NbuBpmService bpmService;

    @Autowired
    private UserInfoService userService;

    private static EntityPathBase<TaskEntity> base = QTaskEntity.taskEntity;

    private static final Logger logger = LoggerFactory.getLogger(TaskListServiceImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskInfoList getByUser(String user, List<ColumnCondition> conditions) {
        try {
            if (conditions == null) {
                conditions = new ArrayList<>();
            }
            conditions.add(getAssignedToFilter(user));
            TaskInfoList taskList = new TaskInfoList();
            taskList.setTasks(makeList(taskRepository.findAll(QuerydslUtil.createExpression(conditions, base), QuerydslUtil.getSort(conditions, base))));
            taskList.setTotal((long) taskList.getTasks().size());
            return taskList;
        } catch (ParseException e) {
            logger.error("Can't get tasks by user " + user, e);
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskInfoList getByUser(String user, List<ColumnCondition> conditions, int page, int size) {
        if (conditions == null) {
            conditions = new ArrayList<>();
        }
        conditions.add(getAssignedToFilter(user));
        return getByConditions(conditions, page, size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskInfoList getByChiefUser(String user, List<ColumnCondition> conditions, int page, int size) {
        if (conditions == null) {
            conditions = new ArrayList<>();
        }
        conditions.add(getAssignedToFilterForChief(user));
        return getByConditions(conditions, page, size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskInfoList get(List<ColumnCondition> conditions, int page, int size) {
        if (conditions == null) {
            conditions = new ArrayList<>();
        }
        return getByConditions(conditions, page, size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskInfoList get(List<ColumnCondition> conditions) {
        if (conditions == null) {
            conditions = new ArrayList<>();
        }
        return getByConditions(conditions);
    }

    /**
     * Get tasks by filters
     *
     * @param conditions List of filters )
     * @param page       Page number
     * @param size       Rows count
     * @return Tasks (see {@link TaskInfoList})
     */
    private TaskInfoList getByConditions(List<ColumnCondition> conditions, int page, int size) {
        try {
            QPageRequest pageRequest = new QPageRequest(page, size, QuerydslUtil.getSort(conditions, base));
            TaskInfoList taskList = new TaskInfoList();
            Page<TaskEntity> resultPage = taskRepository.findAll(QuerydslUtil.createExpression(conditions, base), pageRequest);
            taskList.setTasks(makeList(resultPage));
            taskList.setTotal(resultPage.getTotalElements());
            return taskList;
        } catch (ParseException e) {
            logger.error("Can't get tasks by conditions", e);
            return null;
        }
    }

    /**
     * Get tasks by filters
     *
     * @param conditions List of filters )
     * @return Tasks (see {@link TaskInfoList})
     */
    private TaskInfoList getByConditions(List<ColumnCondition> conditions) {
        try {
            TaskInfoList taskList = new TaskInfoList();
            taskList.setTasks(makeList(taskRepository.findAll(QuerydslUtil.createExpression(conditions, base), QuerydslUtil.getSort(conditions, base))));
            taskList.setTotal((long) taskList.getTasks().size());
            return taskList;
        } catch (ParseException e) {
            logger.error("Can't get tasks by conditions", e);
            return null;
        }
    }

    /**
     * Get AssignedTo filter
     *
     * @param userName User name
     * @return AssignedTo filter )
     */
    private ColumnCondition getAssignedToFilter(String userName) {
        ColumnCondition condition = new ColumnCondition();
        condition.setColumn("assignedTo");
        condition.setOperation("in");
        condition.setValue(userName + "," + StringUtils.collectionToCommaDelimitedString(bpmService.getAllRoles(userName)));
        return condition;
    }

    /**
     * Get AssignedTo filter for chief
     *
     * @param userName Chief user name
     * @return AssignedTo filter )
     */
    private ColumnCondition getAssignedToFilterForChief(String userName) {
        ColumnCondition condition = new ColumnCondition();
        condition.setColumn("assignedTo");
        condition.setOperation("in");
        condition.setValue(StringUtils.collectionToCommaDelimitedString(getManagedUsersAndRoles(userName)));
        return condition;
    }

    /**
     * Get managed users and roles for user
     *
     * @param userName User name
     * @return List of managed users and roles
     */
    private Set<String> getManagedUsersAndRoles(String userName) {
        Set<String> managedUsersAndRoles = new HashSet<>();
        for (UserInfo user : userService.getManagedUsersList(userName)) {
            managedUsersAndRoles.add(user.getLogin());
            managedUsersAndRoles.addAll(bpmService.getAllRoles(user.getLogin()));
        }
        managedUsersAndRoles.addAll(userService.getManagedRolesList(userName));
        return managedUsersAndRoles;
    }

    /**
     * Make list from iterable
     *
     * @param iter Iterable for TaskEntity (see {@link TaskEntity})
     * @return List of TaskEntity (see {@link TaskEntity})
     */
    private static List<TaskEntity> makeList(Iterable<TaskEntity> iter) {
        ArrayList<TaskEntity> list = new ArrayList<>();
        for (TaskEntity item : iter) {
            list.add(item);
        }
        return list;
    }

}
