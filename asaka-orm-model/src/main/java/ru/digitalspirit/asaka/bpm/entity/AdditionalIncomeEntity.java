package ru.digitalspirit.asaka.bpm.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

@Getter
@Setter
@Builder
@Entity
@Table(name = "ADDITIONAL_INCOME")
public class AdditionalIncomeEntity {

    @Id
    @GeneratedValue(generator = "SEQ_ADDINCOME")
    @SequenceGenerator(name = "SEQ_ADDINCOME", sequenceName = "SEQ_ADDINCOME", allocationSize = 1)
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "SUM", precision = 19)
    private BigDecimal sum;
    @Column(name = "INCOME_TYPE")
    private String incomeType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdditionalIncomeEntity that = (AdditionalIncomeEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
