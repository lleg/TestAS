package ru.digitalspirit.asaka.bpm.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@Entity
@Table(name = "CURR_LOAN")
public class CurrentLoanEntity {

    @Id
    @GeneratedValue(generator = "SEQ_CURRENT_LOAN")
    @SequenceGenerator(name = "SEQ_CURRENT_LOAN", sequenceName = "SEQ_CURRENT_LOAN", allocationSize = 1)
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "SUM")
    private BigDecimal sum;
    @Column(name = "CREDIT_BANK")
    private String creditBank;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CurrentLoanEntity that = (CurrentLoanEntity) o;

        return id != null ? id.equals(that) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
