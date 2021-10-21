package ru.digitalspirit.asaka.bpm.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@Builder
@Entity
@Table(name = "ADDITIONAL_CONTACT")
public class AdditionalContactEntity {
    @Id
    @GeneratedValue(generator = "SEQ_ADDITIONAL_CONTACT")
    @SequenceGenerator(name = "SEQ_ADDITIONAL_CONTACT", sequenceName = "SEQ_ADDITIONAL_CONTACT", allocationSize = 1)
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "FIO")
    private String fio;
    @Column(name = "RELATION_TYPE")
    private String relationType;
    @Column(name = "BIRTH_DATE")
    private Date birthdate;
    @Column(name = "PHONE")
    private String phone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdditionalContactEntity that = (AdditionalContactEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
