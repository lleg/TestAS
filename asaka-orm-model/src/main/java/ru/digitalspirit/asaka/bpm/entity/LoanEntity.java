package ru.digitalspirit.asaka.bpm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "LOAN")
public class LoanEntity {

    @Id
    @GeneratedValue(generator = "SEQ_LOAN")
    @SequenceGenerator(name = "SEQ_LOAN", sequenceName = "SEQ_LOAN", allocationSize = 1)
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "LOAN_PRODUCT")
    private String loanProduct;
    @Column(name = "PRODUCTID")
    private String productId;
    @Column(name = "SUM")
    private BigDecimal sum;
    @Column(name = "LOAN_PUPORSE")
    private String loanPurpose;
    @Column(name = "TERM_IN_MONTH")
    private Integer termInMonth;
    @Column(name = "INTEREST_RATE")
    private BigDecimal interestRate;
    @Column(name = "REPAYMENT_DATE")
    private Date repaymentDate;
    @Column(name = "REPAYMENT_TYPE")
    private String repaymentType;
    @Column(name = "COLLATERAL_TYPE")
    private String collateralType;
    @Column(name = "REPAYMENT_FROM_CREDIT_FUNDS")
    private Boolean paymentFromCreditFunds;
    @Column(name = "REPAYMENT_FROM_OWN_FUNDS")
    private Boolean paymentFromOwnFunds;
    @Column(name = "CURRENCY")
    private String currency;
    @Column(name = "MIN_INTEREST_RATE")
    private BigDecimal minInterestRate;
    @Column(name = "MAX_INTEREST_RATE")
    private BigDecimal maxInterestRate;
    @Column(name = "FACILITATE_FOR_PAYMENT_DATE")
    private Boolean facilitiesForRepaymentDate;
    @Column(name = "MAX_DEF_REPAYMENT_PERIOD")
    private Integer maxDeferralRepaymentPeriod;
    @Column(name = "INITIAL_PAYMENT")
    private BigDecimal initialPayment;
    @Column(name = "MIN_INITIAL_PAYMENT")
    private BigDecimal minInitialPayment;
    @Column(name = "MAX_INITIAL_PAYMENT")
    private BigDecimal maxInitialPayment;
    @Column(name = "CRED_TYPE")
    private String creditType;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LoanEntity that = (LoanEntity) o;

        return id != null ? id.equals(that) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
