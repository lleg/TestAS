package ru.digitalspirit.asaka.bpm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "ID сессии")
public class SessionIdDTO {

    @ApiModelProperty(value = "ID сессии")
    private String sessionId;
}
