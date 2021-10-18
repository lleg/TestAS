package ru.digitalspirit.asaka.bpm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Состояние сессии")
public class SessionStateDTO {

    @ApiModelProperty(value = "Активность сессии")
    private Boolean alive;
}
