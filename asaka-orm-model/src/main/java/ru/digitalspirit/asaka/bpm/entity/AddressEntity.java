package ru.digitalspirit.asaka.bpm.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Getter
@Setter
@Builder
@Entity
@Table(name = "ADDRESS")
public class AddressEntity {

    @Id
    @GeneratedValue(generator = "SEQ_ADDRESS")
    @SequenceGenerator(name = "SEQ_ADDRESS", sequenceName = "SEQ_ADDRESS", allocationSize = 1)
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "ADDRESS_TYPE")
    private String addressType;
    @Column(name = "FORMAT")
    private String format;
    @Column(name = "ACTIVE_FLAG")
    private String activeFlag;
    @Column(name = "REGION_CODE")
    private String regionCode;
    @Column(name = "REGION")
    private String region;
    @Column(name = "REGION_TYPE")
    private String regionType;
    @Column(name = "DISTRICT_TYPE")
    private String districtType;
    @Column(name = "DISTRICT_CODE")
    private String districtCode;
    @Column(name = "CITY_TYPE")
    private String cityType;
    @Column(name = "CITY_CODE")
    private String cityCode;
    @Column(name = "SETTLEMENT_TYPE")
    private String settlementType;
    @Column(name = "SETTLEMENT_CODE")
    private String settlementCode;
    @Column(name = "STREET_TYPE")
    private String streetType;
    @Column(name = "STREET_CODE")
    private String streetCode;
    @Column(name = "HOUSE_TYPE")
    private String houseType;
    @Column(name = "HOUSE_CODE")
    private String houseCode;
    @Column(name = "FLAT_CODE")
    private String flatCode;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "POSTAL_CODE")
    private String postalCode;
    @Column(name = "STATE")
    private String state;
    @Column(name = "DISTRICT")
    private String district;
    @Column(name = "CITY")
    private String city;
    @Column(name = "SETTLEMENT")
    private String settlement;
    @Column(name = "STREET")
    private String street;
    @Column(name = "HOUSE")
    private String house;
    @Column(name = "BUILDING")
    private String building;
    @Column(name = "APARTMENT")
    private String apartment;
    @Column(name = "ADDRESS_LINE", length = 2000)
    private String addressLine;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
