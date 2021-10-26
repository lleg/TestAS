package ru.digitalspirit.asaka.bpm.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "INSURANCE")
public class InsuranceTypeEntity {
    @Id
    @GeneratedValue(generator = "SEQ_INSURANCE")
    @SequenceGenerator(name = "SEQ_INSURANCE", sequenceName = "SEQ_INSURANCE", allocationSize = 1)
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "INSURANCE_COMPANY")
    private String insuranceCompany;
    @Column(name = "PREMIUM_FROM_CREDIT_FUNDS")
    private Boolean premiumFromCreditFunds;
    @Column(name = "PREMIUM_FROM_OWN_FUNDS")
    private Boolean premiumFromOwnFunds;
    @Column(name = "PREMIUM_SUM", precision = 19)
    private BigDecimal premiumSum;
    @Column(name = "LIABILITY")
    private String liability;
    @Column(name = "CONTRACT_ID")
    private String contractId;
    @Column(name = "PAYMENT_DATE")
    private Date paymentDate;
    @Column(name = "POLICY_SERIES")
    private String policySeries;
    @Column(name = "POLICY_NUMBER")
    private String policy_number;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsuranceTypeEntity that = (InsuranceTypeEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
