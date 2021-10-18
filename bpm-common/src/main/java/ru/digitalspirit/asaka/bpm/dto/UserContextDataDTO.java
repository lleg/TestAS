package ru.digitalspirit.asaka.bpm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@ApiModel(value = "Данные запуска")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserContextDataDTO {

    @ApiModelProperty(value = "Идентификатор пользователя")
    private String userLogin;

    @ApiModelProperty(value = "ФИО пользователя")
    private String userName;

    @ApiModelProperty(value = "Роль пользователя (KM, KM_CHIEF, MO, MO_CHIEF, KMPBU, CONT_PBU, KMPBU_CHIEF)")
    private String userRole;

    @ApiModelProperty(value = "Номер отделения операции")
    private String branch;

    @ApiModelProperty(value = "Номер отделения операции")
    private String department;

    @ApiModelProperty(value = "Номер отделения клиента")
    private String customerDepartment;

    @ApiModelProperty(value = "ID клиента/проспекта CRM")
    private String prospectID;

    @ApiModelProperty(value = "Признак - клиент (false)/проспект (true)")
    private Boolean prospectFlag;

    @ApiModelProperty(value = "Признак тип клиента")
    private String clientType;

    @ApiModelProperty(value = "ID клиента BIS")
    private String bisID;

    @ApiModelProperty(value = "ID представителя (довер. лица) в CRM")
    private String relatedPersonsID;

    @ApiModelProperty(value = "ID заявки операции открытия счета")
    private String operationID;

    @ApiModelProperty(value = "ID тематики CRM")
    private String subjectsID;

    @ApiModelProperty(value = "ID сессии")
    private String sessionId;

    @ApiModelProperty(value = "Роли пользователя в BPM")
    private Set<String> bpmRoles;

    @ApiModelProperty(value = "Email пользователя")
    private String email;

}
