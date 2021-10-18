package ru.digitalspirit.asaka.bpm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@ApiModel(value = "Данные для запуска бизнес-процесса")
public class ProcessAppStartDTO extends ProcessAppCheckDTO {

    @ApiModelProperty(value = "Дополнительные параметры процесса")
    private Map<String, Object> bpParams;

}
