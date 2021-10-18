package ru.digitalspirit.asaka.microzime.dto;

import io.swagger.annotations.ApiModel;
import lombok.ToString;
import java.math.BigInteger;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * This DTO class was generated automatically
 */
@ApiModel("Информация о дополнительных контактах (microzime)")
@ToString
public class AdditionalContactTypeDTO {

	private BigInteger Id;
	private String FIO;
	private String RelationType;
	private String Birthdate;
	private String Phone;

	@JsonProperty("id")
	@ApiModelProperty("Идентификатор")
	public BigInteger getId() {
		return Id;
	}

	public void setId(BigInteger Id) {
		this.Id = Id;
	}

	@JsonProperty("FIO")
	@ApiModelProperty("ФИО лица")
	public String getFIO() {
		return FIO;
	}

	public void setFIO(String FIO) {
		this.FIO = FIO;
	}

	@JsonProperty("RelationType")
	@ApiModelProperty("Отношение к заемщику")
	public String getRelationType() {
		return RelationType;
	}

	public void setRelationType(String RelationType) {
		this.RelationType = RelationType;
	}

	@JsonProperty("Birthdate")
	@ApiModelProperty("Дата рождения")
	public String getBirthdate() {
		return Birthdate;
	}

	public void setBirthdate(String Birthdate) {
		this.Birthdate = Birthdate;
	}

	@JsonProperty("Phone")
	@ApiModelProperty("Номер мобильного телефона")
	public String getPhone() {
		return Phone;
	}

	public void setPhone(String Phone) {
		this.Phone = Phone;
	}
}