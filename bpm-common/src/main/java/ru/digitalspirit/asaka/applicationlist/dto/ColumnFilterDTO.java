package ru.digitalspirit.asaka.applicationlist.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Параметры для фильтрации и/или сортировки по полю")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ColumnFilterDTO {

    @ApiModelProperty(value = "Название поля")
    private String column;

    @ApiModelProperty(value = "Тип сортировки ASC/DESC")
    private String sort;

    @ApiModelProperty(value = "Условие для фильтрации")
    private String operation;

    @ApiModelProperty(value = "Параметр фильтрации")
    private String value;

}
