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
    @GeneratedValue(generator="SEQ_ADDRESS")
    @SequenceGenerator(name="SEQ_ADDRESS",sequenceName="SEQ_ADDRESS", allocationSize=1)
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "ADDRESSTYPE")
    private String addressType;
    @Column(name = "FORMAT")
    private String format;
    @Column(name = "ACTIVEFLAG")
    private String activeFlag;
    @Column(name = "REGIONCODE")
    private String regionCode;
    @Column(name = "REGION")
    private String region;
    @Column(name = "REGIONTYPE")
    private String regionType;
    @Column(name = "DISTRICTTYPE")
    private String districtType;
    @Column(name = "DISTRICTCODE")
    private String districtCode;
    @Column(name = "CITYTYPE")
    private String cityType;
    @Column(name = "CITYCODE")
    private String cityCode;
    @Column(name = "SETTLEMENTTYPE")
    private String settlementType;
    @Column(name = "SETTLEMENTCODE")
    private String settlementCode;
    @Column(name = "STREETTYPE")
    private String streetType;
    @Column(name = "STREETCODE")
    private String streetCode;
    @Column(name = "HOUSETYPE")
    private String houseType;
    @Column(name = "HOUSECODE")
    private String houseCode;
    @Column(name = "FLATCODE")
    private String flatCode;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "POSTALCODE")
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
    @Column(name = "ADDRESSLINE", length = 2000)
    private String addressLine;



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AddressEntity that = (AddressEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
