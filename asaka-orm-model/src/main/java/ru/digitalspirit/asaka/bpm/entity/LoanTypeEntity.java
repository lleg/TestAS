package ru.digitalspirit.asaka.bpm.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LOAN")
public class LoanTypeEntity {

    @Id
    @GeneratedValue(generator = "SEQ_LOAN")
    @SequenceGenerator(name = "SEQ_LOAN", sequenceName = "SEQ_LOAN", allocationSize = 1)
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "LOAN_PRODUCT")
    private String loanProduct;
    @Column(name = "PRODUCT_ID")
    private String productId;
    @Column(name = "SUM", precision = 19)
    private BigDecimal sum;
    @Column(name = "LOAN_PURPOSE")
    private String loanPurpose;
    @Column(name = "TERM_IN_MONTH")
    private Integer termInMonth;
    @Column(name = "INTEREST_RATE")
    private BigDecimal interestRate;
    @Column(name = "START_DATE")
    private Date startDate;
    @Column(name = "REPAYMENT_DAY")
    private Integer repaymentDay;
    @Column(name = "REPAYMENT_TYPE")
    private String repaymentType;
    @Column(name = "COLLATERAL_TYPE")
    private String collateralType;
    @Column(name = "INSURANCE_FROM_CREDIT_FUNDS")
    private Boolean insuranceFromCreditFunds;
    @Column(name = "INSURANCE_FROM_OWN_FUNDS")
    private Boolean insuranceFromOwnFunds;
    @Column(name = "CURRENCY")
    private String currency;
    @Column(name = "MIN_INTEREST_RATE", precision = 19)
    private BigDecimal minInterestRate;
    @Column(name = "MAX_INTEREST_RATE", precision = 19)
    private BigDecimal maxInterestRate;
    @Column(name = "FACILITATE_FOR_REPAYMENT_DATE")
    private Boolean facilitiesForRepaymentDate;
    @Column(name = "MAX_DEF_REPAYMENT_PERIOD")
    private Integer maxDeferralRepaymentPeriod;
    @Column(name = "INITIAL_PAYMENT", precision = 19)
    private BigDecimal initialPayment;
    @Column(name = "MIN_INITIAL_PAYMENT", precision = 19)
    private BigDecimal minInitialPayment;
    @Column(name = "MAX_INITIAL_PAYMENT", precision = 19)
    private BigDecimal maxInitialPayment;
    @Column(name = "ISSUANCE_FORM")
    private String issuanceForm;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanTypeEntity that = (LoanTypeEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
