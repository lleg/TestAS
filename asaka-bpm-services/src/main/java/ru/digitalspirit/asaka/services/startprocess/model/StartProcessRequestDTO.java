package ru.digitalspirit.asaka.services.startprocess.model;

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
@XmlRootElement(name="StartProcessRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class StartProcessRequestDTO {

    @ApiModelProperty(value = "Идентификатор заявки")
    private String applicationId;

}
