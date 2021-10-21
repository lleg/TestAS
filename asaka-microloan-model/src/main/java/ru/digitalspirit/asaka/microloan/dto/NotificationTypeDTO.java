package ru.digitalspirit.asaka.microloan.dto;

import io.swagger.annotations.ApiModel;
import lombok.ToString;
import java.math.BigInteger;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * This DTO class was generated automatically
 */
@ApiModel("Уведомления на Email (microzime)")
@ToString
public class NotificationTypeDTO {

	private BigInteger Id;
	private EmailTypeDTO Email;
	private EmailTypeDTO ExtraEmail;
	private String Frequency;

	@JsonProperty("id")
	@ApiModelProperty("Идентификатор уведомления")
	public BigInteger getId() {
		return Id;
	}

	public void setId(BigInteger Id) {
		this.Id = Id;
	}

	@JsonProperty("Email")
	@ApiModelProperty("Адрес электронной почты")
	public EmailTypeDTO getEmail() {
		return Email;
	}

	public void setEmail(EmailTypeDTO Email) {
		this.Email = Email;
	}

	@JsonProperty("ExtraEmail")
	@ApiModelProperty("Дополнительный адрес электронной почты")
	public EmailTypeDTO getExtraEmail() {
		return ExtraEmail;
	}

	public void setExtraEmail(EmailTypeDTO ExtraEmail) {
		this.ExtraEmail = ExtraEmail;
	}

	@JsonProperty("Frequency")
	@ApiModelProperty("Частота уведомления")
	public String getFrequency() {
		return Frequency;
	}

	public void setFrequency(String Frequency) {
		this.Frequency = Frequency;
	}
}