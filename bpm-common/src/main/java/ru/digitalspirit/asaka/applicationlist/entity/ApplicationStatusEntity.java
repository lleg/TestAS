package ru.digitalspirit.asaka.applicationlist.entity;

import lombok.Getter;
import lombok.Setter;
import ru.digitalspirit.asaka.applicationlist.model.ApplicationInfo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "APPLICATION_LIST")
@Getter
@Setter
public class ApplicationStatusEntity implements ApplicationInfo {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "APPLICATION_ID")
    private String applicationId;

    @Column(name = "BUSINESS_PROCESS_CODE")
    private String businessProcessCode;

    @Column(name = "APPLICATION_NUMBER")
    private String applicationNumber;

    @Column(name = "DEPARTMENT")
    private String department;

    @Column(name = "BRANCH")
    private String branch;

    @Column(name = "CENTRAL_SQUARE")
    private String centralSquare;

    @Column(name = "APPLICATION_STATUS")
    private String applicationStatus;

    @Column(name = "CLIENT_NAME")
    private String clientName;

    @Column(name = "CLIENT_INN")
    private String clientInn;

    @Column(name = "CLIENT_SEGMENT")
    private String clientSegment;

    @Column(name = "DATE_MODIFIED")
    private Timestamp modifiedDate;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "APP_LIST_ID")
    private List<ApplicationStatusUserEntity> users;

    @OneToOne(mappedBy = "application", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private AdditionalApplicationInfoEntity additionalInfo;
}
