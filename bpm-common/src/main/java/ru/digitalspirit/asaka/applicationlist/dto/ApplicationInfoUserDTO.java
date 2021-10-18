package ru.digitalspirit.asaka.applicationlist.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Информация о пользователе")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationInfoUserDTO {

    @ApiModelProperty(value = "Логин пользователя")
    private String userLogin;

    @ApiModelProperty(value = "ФИО пользователя")
    private String userName;

    @ApiModelProperty(value = "НФО роль")
    private String nfoRole;

    @ApiModelProperty(value = "BPM роль")
    private String bpmRole;

}
