package ru.digitalspirit.asaka.microzime.dto;

import io.swagger.annotations.ApiModel;
import lombok.ToString;
import java.math.BigInteger;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * This DTO class was generated automatically
 */
@ApiModel("Отношение к клиенту (microzime)")
@ToString
public class TrusteeStatusTypeDTO {

	private BigInteger Id;
	private String Value;

	@JsonProperty("id")
	@ApiModelProperty("Идентификатор")
	public BigInteger getId() {
		return Id;
	}

	public void setId(BigInteger Id) {
		this.Id = Id;
	}

	@JsonProperty("value")
	@ApiModelProperty("Значение")
	public String getValue() {
		return Value;
	}

	public void setValue(String Value) {
		this.Value = Value;
	}
}