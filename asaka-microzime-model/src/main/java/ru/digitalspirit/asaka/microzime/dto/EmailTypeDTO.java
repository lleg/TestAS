package ru.digitalspirit.asaka.microzime.dto;

import io.swagger.annotations.ApiModel;
import lombok.ToString;
import java.math.BigInteger;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * This DTO class was generated automatically
 */
@ApiModel("Email (microzime)")
@ToString
public class EmailTypeDTO {

	private BigInteger Id;
	private String Type;
	private String EmailAddress;
	private String EmailStatus;
	private Boolean ContactEmail;

	@JsonProperty("id")
	@ApiModelProperty("Идентификатор Email")
	public BigInteger getId() {
		return Id;
	}

	public void setId(BigInteger Id) {
		this.Id = Id;
	}

	@JsonProperty("Type")
	@ApiModelProperty("Тип Email")
	public String getType() {
		return Type;
	}

	public void setType(String Type) {
		this.Type = Type;
	}

	@JsonProperty("EmailAddress")
	@ApiModelProperty("Адрес электронной почты")
	public String getEmailAddress() {
		return EmailAddress;
	}

	public void setEmailAddress(String EmailAddress) {
		this.EmailAddress = EmailAddress;
	}

	@JsonProperty("EmailStatus")
	@ApiModelProperty("Статус почтового ящика")
	public String getEmailStatus() {
		return EmailStatus;
	}

	public void setEmailStatus(String EmailStatus) {
		this.EmailStatus = EmailStatus;
	}

	@JsonProperty("ContactEmail")
	@ApiModelProperty("Email указан в ДКБО как контактный")
	public Boolean getContactEmail() {
		return ContactEmail;
	}

	public void setContactEmail(Boolean ContactEmail) {
		this.ContactEmail = ContactEmail;
	}
}