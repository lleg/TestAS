package ru.digitalspirit.asaka.bpm.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@Entity
@Table(name = "APPLICATION")
public class ApplicationEntity {

    @Id
    @GeneratedValue(generator = "SEQ_APPLICATION")
    @SequenceGenerator(name = "SEQ_APPLICATION", sequenceName = "SEQ_APPLICATION", allocationSize = 1)
    @Column(name = "APPLICATION_ID")
    private BigInteger applicationID;
    @Column(name = "CLAIM_NUM_BPM")
    private String claimNumBpm;
    @Column(name = "CLAIM_NUM_CRM")
    private String ClaimNumCrm;
    @Column(name = "BRANCH")
    private String branch;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "DESICION")
    private String desicion;
    @Column(name = "CLIME_DATE")
    private Timestamp claimDate;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = ClientEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENT_ID", unique = true)
    private ClientEntity client;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = LoanEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "LOAN_ID", unique = true)
    private LoanEntity loan;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = InsuranceEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "INSURANCE_ID", unique = true)
    private InsuranceEntity insurance;
    @OneToMany(targetEntity = AdditionalContactEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "APPLICATION_ID")
    private List<AdditionalContactEntity> additionalContacts;
    @Column(name = "MANAGER_NAME")
    private String managerName;
    @OneToMany(targetEntity = ApplicationHistoryEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "APPLICATION_ID")
    private List<ApplicationHistoryEntity> applicationHistory;
    @OneToMany(targetEntity = AttachedDocumentEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "APPLICATION_ID")
    private List<AttachedDocumentEntity> attachedDocuments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationEntity that = (ApplicationEntity) o;
        return applicationID.equals(that.applicationID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationID);
    }
}
