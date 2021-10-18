package ru.digitalspirit.asaka.bpm.dto;

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

    @ApiModelProperty(value = "Тип сортировки ASC/DESC", allowableValues="desc, asc")
    private String sort;

    @ApiModelProperty(value = "Условие для фильтрации", allowableValues="=, >, >=, <, <=, %, in, eq, null, notNull")
    private String operation;

    @ApiModelProperty(value = "Параметр фильтрации")
    private String value;

}
