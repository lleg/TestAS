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
@ApiModel("Сведения о страховке (microzime)")
@ToString
public class InsuranceTypeDTO {

	private BigInteger Id;
	private String InsuranceCompany;
	private Boolean PremiumFromCreditFunds;
	private Boolean PremiumFromOwnFunds;
	private BigDecimal PremiumSum;
	private String Liability;
	private String ContractId;
	private String PaymentDate;
	private String PolicySeries;
	private String PolicyNumber;

	@JsonProperty("id")
	@ApiModelProperty("Идентификатор записи в BPM")
	public BigInteger getId() {
		return Id;
	}

	public void setId(BigInteger Id) {
		this.Id = Id;
	}

	@JsonProperty("InsuranceCompany")
	@ApiModelProperty("Страховая компания")
	public String getInsuranceCompany() {
		return InsuranceCompany;
	}

	public void setInsuranceCompany(String InsuranceCompany) {
		this.InsuranceCompany = InsuranceCompany;
	}

	@JsonProperty("PremiumFromCreditFunds")
	@ApiModelProperty("Оплата страховой премии из кредитных средств")
	public Boolean getPremiumFromCreditFunds() {
		return PremiumFromCreditFunds;
	}

	public void setPremiumFromCreditFunds(Boolean PremiumFromCreditFunds) {
		this.PremiumFromCreditFunds = PremiumFromCreditFunds;
	}

	@JsonProperty("PremiumFromOwnFunds")
	@ApiModelProperty("Оплата страховой премии из собственных средств")
	public Boolean getPremiumFromOwnFunds() {
		return PremiumFromOwnFunds;
	}

	public void setPremiumFromOwnFunds(Boolean PremiumFromOwnFunds) {
		this.PremiumFromOwnFunds = PremiumFromOwnFunds;
	}

	@JsonProperty("PremiumSum")
	@ApiModelProperty("Сумма страховой премии")
	public BigDecimal getPremiumSum() {
		return PremiumSum;
	}

	public void setPremiumSum(BigDecimal PremiumSum) {
		this.PremiumSum = PremiumSum;
	}

	@JsonProperty("Liability")
	@ApiModelProperty("Страховая ответственность")
	public String getLiability() {
		return Liability;
	}

	public void setLiability(String Liability) {
		this.Liability = Liability;
	}

	@JsonProperty("ContractId")
	@ApiModelProperty("Идентификатор договора страхования в СК")
	public String getContractId() {
		return ContractId;
	}

	public void setContractId(String ContractId) {
		this.ContractId = ContractId;
	}

	@JsonProperty("PaymentDate")
	@ApiModelProperty("Дата оплаты страховой премии")
	public String getPaymentDate() {
		return PaymentDate;
	}

	public void setPaymentDate(String PaymentDate) {
		this.PaymentDate = PaymentDate;
	}

	@JsonProperty("PolicySeries")
	@ApiModelProperty("Серия полиса")
	public String getPolicySeries() {
		return PolicySeries;
	}

	public void setPolicySeries(String PolicySeries) {
		this.PolicySeries = PolicySeries;
	}

	@JsonProperty("PolicyNumber")
	@ApiModelProperty("Номер полиса")
	public String getPolicyNumber() {
		return PolicyNumber;
	}

	public void setPolicyNumber(String PolicyNumber) {
		this.PolicyNumber = PolicyNumber;
	}
}