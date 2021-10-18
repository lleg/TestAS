package ru.digitalspirit.asaka.bpm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Информация о состоянии задачи")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskStatusDTO {

    @ApiModelProperty(value = "ID задачи")
    private String id;

    @ApiModelProperty(value = "Флаг завершения задачи")
    private Boolean finished;

}
