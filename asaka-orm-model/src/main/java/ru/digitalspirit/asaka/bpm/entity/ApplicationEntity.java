package ru.digitalspirit.asaka.bpm.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "APPLICATION")
public class ApplicationEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "APPLICATIONID", length = 36)
    private String applicationID;
    @Column(name = "CLAIM_NUM_BPM")
    private String claimNumBpm;
    @Column(name = "CLAIM_NUM_CRM")
    private String ClaimNumCrm;
    @Column(name = "BRANCH")
    private String Branch;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "CLIME_DATE")
    private Timestamp claimDate;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = ClientEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENTID", unique = true)
    private ClientEntity client;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = LoanEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "LOANID", unique = true)
    private LoanEntity loan;
    @OneToMany(targetEntity = AdditionalContactEntity.class, cascade = { CascadeType.ALL }, orphanRemoval = true)
    @JoinColumn(name = "APPLICATION_ID")
    private List<AdditionalContactEntity> additionalContacts;
    @Column(name = "MANAGER_NAME")
    private String managerName;
    @OneToMany(targetEntity = ApplicationHistoryEntity.class, cascade = { CascadeType.ALL }, orphanRemoval = true)
    @JoinColumn(name = "APPLICATION_ID")
    private List<ApplicationHistoryEntity> applicationHistory;
    @OneToMany(targetEntity = AttachedDocumentEntity.class, cascade = { CascadeType.ALL }, orphanRemoval = true)
    @JoinColumn(name = "APPLICATION_ID")
    private List<AttachedDocumentEntity> attachedDocuments;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ApplicationEntity that = (ApplicationEntity) o;

        return applicationID != null ? applicationID.equals(that.applicationID) : that.applicationID == null;
    }

    @Override
    public int hashCode() {
        return applicationID != null ? applicationID.hashCode() : 0;
    }
}
