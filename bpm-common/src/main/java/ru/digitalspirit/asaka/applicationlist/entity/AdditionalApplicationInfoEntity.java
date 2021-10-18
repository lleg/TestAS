package ru.digitalspirit.asaka.applicationlist.entity;

import lombok.Data;
import ru.digitalspirit.asaka.applicationlist.model.ApplicationAddInfo;

import javax.persistence.*;

@Data
@Entity
@Table(name = "APPLICATION_ADD_INFO")
public class AdditionalApplicationInfoEntity implements ApplicationAddInfo {
    @Id
    @Column(name = "ID")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "APP_LIST_ID")
    private ApplicationStatusEntity application;

    @Column(name = "APPLICATION_ID")
    private String applicationId;

    @Column(name = "PROTOCOL_NUMBER")
    private String protocolNumber;

    @Column(name = "PROTOCOL_STATUS")
    private String protocolStatus;

    @Column(name = "PROTOCOL_COUNT_MEMBER")
    private String protocolCountMember;

    @Column(name = "PROTOCOL_TOTAL_COUNT_MEMBER")
    private String protocolTotalCountMember;

    @Transient
    private String branchCode;
}
