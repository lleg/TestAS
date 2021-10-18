package ru.digitalspirit.asaka.microzime.dto;

import io.swagger.annotations.ApiModel;
import lombok.ToString;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * This DTO class was generated automatically
 */
@ApiModel("Список телефонов (microzime)")
@ToString
public class PhoneListTypeDTO {

	private List<PhoneTypeDTO> Phone;

	@JsonProperty("Phone")
	@ApiModelProperty("")
	public List<PhoneTypeDTO> getPhone() {
		return Phone;
	}

	public void setPhone(List<PhoneTypeDTO> Phone) {
		this.Phone = Phone;
	}
}