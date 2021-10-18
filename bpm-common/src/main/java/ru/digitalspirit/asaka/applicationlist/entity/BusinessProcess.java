package ru.digitalspirit.asaka.applicationlist.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "BUSINESSPROCESS")
@ApiModel(value = "Справочник бизнес-процессов")
public class BusinessProcess implements Serializable { // implements Serializable due to HHH-7668

    @Id
    @Column(name = "ID")
    @ApiModelProperty(value = "Внутренний идентификатор")
    private Long id;

    @Column(name = "BP_CODE")
    @ApiModelProperty(value = "Символьный код")
    private String code;

    @Column(name = "BP_NAME")
    @ApiModelProperty(value = "Наименование бизнесс-процесса")
    private String name;

    @Column(name = "FIRST_TASK_NAME")
    @ApiModelProperty(value = "Наименование первой задачи бизнесс-процесса")
    private String firstTaskName;

    @Column(name = "URL")
    @ApiModelProperty(value = "Url веб-приложения для бизнесс-процесса")
    private String url;

    @Column(name = "DISPLAYNAME")
    @ApiModelProperty(value = "Наименование бизнесс-процесса для портала")
    private String displayName;

    @Column(name = "VISIBLE")
    @ApiModelProperty(value = "Видимость бизнесс-процесса")
    private Boolean active;

    @Column(name = "ISVISIBLEMSB")
    @ApiModelProperty(value = "Видимость для клиентов сегмента МСБ")
    private Boolean isVisibleMSB;

    @Column(name = "ISVISIBLECORP")
    @ApiModelProperty(value = "Видимость для клиентов сегмента КОРП")
    private Boolean isVisibleCORP;

    @Column(name = "BP_SUBJECT_ID")
    @ApiModelProperty(value = "Ид тематики в Siebel CRM")
    private String subjectId;

    @Column(name = "BP_ACRONYM")
    @ApiModelProperty(value = "Акроним бизнесс-процесса")
    private String acronym;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="BUSINESSPROCESS_STARTROLES",
            joinColumns = @JoinColumn(name="BP_CODE", referencedColumnName = "BP_CODE"),
            inverseJoinColumns = @JoinColumn(name="ROLE_CODE", referencedColumnName = "CODE")
    )
    @ApiModelProperty(value = "Роли старта бизнес-процесса")
    private List<BusinessProcessRole> startRoles;
}
