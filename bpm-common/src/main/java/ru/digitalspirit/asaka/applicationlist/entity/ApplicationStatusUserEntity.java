package ru.digitalspirit.asaka.applicationlist.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "APPLICATION_USER_LIST")
@Getter
@Setter
public class ApplicationStatusUserEntity {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_LOGIN")
    private String userLogin;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "NFO_ROLE")
    private String nfoRole;

    @Column(name = "BPM_ROLE")
    private String bpmRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APP_LIST_ID")
    private ApplicationStatusEntity appInfo;

}
