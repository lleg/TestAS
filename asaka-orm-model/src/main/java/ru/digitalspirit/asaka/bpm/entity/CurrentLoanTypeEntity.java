package ru.digitalspirit.asaka.bpm.entity;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CURRENT_LOAN")
public class CurrentLoanTypeEntity {
    @Id
    @GeneratedValue(generator = "SEQ_CURRENT_LOAN")
    @SequenceGenerator(name = "SEQ_CURRENT_LOAN", sequenceName = "SEQ_CURRENT_LOAN", allocationSize = 1)
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "SUM", precision = 19)
    private BigDecimal sum;
    @Column(name = "MONTHLY_PAYMENT", precision = 19)
    private BigDecimal monthlyPayment;
    @Column(name = "CREDIT_BANK")
    private String creditBank;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentLoanTypeEntity that = (CurrentLoanTypeEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
