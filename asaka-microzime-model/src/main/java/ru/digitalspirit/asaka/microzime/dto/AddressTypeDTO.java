package ru.digitalspirit.asaka.microzime.dto;

import io.swagger.annotations.ApiModel;
import lombok.ToString;
import java.math.BigInteger;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * This DTO class was generated automatically
 */
@ApiModel("Адрес (microzime)")
@ToString
public class AddressTypeDTO {

	private BigInteger Id;
	private String AddressType;
	private String Format;
	private String ActiveFlag;
	private String RegionCode;
	private String Region;
	private String RegionType;
	private String DistrictType;
	private String DistrictCode;
	private String CityType;
	private String CityCode;
	private String SettlementType;
	private String SettlementCode;
	private String StreetType;
	private String StreetCode;
	private String HouseType;
	private String HouseCode;
	private String FlatCode;
	private String Country;
	private String PostalCode;
	private String State;
	private String District;
	private String City;
	private String Settlement;
	private String Street;
	private String House;
	private String Building;
	private String Apartment;
	private String AddressLine;

	@JsonProperty("id")
	@ApiModelProperty("Идентификатор адреса")
	public BigInteger getId() {
		return Id;
	}

	public void setId(BigInteger Id) {
		this.Id = Id;
	}

	@JsonProperty("AddressType")
	@ApiModelProperty("Тип адреса")
	public String getAddressType() {
		return AddressType;
	}

	public void setAddressType(String AddressType) {
		this.AddressType = AddressType;
	}

	@JsonProperty("Format")
	@ApiModelProperty("Формат хранения адреса")
	public String getFormat() {
		return Format;
	}

	public void setFormat(String Format) {
		this.Format = Format;
	}

	@JsonProperty("ActiveFlag")
	@ApiModelProperty("Флаг «Действующий»")
	public String getActiveFlag() {
		return ActiveFlag;
	}

	public void setActiveFlag(String ActiveFlag) {
		this.ActiveFlag = ActiveFlag;
	}

	@JsonProperty("RegionCode")
	@ApiModelProperty("Код региона")
	public String getRegionCode() {
		return RegionCode;
	}

	public void setRegionCode(String RegionCode) {
		this.RegionCode = RegionCode;
	}

	@JsonProperty("Region")
	@ApiModelProperty("Регион")
	public String getRegion() {
		return Region;
	}

	public void setRegion(String Region) {
		this.Region = Region;
	}

	@JsonProperty("RegionType")
	@ApiModelProperty("Тип региона")
	public String getRegionType() {
		return RegionType;
	}

	public void setRegionType(String RegionType) {
		this.RegionType = RegionType;
	}

	@JsonProperty("DistrictType")
	@ApiModelProperty("Тип района")
	public String getDistrictType() {
		return DistrictType;
	}

	public void setDistrictType(String DistrictType) {
		this.DistrictType = DistrictType;
	}

	@JsonProperty("DistrictCode")
	@ApiModelProperty("Код района")
	public String getDistrictCode() {
		return DistrictCode;
	}

	public void setDistrictCode(String DistrictCode) {
		this.DistrictCode = DistrictCode;
	}

	@JsonProperty("CityType")
	@ApiModelProperty("Тип города")
	public String getCityType() {
		return CityType;
	}

	public void setCityType(String CityType) {
		this.CityType = CityType;
	}

	@JsonProperty("CityCode")
	@ApiModelProperty("Код города")
	public String getCityCode() {
		return CityCode;
	}

	public void setCityCode(String CityCode) {
		this.CityCode = CityCode;
	}

	@JsonProperty("SettlementType")
	@ApiModelProperty("Тип н.п.")
	public String getSettlementType() {
		return SettlementType;
	}

	public void setSettlementType(String SettlementType) {
		this.SettlementType = SettlementType;
	}

	@JsonProperty("SettlementCode")
	@ApiModelProperty("Код населенного пункта")
	public String getSettlementCode() {
		return SettlementCode;
	}

	public void setSettlementCode(String SettlementCode) {
		this.SettlementCode = SettlementCode;
	}

	@JsonProperty("StreetType")
	@ApiModelProperty("Тип улицы")
	public String getStreetType() {
		return StreetType;
	}

	public void setStreetType(String StreetType) {
		this.StreetType = StreetType;
	}

	@JsonProperty("StreetCode")
	@ApiModelProperty("Код улицы")
	public String getStreetCode() {
		return StreetCode;
	}

	public void setStreetCode(String StreetCode) {
		this.StreetCode = StreetCode;
	}

	@JsonProperty("HouseType")
	@ApiModelProperty("Тип дома")
	public String getHouseType() {
		return HouseType;
	}

	public void setHouseType(String HouseType) {
		this.HouseType = HouseType;
	}

	@JsonProperty("HouseCode")
	@ApiModelProperty("Код дома")
	public String getHouseCode() {
		return HouseCode;
	}

	public void setHouseCode(String HouseCode) {
		this.HouseCode = HouseCode;
	}

	@JsonProperty("FlatCode")
	@ApiModelProperty("Код квартиры")
	public String getFlatCode() {
		return FlatCode;
	}

	public void setFlatCode(String FlatCode) {
		this.FlatCode = FlatCode;
	}

	@JsonProperty("Country")
	@ApiModelProperty("Код страны")
	public String getCountry() {
		return Country;
	}

	public void setCountry(String Country) {
		this.Country = Country;
	}

	@JsonProperty("PostalCode")
	@ApiModelProperty("Почтовый индекс")
	public String getPostalCode() {
		return PostalCode;
	}

	public void setPostalCode(String PostalCode) {
		this.PostalCode = PostalCode;
	}

	@JsonProperty("State")
	@ApiModelProperty("Область")
	public String getState() {
		return State;
	}

	public void setState(String State) {
		this.State = State;
	}

	@JsonProperty("District")
	@ApiModelProperty("Район")
	public String getDistrict() {
		return District;
	}

	public void setDistrict(String District) {
		this.District = District;
	}

	@JsonProperty("City")
	@ApiModelProperty("Город")
	public String getCity() {
		return City;
	}

	public void setCity(String City) {
		this.City = City;
	}

	@JsonProperty("Settlement")
	@ApiModelProperty("Населенный пункт")
	public String getSettlement() {
		return Settlement;
	}

	public void setSettlement(String Settlement) {
		this.Settlement = Settlement;
	}

	@JsonProperty("Street")
	@ApiModelProperty("Улица")
	public String getStreet() {
		return Street;
	}

	public void setStreet(String Street) {
		this.Street = Street;
	}

	@JsonProperty("House")
	@ApiModelProperty("Дом")
	public String getHouse() {
		return House;
	}

	public void setHouse(String House) {
		this.House = House;
	}

	@JsonProperty("Building")
	@ApiModelProperty("Корпус, строение, владение, литера")
	public String getBuilding() {
		return Building;
	}

	public void setBuilding(String Building) {
		this.Building = Building;
	}

	@JsonProperty("Apartment")
	@ApiModelProperty("Квартира/Офис")
	public String getApartment() {
		return Apartment;
	}

	public void setApartment(String Apartment) {
		this.Apartment = Apartment;
	}

	@JsonProperty("AddressLine")
	@ApiModelProperty("Адрес строкой")
	public String getAddressLine() {
		return AddressLine;
	}

	public void setAddressLine(String AddressLine) {
		this.AddressLine = AddressLine;
	}
}