package ru.digitalspirit.asaka.startprocess.model;

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
@ApiModel(value = "StartProcessRequest")
public class StartProcessRequestDTO {

    @ApiModelProperty(value = "Название процесса")
    private String processName;

    @ApiModelProperty(value = "Идентификатор заявки")
    private String applicationId;

}
