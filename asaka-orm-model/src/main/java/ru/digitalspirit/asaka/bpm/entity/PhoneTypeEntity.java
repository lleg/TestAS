package ru.digitalspirit.asaka.bpm.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PHONE_INFO")
public class PhoneTypeEntity {
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
    private String number;
    @Column(name = "ADD_PHONE_NUMBER")
    private String addPhoneNumber;
    @Column(name = "PHONE_STATUS")
    private String phoneStatus;
    @Column(name = "PRIMARY_PHONE")
    private Boolean primaryPhone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneTypeEntity that = (PhoneTypeEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
