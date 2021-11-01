package ru.digitalspirit.asaka.startprocess.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@ToString
@ApiModel(value = "StartProcessRequest")
public class StartProcessRequestDTO {

    @ApiModelProperty(value = "Название процесса")
    @NotNull(message = "Название процесса (processName) не может быть пустым")
    private String processName;

    @ApiModelProperty(value = "Идентификатор заявки")
    @NotNull(message = "Id заявки (applicationId) не может быть пустым")
    private String applicationId;

    @ApiModelProperty(value = "Номер заявки в BPM")
    @NotNull(message = "ClaimNumBpm не может быть пустым")
    private String claimNumBpm;

}
