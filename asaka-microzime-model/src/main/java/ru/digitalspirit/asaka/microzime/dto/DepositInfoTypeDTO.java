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
@ApiModel("Информация о депозите (microzime)")
@ToString
public class DepositInfoTypeDTO {

	private BigInteger Id;
	private BigDecimal Sum;
	private String Bank;

	@JsonProperty("id")
	@ApiModelProperty("Идентификатор")
	public BigInteger getId() {
		return Id;
	}

	public void setId(BigInteger Id) {
		this.Id = Id;
	}

	@JsonProperty("sum")
	@ApiModelProperty("Сумма")
	public BigDecimal getSum() {
		return Sum;
	}

	public void setSum(BigDecimal Sum) {
		this.Sum = Sum;
	}

	@JsonProperty("Bank")
	@ApiModelProperty("Наименование банка")
	public String getBank() {
		return Bank;
	}

	public void setBank(String Bank) {
		this.Bank = Bank;
	}
}