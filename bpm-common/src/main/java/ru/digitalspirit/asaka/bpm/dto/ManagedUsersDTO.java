package ru.digitalspirit.asaka.bpm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Логин подчиненного")
public class ManagedUsersDTO {

    @ApiModelProperty(value = "Логин подчиненного сотрудника")
    private String login;
}
