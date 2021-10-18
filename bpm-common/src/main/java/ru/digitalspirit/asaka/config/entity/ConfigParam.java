package ru.digitalspirit.asaka.config.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "CONFIG_PARAM")
@ApiModel(value = "Параметры конфигурации приложений")
public class ConfigParam {

    @Id
    @Column(name = "ID")
    @ApiModelProperty(value = "Внутренний идентификатор")
    private Integer id;

    @Column(name = "PARAM_CODE")
    @ApiModelProperty(value = "Код")
    private String code;

    @Column(name = "PARAM_DESC")
    @ApiModelProperty(value = "Описание")
    private String description;

    @Column(name = "PARAM_VALUE")
    @ApiModelProperty(value = "Значение")
    private String value;
}
