package ru.digitalspirit.asaka.microloan.dto;

import io.swagger.annotations.ApiModel;
import lombok.ToString;
import java.math.BigInteger;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * This DTO class was generated automatically
 */
@ApiModel("Валюта (microzime)")
@ToString
public class CurrencyTypeDTO {

	private BigInteger Id;
	private String NumCurrencyCode;
	private String CharCurrencyCode;
	private String CurrencyName;

	@JsonProperty("id")
	@ApiModelProperty("Идентификатор записи в NFO")
	public BigInteger getId() {
		return Id;
	}

	public void setId(BigInteger Id) {
		this.Id = Id;
	}

	@JsonProperty("NumCurrencyCode")
	@ApiModelProperty("Цифровой код валюты по ISO-4217")
	public String getNumCurrencyCode() {
		return NumCurrencyCode;
	}

	public void setNumCurrencyCode(String NumCurrencyCode) {
		this.NumCurrencyCode = NumCurrencyCode;
	}

	@JsonProperty("CharCurrencyCode")
	@ApiModelProperty("Буквенный код валюты по ISO-4217")
	public String getCharCurrencyCode() {
		return CharCurrencyCode;
	}

	public void setCharCurrencyCode(String CharCurrencyCode) {
		this.CharCurrencyCode = CharCurrencyCode;
	}

	@JsonProperty("CurrencyName")
	@ApiModelProperty("Наименование валюты")
	public String getCurrencyName() {
		return CurrencyName;
	}

	public void setCurrencyName(String CurrencyName) {
		this.CurrencyName = CurrencyName;
	}
}