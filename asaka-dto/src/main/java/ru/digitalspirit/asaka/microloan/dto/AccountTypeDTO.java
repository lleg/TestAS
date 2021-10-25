package ru.digitalspirit.asaka.microloan.dto;

import io.swagger.annotations.ApiModel;
import lombok.ToString;
import java.math.BigInteger;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

/**
 * This DTO class was generated automatically
 */
@ApiModel("Параметры платежного поручения (microzime)")
@ToString
public class AccountTypeDTO {

	private BigInteger Id;
	private Boolean IsRosbank;
	private Boolean UseBrokerAccount;
	private String Number;
	private CurrencyTypeDTO Currency;
	private String BeneficiaryBankBIC;
	private String BeneficiaryBankSWIFT;
	private String BeneficiaryBankName;
	private String BeneficiaryBankINN;
	private String BeneficiaryBankKPP;
	private String CorrespondentAccount;
	private String BeneficiaryBankCA;
	private List<String> CorrespondentBankBIC;

	@JsonProperty("id")
	@ApiModelProperty("Идентификатор записи в NFO")
	public BigInteger getId() {
		return Id;
	}

	public void setId(BigInteger Id) {
		this.Id = Id;
	}

	@JsonProperty("isRosbank")
	@ApiModelProperty("Счет Росбанка")
	public Boolean getIsRosbank() {
		return IsRosbank;
	}

	public void setIsRosbank(Boolean IsRosbank) {
		this.IsRosbank = IsRosbank;
	}

	@JsonProperty("UseBrokerAccount")
	@ApiModelProperty("Использовать брокерский счет")
	public Boolean getUseBrokerAccount() {
		return UseBrokerAccount;
	}

	public void setUseBrokerAccount(Boolean UseBrokerAccount) {
		this.UseBrokerAccount = UseBrokerAccount;
	}

	@JsonProperty("Number")
	@ApiModelProperty("Номер счета")
	public String getNumber() {
		return Number;
	}

	public void setNumber(String Number) {
		this.Number = Number;
	}

	@JsonProperty("Currency")
	@ApiModelProperty("Наименование валюты")
	public CurrencyTypeDTO getCurrency() {
		return Currency;
	}

	public void setCurrency(CurrencyTypeDTO Currency) {
		this.Currency = Currency;
	}

	@JsonProperty("BeneficiaryBankBIC")
	@ApiModelProperty("БИК банк получателя")
	public String getBeneficiaryBankBIC() {
		return BeneficiaryBankBIC;
	}

	public void setBeneficiaryBankBIC(String BeneficiaryBankBIC) {
		this.BeneficiaryBankBIC = BeneficiaryBankBIC;
	}

	@JsonProperty("BeneficiaryBankSWIFT")
	@ApiModelProperty("Банк получателя SWIFT BIC")
	public String getBeneficiaryBankSWIFT() {
		return BeneficiaryBankSWIFT;
	}

	public void setBeneficiaryBankSWIFT(String BeneficiaryBankSWIFT) {
		this.BeneficiaryBankSWIFT = BeneficiaryBankSWIFT;
	}

	@JsonProperty("BeneficiaryBankName")
	@ApiModelProperty("Наименование банка-получателя")
	public String getBeneficiaryBankName() {
		return BeneficiaryBankName;
	}

	public void setBeneficiaryBankName(String BeneficiaryBankName) {
		this.BeneficiaryBankName = BeneficiaryBankName;
	}

	@JsonProperty("BeneficiaryBankINN")
	@ApiModelProperty("ИНН Банка-получателя")
	public String getBeneficiaryBankINN() {
		return BeneficiaryBankINN;
	}

	public void setBeneficiaryBankINN(String BeneficiaryBankINN) {
		this.BeneficiaryBankINN = BeneficiaryBankINN;
	}

	@JsonProperty("BeneficiaryBankKPP")
	@ApiModelProperty("КПП Банка-получателя")
	public String getBeneficiaryBankKPP() {
		return BeneficiaryBankKPP;
	}

	public void setBeneficiaryBankKPP(String BeneficiaryBankKPP) {
		this.BeneficiaryBankKPP = BeneficiaryBankKPP;
	}

	@JsonProperty("CorrespondentAccount")
	@ApiModelProperty("Корреспондентский счет в ЦБ")
	public String getCorrespondentAccount() {
		return CorrespondentAccount;
	}

	public void setCorrespondentAccount(String CorrespondentAccount) {
		this.CorrespondentAccount = CorrespondentAccount;
	}

	@JsonProperty("BeneficiaryBankCA")
	@ApiModelProperty("Корреспондентский счет в Банке-корреспонденте")
	public String getBeneficiaryBankCA() {
		return BeneficiaryBankCA;
	}

	public void setBeneficiaryBankCA(String BeneficiaryBankCA) {
		this.BeneficiaryBankCA = BeneficiaryBankCA;
	}

	@JsonProperty("CorrespondentBankBIC")
	@ApiModelProperty("Банк-корреспондент Банка получателя SWIFT BIC")
	public List<String> getCorrespondentBankBIC() {
		return CorrespondentBankBIC;
	}

	public void setCorrespondentBankBIC(List<String> CorrespondentBankBIC) {
		this.CorrespondentBankBIC = CorrespondentBankBIC;
	}
}