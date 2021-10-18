package ru.digitalspirit.asaka.bpm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Данные для проверки бизнес-процесса")
public class ProcessAppCheckDTO {

    @ApiModelProperty(value = "Код бизнес-процесса")
    private String bpCode;

    @ApiModelProperty(value = "Идентификатор бизнес-процесса")
    private String bpId;

    @ApiModelProperty(value = "Идентификатор тематики в Siebel CRM")
    private String bpSubjectId;

    @ApiModelProperty(value = "ID сессии")
    private String sessionId;

}
