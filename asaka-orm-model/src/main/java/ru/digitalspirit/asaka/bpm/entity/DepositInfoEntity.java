package ru.digitalspirit.asaka.bpm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@Entity
@Table(name = "DEPOSIT_INFO")
public class DepositInfoEntity {

    @Id
    @GeneratedValue(generator = "SEQ_DEPOSIT_INFO")
    @SequenceGenerator(name = "SEQ_DEPOSIT_INFO", sequenceName = "SEQ_DEPOSIT_INFO", allocationSize = 1)
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "SUM")
    private BigDecimal sum;
    @Column(name = "BANK")
    private String bank;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DepositInfoEntity that = (DepositInfoEntity) o;

        return id != null ? id.equals(that) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}



