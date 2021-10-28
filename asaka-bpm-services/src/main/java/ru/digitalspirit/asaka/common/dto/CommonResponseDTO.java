package ru.digitalspirit.asaka.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(value = "CommonProcessResponse")
public class CommonResponseDTO {

    @ApiModelProperty(value = "Код результата")
    @JsonProperty("code")
    String code = null;

    @ApiModelProperty(value = "Описание ошибки")
    @JsonProperty("message")
    String message = null;
}
