package ru.digitalspirit.asaka.bpm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Результат запроса")
public class ResponseResult {

    @ApiModelProperty(value = "Успешность запроса")
    private Boolean success;

    @ApiModelProperty(value = "Сообщение")
    private String message;

}
