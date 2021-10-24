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
@Table(name = "RELATED_PERSON")
public class RelatedPersonEntity {
    @Id
    @GeneratedValue(generator = "SEQ_RELATED_PERSON")
    @SequenceGenerator(name = "SEQ_RELATED_PERSON", sequenceName = "SEQ_RELATED_PERSON", allocationSize = 1)
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "TRUSTEE_STATUS")
    private String trusteeStatus;
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
    @JoinColumn(name = "REL_PERSON_ID")
    private List<DocumentEntity> documents;
    @OneToMany(targetEntity = AddressEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "REL_PERSON_ID")
    private List<AddressEntity> addresses;
    @Column(name = "REG_ADDR_EQ_RES_ADDR")
    private Boolean regAddrEqualseResAddr;
    @Column(name = "IS_COBORROWED")
    private Boolean isCoBorrowed;
    @OneToMany(targetEntity = PhoneEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "REL_PERSON_ID")
    private List<PhoneEntity> phone;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = JobInfoEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "JOB_INFO_ID", unique = true)
    private JobInfoEntity jobInfo;
    @Column(name = "IS_ADDITIONAL_INCOME")
    private Boolean isAdditionalIncome;
    @OneToMany(targetEntity = AdditionalIncomeEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "REL_PERSON_ID")
    private List<AdditionalIncomeEntity> additionalIncome;
    @Column(name = "IS_CAR")
    private Boolean isCar;
    @Column(name = "IS_REAL_ESTATE")
    private Boolean isRealEstate;
    @OneToMany(targetEntity = DepositInfoEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "REL_PERSON_ID")
    private List<DepositInfoEntity> depositInfo;
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
    private String TypeOfHousing;
    @Column(name = "IS_RETIREE")
    private Boolean isRetiree;
    @Column(name = "NINPS_ACCOUNT_BANK")
    private String ninpsAccountBank;
    @OneToMany(targetEntity = CurrentLoanEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "REL_PERSON_ID")
    private List<CurrentLoanEntity> currentLoans;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelatedPersonEntity that = (RelatedPersonEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
