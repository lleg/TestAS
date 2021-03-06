package ru.digitalspirit.asaka.microloan.dto;

import io.swagger.annotations.ApiModel;
import lombok.ToString;
import java.math.BigInteger;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

/**
 * This DTO class was generated automatically
 */
@ApiModel("Информация о дополнительных кредитах (microzime)")
@ToString
public class CurrentLoanTypeDTO {

	private BigInteger Id;
	private BigDecimal Sum;
	private BigDecimal MonthlyPayment;
	private String CreditBank;

	@JsonProperty("id")
	@ApiModelProperty("Идентификатор")
	public BigInteger getId() {
		return Id;
	}

	public void setId(BigInteger Id) {
		this.Id = Id;
	}

	@JsonProperty("Sum")
	@ApiModelProperty("Размер кредита")
	public BigDecimal getSum() {
		return Sum;
	}

	public void setSum(BigDecimal Sum) {
		this.Sum = Sum;
	}

	@JsonProperty("MonthlyPayment")
	@ApiModelProperty("Ежемесячный платеж")
	public BigDecimal getMonthlyPayment() {
		return MonthlyPayment;
	}

	public void setMonthlyPayment(BigDecimal MonthlyPayment) {
		this.MonthlyPayment = MonthlyPayment;
	}

	@JsonProperty("CreditBank")
	@ApiModelProperty("Наименование Банка-кредитора ")
	public String getCreditBank() {
		return CreditBank;
	}

	public void setCreditBank(String CreditBank) {
		this.CreditBank = CreditBank;
	}
}