package ru.digitalspirit.asaka.bpm.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "APPLICATION")
public class ApplicationEntity {
    @Id
    @GeneratedValue(generator="SEQ_APPLICATION")
    @SequenceGenerator(name="SEQ_APPLICATION",sequenceName="SEQ_APPLICATION", allocationSize=1)
    @Column(name = "APPLICATION_ID")
    private BigInteger applicationID;
    @Column(name = "CLAIM_NUM_BPM")
    private String claimNumBpm;
    @Column(name = "CLAIM_NUM_CRM")
    private String claimNumCrm;
    @Column(name = "BRANCH")
    private String branch;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "DESICION")
    private String decision;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = CommentTypeEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "VERIFICATION_COMMENT_ID", unique = true)
    private CommentTypeEntity verificationComment;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = CommentTypeEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "CREDIT_ADMINISTRATOR_COMMENT_ID", unique = true)
    private CommentTypeEntity creditAdministratorComment;
    @Column(name = "CLIME_DATE")
    private Timestamp claimDate;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = ClientTypeEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENT_ID", unique = true)
    private ClientTypeEntity client;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = LoanTypeEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "LOAN_ID", unique = true)
    private LoanTypeEntity loan;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = InsuranceTypeEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "INSURANCE_ID", unique = true)
    private InsuranceTypeEntity insurance;
    @OneToMany(targetEntity = AdditionalContactTypeEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "APPLICATION_ID")
    private List<AdditionalContactTypeEntity> additionalContact;
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
