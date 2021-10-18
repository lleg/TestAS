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
    private String EmployerName;
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
    @Column(name = "MONTHLY_SALARY")
    private BigDecimal monthlySalary;
    @Column(name = "BANK_SALARY")
    private String bankSalary;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JobInfoEntity that = (JobInfoEntity) o;

        return id != null ? id.equals(that) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
