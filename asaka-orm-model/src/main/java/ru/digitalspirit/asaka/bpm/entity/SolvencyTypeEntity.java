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
@Table(name = "SOLVENCY_TYPE")
public class SolvencyTypeEntity {
    @Id
    @GeneratedValue(generator = "SEQ_SOLVENCY_TYPE")
    @SequenceGenerator(name = "SEQ_SOLVENCY_TYPE", sequenceName = "SEQ_SOLVENCY_TYPE", allocationSize = 1)
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "LOAN_TERM_NAME")
    private String loanTermName;
    @Column(name = "LOAN_TERM")
    private String loanTerm;
    @Column(name = "LOAN_TERM_INTERVAL_NAME")
    private String loanTermIntervalName;
    @Column(name = "LOAN_TERM_INTERVAL")
    private String loanTermInterval;
    @Column(name = "CONTRACT_END_DATE")
    private Date contractEndDate;
    @Column(name = "INTEREST_RATE")
    private BigDecimal interestRate;
    @Column(name = "TERM")
    private Integer term;
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Column(name = "TYPE_OFFER_TEXT")
    private String typeOfferText;
    @Column(name = "TYPE_OFFER_CODE")
    private String typeOfferCode;
    @Column(name = "COBORROWER")
    private String coborrower;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolvencyTypeEntity that = (SolvencyTypeEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
