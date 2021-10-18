package ru.digitalspirit.asaka.microzime.dto;

import io.swagger.annotations.ApiModel;
import lombok.ToString;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * This DTO class was generated automatically
 */
@ApiModel("Список адресов (microzime)")
@ToString
public class AddressListTypeDTO {

	private List<AddressTypeDTO> Address;

	@JsonProperty("Address")
	@ApiModelProperty("")
	public List<AddressTypeDTO> getAddress() {
		return Address;
	}

	public void setAddress(List<AddressTypeDTO> Address) {
		this.Address = Address;
	}
}