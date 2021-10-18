package ru.digitalspirit.asaka.bpm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Данные для запуска бизнес-процесса")
public class ProcessAppFullStartDTO extends ProcessAppStartDTO {

    @ApiModelProperty(value = "Логин Клиентского менеджера", required = true)
    private String userLogin;

    @ApiModelProperty(value = "Код филиала", required = true)
    private String branch;

}
