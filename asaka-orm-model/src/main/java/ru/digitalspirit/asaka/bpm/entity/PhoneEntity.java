package ru.digitalspirit.asaka.bpm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@Entity
@Table(name = "PHONE_INFO")
public class PhoneEntity {

    @Id
    @GeneratedValue(generator = "SEQ_PHONE")
    @SequenceGenerator(name = "SEQ_PHONE", sequenceName = "SEQ_PHONE", allocationSize = 1)
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "COUNTRY_PREFIX")
    private String countryPrefix;
    @Column(name = "PHONE_NUMBER")
    private String Number;
    @Column(name = "ADD_PHONE_NUMBER")
    private String addPhoneNumber;
    @Column(name = "PHONE_STATUS")
    private String phoneStatus;
    @Column(name = "IS_PHONE_NUMBER")
    private Boolean primaryPhone;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PhoneEntity that = (PhoneEntity) o;

        return id != null ? id.equals(that) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
