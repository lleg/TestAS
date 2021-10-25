package ru.digitalspirit.asaka.microloan.dto;

import io.swagger.annotations.ApiModel;
import lombok.ToString;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * This DTO class was generated automatically
 */
@ApiModel("Список email (microzime)")
@ToString
public class EmailListTypeDTO {

	private List<EmailTypeDTO> Email;

	@JsonProperty("Email")
	@ApiModelProperty("")
	public List<EmailTypeDTO> getEmail() {
		return Email;
	}

	public void setEmail(List<EmailTypeDTO> Email) {
		this.Email = Email;
	}
}