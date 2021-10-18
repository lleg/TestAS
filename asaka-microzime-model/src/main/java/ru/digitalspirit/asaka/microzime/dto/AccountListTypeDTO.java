package ru.digitalspirit.asaka.microzime.dto;

import io.swagger.annotations.ApiModel;
import lombok.ToString;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * This DTO class was generated automatically
 */
@ApiModel("Список платежных поручений (microzime)")
@ToString
public class AccountListTypeDTO {

	private List<AccountTypeDTO> Account;
	private String BeneficiaryName;
	private String BeneficiaryINN;
	private String BeneficiaryResidentCountryID;

	@JsonProperty("Account")
	@ApiModelProperty("Одна запись об атрибутах платежного поручения")
	public List<AccountTypeDTO> getAccount() {
		return Account;
	}

	public void setAccount(List<AccountTypeDTO> Account) {
		this.Account = Account;
	}

	@JsonProperty("BeneficiaryName")
	@ApiModelProperty("Получатель")
	public String getBeneficiaryName() {
		return BeneficiaryName;
	}

	public void setBeneficiaryName(String BeneficiaryName) {
		this.BeneficiaryName = BeneficiaryName;
	}

	@JsonProperty("BeneficiaryINN")
	@ApiModelProperty("ИНН получателя")
	public String getBeneficiaryINN() {
		return BeneficiaryINN;
	}

	public void setBeneficiaryINN(String BeneficiaryINN) {
		this.BeneficiaryINN = BeneficiaryINN;
	}

	@JsonProperty("BeneficiaryResidentCountryID")
	@ApiModelProperty("Страна резидентности получателя")
	public String getBeneficiaryResidentCountryID() {
		return BeneficiaryResidentCountryID;
	}

	public void setBeneficiaryResidentCountryID(
			String BeneficiaryResidentCountryID) {
		this.BeneficiaryResidentCountryID = BeneficiaryResidentCountryID;
	}
}