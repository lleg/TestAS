package ru.digitalspirit.asaka.bpm.api.client;

import ru.digitalspirit.asaka.bpm.model.task.TaskClientSettings;
import ru.digitalspirit.asaka.bpm.model.task.TaskStartData;
import ru.digitalspirit.asaka.bpm.model.task.TaskData;
import ru.digitalspirit.asaka.bpm.model.task.TaskDetails;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;


//TODO: Add full api possibilities

/**
 * Client for task api actions.
 */
public interface TaskClient {

    /**
     * Retrieves the details of a task.
     *
     * @param tkiid The id of the task instance to be retrieved.
     * @return the detailed task information (see {@link TaskDetails});
     * @throws IllegalArgumentException if tkiid is null
     */
    TaskDetails getTask(@Nonnull String tkiid);

    /**
     * Use this method to start a task. The input variables defined on the task will be set according to the definitions in
     * the associated business process instance. The task will proceed until the first coach is encountered.
     *
     * @param tkiid The id of the task instance to be retrieved.
     * @return task start data which contains a list of attribute/value pairs (see {@link TaskStartData}})
     * @throws IllegalArgumentException if tkiid is null
     */
    TaskStartData startTask(@Nonnull String tkiid);

    /**
     * Assign the specified task to the current user.
     *
     * @param tkiid The id of the task instance to be assigned.
     * @return the detailed task information (see {@link TaskDetails});
     * @throws IllegalArgumentException if tkiid is null
     */
    TaskDetails assignTaskToMe(@Nonnull String tkiid);

    /**
     * Assign the specified task back to the original task owner.
     *
     * @param tkiid The id of the task instance to be assigned.
     * @return the detailed task information (see {@link TaskDetails});
     * @throws IllegalArgumentException if tkiid is null
     */
    TaskDetails assignTaskBack(@Nonnull String tkiid);

    /**
     * Assign the specified task to another user.
     * If userName is null, reassigned task back to the original task owner.
     *
     * @param tkiid The id of the task instance to be assigned.
     * @return the detailed task information (see {@link TaskDetails});
     * @throws IllegalArgumentException if tkiid is null
     */
    TaskDetails assignTaskToUser(@Nonnull String tkiid, @Nullable String userName);

    /**
     * Assign the specified task to a group.
     * If groupName is null, reassigned task back to the original task owner.
     *
     * @param tkiid The id of the task instance to be assigned.
     * @return the detailed task information (see {@link TaskDetails});
     * @throws IllegalArgumentException if tkiid is null
     */
    TaskDetails assignTaskToGroup(@Nonnull String tkiid, @Nullable String groupName);

    /**
     * Finish the specified task.
     *
     * @param tkiid The id of the task instance to be assigned.
     * @param input Parameters to finish specified task/activity. Parameters NOT propagated to enclosing process. Use Task
     *              api setData(@Nonnull String tkiid, @Nullable Map&lt;String, Object&gt; input) for that purpose.
     * @return the detailed task information (see {@link TaskDetails});
     * @throws IllegalArgumentException if tkiid is null
     */
    TaskDetails completeTask(@Nonnull String tkiid, @Nullable Map<String, Object> input);

    /**
     * Get data from specified task
     *
     * @param tkiid  The id of the task instance to be assigned.
     * @param fields Comma-separated list of fields
     * @return task data information (see {@link TaskData});
     * @throws IllegalArgumentException if tkiid is null
     */
    TaskData getData(@Nonnull String tkiid, @Nullable String fields);

    /**
     * Retrieves the client settings of a task
     *
     * @param tkiid The id of the task instance to be assigned.
     * @param type  type of client settings: "IBM_WLE_Coach" or "External"
     * @return task data information (see {@link TaskData});
     * @throws IllegalArgumentException if tkiid is null
     */
    TaskClientSettings getTaskClientSettings(@Nonnull String tkiid, @Nonnull String type);

}
