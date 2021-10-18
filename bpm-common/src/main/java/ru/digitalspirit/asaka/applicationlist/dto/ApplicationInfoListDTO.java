package ru.digitalspirit.asaka.applicationlist.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel(value = "Информация о списке заявок")
public class ApplicationInfoListDTO {

    @ApiModelProperty(value = "Список заявок")
    private List<ApplicationInfoDTO> applications;

    @ApiModelProperty(value = "Общее колличество заявок")
    private Long total;

}
