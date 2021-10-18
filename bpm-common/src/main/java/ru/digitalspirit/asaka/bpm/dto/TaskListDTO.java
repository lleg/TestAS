package ru.digitalspirit.asaka.bpm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel(value = "Информация о списке задач")
public class TaskListDTO {

    @ApiModelProperty(value = "Список задач")
    private List<TaskDTO> tasks;

    @ApiModelProperty(value = "Общее колличество задач")
    private Long total;

}
