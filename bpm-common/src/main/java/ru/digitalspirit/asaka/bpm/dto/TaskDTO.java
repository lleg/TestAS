package ru.digitalspirit.asaka.bpm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Информация о задаче")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDTO {

    @ApiModelProperty(value = "Идентификатор записи в бд")
    private Long id;

    @ApiModelProperty(value = "Номер заявки")
    private String applicationNumber;

    @ApiModelProperty(value = "Клиент")
    private String client;

    @ApiModelProperty(value = "Тип клиента")
    private String clientType;

    @ApiModelProperty(value = "Идентификатор задачи")
    private String taskId;

    @ApiModelProperty(value = "Статус задачи")
    private String taskStatus;

    @ApiModelProperty(value = "Название задачи")
    private String taskName;

    @ApiModelProperty(value = "Номер филиала")
    private String filial;

    @ApiModelProperty(value = "Филиал")
    private String filialName;

    @ApiModelProperty(value = "Отделение")
    private String department;

    @ApiModelProperty(value = "Владелец задачи")
    private String assignedTo;

    @ApiModelProperty(value = "Тип владельца задачи (пользователь/группа)")
    private String assignedType;

    @ApiModelProperty(value = "Бизнес статус")
    private String businessStatus;

    @ApiModelProperty(value = "КМ")
    private String kmFIO;

    @ApiModelProperty(value = "Дата создания задачи")
    private String taskCreationDate;

    @ApiModelProperty(value = "Срок выполнения задачи")
    private String taskDueDate;

    @ApiModelProperty(value = "Идентификатор заявки в бд")
    private String applicationId;

    @ApiModelProperty(value = "Код бизнес-процесса")
    private String bpCode;

}
