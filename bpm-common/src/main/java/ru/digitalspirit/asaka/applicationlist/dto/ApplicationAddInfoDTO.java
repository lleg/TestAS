package ru.digitalspirit.asaka.applicationlist.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Информация о заявке")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationAddInfoDTO {


    @ApiModelProperty(value = "Номер протокола")
    private String protocolNumber;

    @ApiModelProperty(value = "Статус протокола")
    private String protocolStatus;

    @ApiModelProperty(value = "Кол-во проголосовавших членов КК")
    private Long protocolCountMember;

    @ApiModelProperty(value = "Общее кол-во членов КК")
    private Long protocolTotalCountMember;
}
