package ru.digitalspirit.asaka.applicationlist.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "BUSINESSPROCESS_ROLES")
@ApiModel(value = "Справочник ролей бизнес-процессов")
public class BusinessProcessRole implements Serializable { // implements Serializable due to HHH-7668

    @Id
    @Column(name = "ID")
    @ApiModelProperty(value = "Внутренний идентификатор")
    private Long id;

    @Column(name = "CODE")
    @ApiModelProperty(value = "Символьный код")
    private String code;

    @Column(name = "PREFIX")
    @ApiModelProperty(value = "Префикс в названии роли")
    private String prefix;

    @Column(name = "SUFFIX")
    @ApiModelProperty(value = "Суффикс в названии роли")
    private String suffix;

    @Column(name = "WITH_DEPARTMENT")
    @ApiModelProperty(value = "Отделение в названии роли")
    private Boolean withDepartment;

    @Column(name = "WITH_CENTRAL_SQUARE")
    @ApiModelProperty(value = "Централизованная площадка в названии роли")
    private Boolean withCentralSquare;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MANAGED_ROLE", referencedColumnName = "CODE")
    @ApiModelProperty(value = "Управляемая роль")
    private BusinessProcessRole managedRole;

}
