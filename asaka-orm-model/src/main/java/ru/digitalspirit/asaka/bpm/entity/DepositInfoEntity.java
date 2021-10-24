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
@Table(name = "DEPOSIT_INFO")
public class DepositInfoEntity {
    @Id
    @GeneratedValue(generator = "SEQ_DEPOSIT_INFO")
    @SequenceGenerator(name = "SEQ_DEPOSIT_INFO", sequenceName = "SEQ_DEPOSIT_INFO", allocationSize = 1)
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "SUM", precision = 19)
    private BigDecimal sum;
    @Column(name = "BANK")
    private String bank;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepositInfoEntity that = (DepositInfoEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}



