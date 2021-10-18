package ru.digitalspirit.asaka.bpm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Информация для старта задач")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskInfoDTO {

    @ApiModelProperty(value = "ID задачи")
    private String id;

    @ApiModelProperty(value = "Идентификатор заявки")
    private String applicationId;

    @ApiModelProperty(value = "URL для запуска задачи (заполняется при старте БП)")
    private String url;

}
