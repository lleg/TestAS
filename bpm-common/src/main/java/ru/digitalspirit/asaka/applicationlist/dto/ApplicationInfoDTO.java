package ru.digitalspirit.asaka.applicationlist.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel(value = "Информация о заявке")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationInfoDTO {

    @ApiModelProperty(value = "Идентификатор записи в бд")
    private Long id;

    @ApiModelProperty(value = "Идентификатор заявки в бд")
    private String applicationId;

    @ApiModelProperty(value = "Код бизнес-процесса")
    private String businessProcessCode;

    @ApiModelProperty(value = "Номер заявки")
    private String applicationNumber;

    @ApiModelProperty(value = "Отделение")
    private String department;

    @ApiModelProperty(value = "Филиал")
    private String branch;
    
    @ApiModelProperty(value = "Централизованная площадка")
    private String centralSquare;

    @ApiModelProperty(value = "Статус заявки")
    private String applicationStatus;

    @ApiModelProperty(value = "Наименование клиента")
    private String clientName;

    @ApiModelProperty(value = "Сегмент клиента")
    private String clientSegment;

    @ApiModelProperty(value = "ИНН клиента")
    private String clientInn;

    @ApiModelProperty(value = "ИНН клиента")
    private String modifiedDate;

    @ApiModelProperty(value = "Пользователи")
    private List<ApplicationInfoUserDTO> users;

    @ApiModelProperty(value = "Логин клиентского менеджера")
    private String clientManagerLogin;

    @ApiModelProperty(value = "ФИО клиентского менеджера")
    private String clientManagerName;

    @ApiModelProperty(value = "Логин сотрудника MO")
    private String middleOfficeLogin;

    @ApiModelProperty(value = "ФИО сотрудника MO")
    private String middleOfficeName;

    @ApiModelProperty(value = "Доп. информация")
    private ApplicationAddInfoDTO additionalInfo;
}
