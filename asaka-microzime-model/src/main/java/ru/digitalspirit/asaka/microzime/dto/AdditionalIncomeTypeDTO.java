package ru.digitalspirit.asaka.microzime.dto;

import io.swagger.annotations.ApiModel;
import lombok.ToString;
import java.math.BigInteger;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

/**
 * This DTO class was generated automatically
 */
@ApiModel("Дополнительный доход (microzime)")
@ToString
public class AdditionalIncomeTypeDTO {

	private BigInteger Id;
	private BigDecimal Sum;
	private String IncomeType;

	@JsonProperty("id")
	@ApiModelProperty("Идентификатор")
	public BigInteger getId() {
		return Id;
	}

	public void setId(BigInteger Id) {
		this.Id = Id;
	}

	@JsonProperty("sum")
	@ApiModelProperty("Размер дополнительного дохода")
	public BigDecimal getSum() {
		return Sum;
	}

	public void setSum(BigDecimal Sum) {
		this.Sum = Sum;
	}

	@JsonProperty("IncomeType")
	@ApiModelProperty("Источник дополнительного дохода")
	public String getIncomeType() {
		return IncomeType;
	}

	public void setIncomeType(String IncomeType) {
		this.IncomeType = IncomeType;
	}
}