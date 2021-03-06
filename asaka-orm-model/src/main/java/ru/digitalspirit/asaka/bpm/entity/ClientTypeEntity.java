package ru.digitalspirit.asaka.bpm.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CLIENT")
public class ClientTypeEntity {

    @Id
    @GeneratedValue(generator = "SEQ_CLIENT")
    @SequenceGenerator(name = "SEQ_CLIENT", sequenceName = "SEQ_CLIENT", allocationSize = 1)
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "CLIENT_ABS_UID")
    private String clientUID;
    @Column(name = "CLIENT_ABS_ID")
    private String clientID;
    @Column(name = "CLIENT_CODE")
    private String clientCode;
    @Column(name = "CLIENT_CODE_CRM")
    private String clientCodeCrm;
    @Column(name = "FINAL_RISK_LEVEL")
    private String finalRiskLevel;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "PATRONYMIC")
    private String patronymic;
    @Column(name = "BIRTH_DATE")
    private Date birthDate;
    @Column(name = "BIRTH_PLACE")
    private String placeOfBirth;
    @Column(name = "GENDER")
    private String sex;
    @Column(name = "CITIZENSHIP")
    private String citizenship;
    @Column(name = "INN")
    private String inn;
    @Column(name = "PINFL")
    private String pinFl;
    @OneToMany(targetEntity = DocumentEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "CLIENT_ID")
    private List<DocumentEntity> document;
    @OneToMany(targetEntity = AddressTypeEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "CLIENT_ID")
    private List<AddressTypeEntity> address;
    @Column(name = "REG_ADDR_EQ_RES_ADDR")
    private Boolean regAddrEqualsResAddr;
    @Column(name = "IS_CO_BORROWED")
    private Boolean isCoBorrowed;
    @Column(name = "IS_GUARANTOR")
    private Boolean isGuarantor;
    @OneToMany(targetEntity = PhoneTypeEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "CLIENT_ID")
    private List<PhoneTypeEntity> phone;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = JobInfoTypeEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "JOB_INFO_ID", unique = true)
    private JobInfoTypeEntity jobInfo;
    @Column(name = "IS_ADDITIONAL_INCOME")
    private Boolean isAdditionalIncome;
    @OneToMany(targetEntity = AdditionalIncomeTypeEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "CLIENT_ID")
    private List<AdditionalIncomeTypeEntity> additionalIncome;
    @Column(name = "IS_CAR")
    private Boolean isCar;
    @Column(name = "IS_REAL_ESTATE")
    private Boolean isRealEstate;
    @OneToMany(targetEntity = DepositInfoTypeEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "CLIENT_ID")
    private List<DepositInfoTypeEntity> depositInfo;
    @Column(name = "MARITAL_STATUS")
    private String maritalStatus;
    @Column(name = "CHILDREN_NUM_0_TO_3")
    private Integer childrenNum0To3;
    @Column(name = "CHILDREN_NUM_3_TO_16")
    private Integer childrenNum3To16;
    @Column(name = "ADULT_CHILDREN_NUM")
    private Integer adultChildrenNum;
    @Column(name = "EDUCATION")
    private String education;
    @Column(name = "TYPE_OF_HOUSING")
    private String typeOfHousing;
    @Column(name = "IS_RETIREE")
    private Boolean isRetiree;
    @Column(name = "NINPS_ACCOUNT")
    private String ninpsAccount;
    @OneToMany(targetEntity = CurrentLoanTypeEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "CLIENT_ID")
    private List<CurrentLoanTypeEntity> currentLoan;
    @OneToMany(targetEntity = IncomeAndTaxTypeEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "CLIENT_ID")
    private List<IncomeAndTaxTypeEntity> incomeAndTax;
    @OneToMany(targetEntity = RelatedPersonEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "CLIENT_ID")
    private List<RelatedPersonEntity> relatedPerson;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = ScoringTypeEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "SCORING_ID", unique = true)
    private ScoringTypeEntity scoring;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientTypeEntity that = (ClientTypeEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
