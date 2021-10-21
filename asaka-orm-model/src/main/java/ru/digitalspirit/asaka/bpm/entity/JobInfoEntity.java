package ru.digitalspirit.asaka.bpm.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@Builder
@Entity
@Table(name = "JOB_INFO")
public class JobInfoEntity {
    @Id
    @GeneratedValue(generator = "SEQ_JOB_INFO")
    @SequenceGenerator(name = "SEQ_JOB_INFO", sequenceName = "SEQ_JOB_INFO", allocationSize = 1)
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "EMPLOYER_NAME")
    private String employerName;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "INN")
    private String inn;
    @Column(name = "EMPLOYER_ACTIVITY_TYPE")
    private String employerActivityType;
    @Column(name = "POSITION")
    private String position;
    @Column(name = "START_JOB_DATE")
    private Date startJobDate;
    @Column(name = "LAST_JOB_EXPERIENCE")
    private Integer lastJobExperienceMonths;
    @Column(name = "TOTAL_JOB_EXPERIENCE")
    private Integer totalJobExperienceMonths;
    @Column(name = "MONTHLY_SALARY", precision = 19)
    private BigDecimal monthlySalary;
    @Column(name = "BANK_SALARY")
    private String bankSalary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobInfoEntity that = (JobInfoEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
