package ru.digitalspirit.asaka.microzime.dto;

import io.swagger.annotations.ApiModel;
import lombok.ToString;
import java.math.BigInteger;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * This DTO class was generated automatically
 */
@ApiModel("Телефон (microzime)")
@ToString
public class PhoneTypeDTO {

	private BigInteger Id;
	private String Type;
	private String CountryPrefix;
	private String Number;
	private String AddPhoneNumber;
	private String PhoneStatus;
	private Boolean PrimaryPhone;

	@JsonProperty("id")
	@ApiModelProperty("Идентификатор телефона")
	public BigInteger getId() {
		return Id;
	}

	public void setId(BigInteger Id) {
		this.Id = Id;
	}

	@JsonProperty("Type")
	@ApiModelProperty("Код типа телефона")
	public String getType() {
		return Type;
	}

	public void setType(String Type) {
		this.Type = Type;
	}

	@JsonProperty("CountryPrefix")
	@ApiModelProperty("Код страны")
	public String getCountryPrefix() {
		return CountryPrefix;
	}

	public void setCountryPrefix(String CountryPrefix) {
		this.CountryPrefix = CountryPrefix;
	}

	@JsonProperty("Number")
	@ApiModelProperty("Номер телефона")
	public String getNumber() {
		return Number;
	}

	public void setNumber(String Number) {
		this.Number = Number;
	}

	@JsonProperty("AddPhoneNumber")
	@ApiModelProperty("Добавочный номер телефона")
	public String getAddPhoneNumber() {
		return AddPhoneNumber;
	}

	public void setAddPhoneNumber(String AddPhoneNumber) {
		this.AddPhoneNumber = AddPhoneNumber;
	}

	@JsonProperty("PhoneStatus")
	@ApiModelProperty("Статус телефона")
	public String getPhoneStatus() {
		return PhoneStatus;
	}

	public void setPhoneStatus(String PhoneStatus) {
		this.PhoneStatus = PhoneStatus;
	}

	@JsonProperty("PrimaryPhone")
	@ApiModelProperty("Признак главного телефона")
	public Boolean getPrimaryPhone() {
		return PrimaryPhone;
	}

	public void setPrimaryPhone(Boolean PrimaryPhone) {
		this.PrimaryPhone = PrimaryPhone;
	}
}