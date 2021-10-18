package ru.digitalspirit.asaka.bpm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Данные пользователя")
public class NfoUserDTO {

    @ApiModelProperty(value = "Идентификатор пользователя")
    private String login;

    @ApiModelProperty(value = "ФИО пользователя")
    private String fullName;

    @ApiModelProperty(value = "Должность пользователя")
    private String position;

    @ApiModelProperty(value = "Email пользователя")
    private String emailAddress;

}
