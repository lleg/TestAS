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
@ApiModel("Сведения о запрашиваемом кредите (microzime)")
@ToString
public class LoanTypeDTO {

	private BigInteger Id;
	private String LoanProduct;
	private String ProductId;
	private BigDecimal Sum;
	private String LoanPurpose;
	private BigInteger TermInMonth;
	private BigDecimal InterestRate;
	private String RepaymentDate;
	private String RepaymentType;
	private String CollateralType;
	private Boolean PaymentFromCreditFunds;
	private Boolean PaymentFromOwnFunds;
	private String Currency;
	private BigDecimal MinInterestRate;
	private BigDecimal MaxInterestRate;
	private Boolean FacilitiesForRepaymentDate;
	private BigInteger MaxDeferralRepaymentPeriod;
	private BigDecimal InitialPayment;
	private BigDecimal MinInitialPaymentPercent;
	private BigDecimal MaxInitialPaymentPercent;
	private String CreditType;

	@JsonProperty("id")
	@ApiModelProperty("Идентификатор записи в BPM")
	public BigInteger getId() {
		return Id;
	}

	public void setId(BigInteger Id) {
		this.Id = Id;
	}

	@JsonProperty("LoanProduct")
	@ApiModelProperty("Кредитный продукт")
	public String getLoanProduct() {
		return LoanProduct;
	}

	public void setLoanProduct(String LoanProduct) {
		this.LoanProduct = LoanProduct;
	}

	@JsonProperty("ProductId")
	@ApiModelProperty("ID продукта")
	public String getProductId() {
		return ProductId;
	}

	public void setProductId(String ProductId) {
		this.ProductId = ProductId;
	}

	@JsonProperty("Sum")
	@ApiModelProperty("Запрашиваемая сумма кредита")
	public BigDecimal getSum() {
		return Sum;
	}

	public void setSum(BigDecimal Sum) {
		this.Sum = Sum;
	}

	@JsonProperty("LoanPurpose")
	@ApiModelProperty("Цель кредитования")
	public String getLoanPurpose() {
		return LoanPurpose;
	}

	public void setLoanPurpose(String LoanPurpose) {
		this.LoanPurpose = LoanPurpose;
	}

	@JsonProperty("TermInMonth")
	@ApiModelProperty("Количество месяцев на кредит")
	public BigInteger getTermInMonth() {
		return TermInMonth;
	}

	public void setTermInMonth(BigInteger TermInMonth) {
		this.TermInMonth = TermInMonth;
	}

	@JsonProperty("InterestRate")
	@ApiModelProperty("Процентная ставка по кредиту")
	public BigDecimal getInterestRate() {
		return InterestRate;
	}

	public void setInterestRate(BigDecimal InterestRate) {
		this.InterestRate = InterestRate;
	}

	@JsonProperty("RepaymentDate")
	@ApiModelProperty("Дата погашения")
	public String getRepaymentDate() {
		return RepaymentDate;
	}

	public void setRepaymentDate(String RepaymentDate) {
		this.RepaymentDate = RepaymentDate;
	}

	@JsonProperty("RepaymentType")
	@ApiModelProperty("Тип погашения")
	public String getRepaymentType() {
		return RepaymentType;
	}

	public void setRepaymentType(String RepaymentType) {
		this.RepaymentType = RepaymentType;
	}

	@JsonProperty("CollateralType")
	@ApiModelProperty("Тип обеспечения")
	public String getCollateralType() {
		return CollateralType;
	}

	public void setCollateralType(String CollateralType) {
		this.CollateralType = CollateralType;
	}

	@JsonProperty("PaymentFromCreditFunds")
	@ApiModelProperty("Оплата страховой премии из кредитных средств")
	public Boolean getPaymentFromCreditFunds() {
		return PaymentFromCreditFunds;
	}

	public void setPaymentFromCreditFunds(Boolean PaymentFromCreditFunds) {
		this.PaymentFromCreditFunds = PaymentFromCreditFunds;
	}

	@JsonProperty("PaymentFromOwnFunds")
	@ApiModelProperty("Оплата страховой премии из собственных средств")
	public Boolean getPaymentFromOwnFunds() {
		return PaymentFromOwnFunds;
	}

	public void setPaymentFromOwnFunds(Boolean PaymentFromOwnFunds) {
		this.PaymentFromOwnFunds = PaymentFromOwnFunds;
	}

	@JsonProperty("Currency")
	@ApiModelProperty("Валюта кредита")
	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String Currency) {
		this.Currency = Currency;
	}

	@JsonProperty("MinInterestRate")
	@ApiModelProperty("Процентная ставка по кредиту (минимальная)")
	public BigDecimal getMinInterestRate() {
		return MinInterestRate;
	}

	public void setMinInterestRate(BigDecimal MinInterestRate) {
		this.MinInterestRate = MinInterestRate;
	}

	@JsonProperty("MaxInterestRate")
	@ApiModelProperty("Процентная ставка по кредиту (максимальная)")
	public BigDecimal getMaxInterestRate() {
		return MaxInterestRate;
	}

	public void setMaxInterestRate(BigDecimal MaxInterestRate) {
		this.MaxInterestRate = MaxInterestRate;
	}

	@JsonProperty("FacilitiesForRepaymentDate")
	@ApiModelProperty("Флаг наличия льгот по погашению кредита")
	public Boolean getFacilitiesForRepaymentDate() {
		return FacilitiesForRepaymentDate;
	}

	public void setFacilitiesForRepaymentDate(Boolean FacilitiesForRepaymentDate) {
		this.FacilitiesForRepaymentDate = FacilitiesForRepaymentDate;
	}

	@JsonProperty("MaxDeferralRepaymentPeriod")
	@ApiModelProperty("Максимальный срок отсрочки по продукту")
	public BigInteger getMaxDeferralRepaymentPeriod() {
		return MaxDeferralRepaymentPeriod;
	}

	public void setMaxDeferralRepaymentPeriod(
			BigInteger MaxDeferralRepaymentPeriod) {
		this.MaxDeferralRepaymentPeriod = MaxDeferralRepaymentPeriod;
	}

	@JsonProperty("InitialPayment")
	@ApiModelProperty("Первоначальный взнос")
	public BigDecimal getInitialPayment() {
		return InitialPayment;
	}

	public void setInitialPayment(BigDecimal InitialPayment) {
		this.InitialPayment = InitialPayment;
	}

	@JsonProperty("MinInitialPaymentPercent")
	@ApiModelProperty("Процент первоначального взноса (минимальный)")
	public BigDecimal getMinInitialPaymentPercent() {
		return MinInitialPaymentPercent;
	}

	public void setMinInitialPaymentPercent(BigDecimal MinInitialPaymentPercent) {
		this.MinInitialPaymentPercent = MinInitialPaymentPercent;
	}

	@JsonProperty("MaxInitialPaymentPercent")
	@ApiModelProperty("Процент первоначального взноса (максимальный)")
	public BigDecimal getMaxInitialPaymentPercent() {
		return MaxInitialPaymentPercent;
	}

	public void setMaxInitialPaymentPercent(BigDecimal MaxInitialPaymentPercent) {
		this.MaxInitialPaymentPercent = MaxInitialPaymentPercent;
	}

	@JsonProperty("CreditType")
	@ApiModelProperty("Вид кредита")
	public String getCreditType() {
		return CreditType;
	}

	public void setCreditType(String CreditType) {
		this.CreditType = CreditType;
	}
}