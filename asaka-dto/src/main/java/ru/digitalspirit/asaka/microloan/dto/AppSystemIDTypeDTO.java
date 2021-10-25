package ru.digitalspirit.asaka.microloan.dto;

import io.swagger.annotations.ApiModel;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * This DTO class was generated automatically
 */
@ApiModel("Идентификтор банковской системы (microzime)")
@ToString
public class AppSystemIDTypeDTO {

	private String InstanceID;

	@JsonProperty("InstanceID")
	@ApiModelProperty("Идентификатор экземпляра системы")
	public String getInstanceID() {
		return InstanceID;
	}

	public void setInstanceID(String InstanceID) {
		this.InstanceID = InstanceID;
	}
}