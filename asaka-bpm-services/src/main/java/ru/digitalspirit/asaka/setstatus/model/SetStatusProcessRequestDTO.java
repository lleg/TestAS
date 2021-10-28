package ru.digitalspirit.asaka.setstatus.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@ApiModel(value = "SetStatusProcessRequest")
public class SetStatusProcessRequestDTO {

    @ApiModelProperty(value = "Название процесса")
    @NotNull(message = "Название процесса (processName) не может быть пустым")
    private String processName;

    @ApiModelProperty(value = "Номер заявки в BPM")
    @NotNull(message = "ClaimNumBpm не может быть пустым")
    private String claimNumBpm;

    @ApiModelProperty(value = "Статус заявки")
    @NotNull(message = "Статус (status) не может быть пустым")
    private String status;

}
