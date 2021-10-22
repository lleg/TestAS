package ru.digitalspirit.asaka.services.startprocess.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@ToString
@ApiModel(value = "StartProcessResponse")
@XmlRootElement(name="StartProcessResponse")
public class StartProcessResponseDTO {

    @ApiModelProperty(value = "Код результата")
    @JsonProperty("code")
    private String code = null;

    @ApiModelProperty(value = "Описание ошибки")
    @JsonProperty("message")
    private String message = null;
}
