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
@Table(name = "INCOME_AND_TAX_TYPE")
public class IncomeAndTaxTypeEntity {
    @Id
    @GeneratedValue(generator = "SEQ_INCOME_AND_TAX_TYPE")
    @SequenceGenerator(name = "SEQ_INCOME_AND_TAX_TYPE", sequenceName = "SEQ_INCOME_AND_TAX_TYPE", allocationSize = 1)
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "EMPLOY_NAME")
    private String EmployerName;
    @Column(name = "INN")
    private String inn;
    @Column(name = "YEAR")
    private Integer year;
    @Column(name = "MONTH")
    private Integer month;
    @Column(name = "SALARY")
    private BigDecimal salary;
    @Column(name = "SALARY_TAX_SUM")
    private BigDecimal salaryTaxSum;
    @Column(name = "INPS_SUM")
    private BigDecimal inpsSum;
    @Column(name = "PROPERTY_INCOME")
    private BigDecimal propertyIncome;
    @Column(name = "BENEFIT_INCOME")
    private BigDecimal benefitIncome;
    @Column(name = "OTHER_INCOME")
    private BigDecimal otherIncome;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncomeAndTaxTypeEntity that = (IncomeAndTaxTypeEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
