package ru.digitalspirit.asaka.tasklist.model;

import lombok.Getter;
import lombok.Setter;
import ru.digitalspirit.asaka.tasklist.entity.TaskEntity;

import java.util.List;

@Getter
@Setter
public class TaskInfoList {

    private List<TaskEntity> tasks;

    private Long total;

}
