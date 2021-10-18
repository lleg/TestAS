package ru.digitalspirit.asaka.tasklist.service;

import ru.digitalspirit.asaka.tasklist.model.TaskInfoList;
import ru.digitalspirit.asaka.bpm.model.ColumnCondition;

import java.util.List;

/**
 * Service for working with task list
 */
public interface TaskListService {

    /**
     * Get tasks for user
     *
     * @param user       User login
     * @param conditions List of additional filters )
     * @return Tasks (see {@link TaskInfoList})
     */
    TaskInfoList getByUser(String user, List<ColumnCondition> conditions);

    /**
     * Get tasks for user
     *
     * @param user       User login
     * @param conditions List of additional filters )
     * @param page       Page number
     * @param rows       Rows count
     * @return Tasks (see {@link TaskInfoList})
     */
    TaskInfoList getByUser(String user, List<ColumnCondition> conditions, int page, int rows);

    /**
     * Get tasks of managed users
     *
     * @param user       Chief user login
     * @param conditions List of additional filters )
     * @param page       Page number
     * @param size       Rows count
     * @return Tasks (see {@link TaskInfoList})
     */
    TaskInfoList getByChiefUser(String user, List<ColumnCondition> conditions, int page, int size);

    /**
     * Get tasks by filters
     *
     * @param conditions List of filters )
     * @param page       Page number
     * @param size       Rows count
     * @return Tasks (see {@link TaskInfoList})
     */
    TaskInfoList get(List<ColumnCondition> conditions, int page, int size);

    /**
     * Get tasks by filters
     *
     * @param conditions List of filters )
     * @return Tasks (see {@link TaskInfoList})
     */
    TaskInfoList get(List<ColumnCondition> conditions);

}
