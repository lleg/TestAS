package ru.digitalspirit.asaka.microzime.dto;

import io.swagger.annotations.ApiModel;
import lombok.ToString;
import java.math.BigInteger;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * This DTO class was generated automatically
 */
@ApiModel("Идентификатор клиента (microzime)")
@ToString
public class CustomerIDTypeDTO {

	private BigInteger Id;
	private String AppSystemID;
	private String ExternalCustomerID;
	private String Department;
	private Boolean Main;
	private String CreateDate;

	@JsonProperty("id")
	@ApiModelProperty("Идентификатор записи в NFO")
	public BigInteger getId() {
		return Id;
	}

	public void setId(BigInteger Id) {
		this.Id = Id;
	}

	@JsonProperty("AppSystemID")
	@ApiModelProperty("Идентификатор банковской системы")
	public String getAppSystemID() {
		return AppSystemID;
	}

	public void setAppSystemID(String AppSystemID) {
		this.AppSystemID = AppSystemID;
	}

	@JsonProperty("ExternalCustomerID")
	@ApiModelProperty("Идентификатор клиента во внешней системе")
	public String getExternalCustomerID() {
		return ExternalCustomerID;
	}

	public void setExternalCustomerID(String ExternalCustomerID) {
		this.ExternalCustomerID = ExternalCustomerID;
	}

	@JsonProperty("Department")
	@ApiModelProperty("Отделение")
	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String Department) {
		this.Department = Department;
	}

	@JsonProperty("Main")
	@ApiModelProperty("Признак основного идентификатора")
	public Boolean getMain() {
		return Main;
	}

	public void setMain(Boolean Main) {
		this.Main = Main;
	}

	@JsonProperty("CreateDate")
	@ApiModelProperty("Дата создания анкеты клиента")
	public String getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(String CreateDate) {
		this.CreateDate = CreateDate;
	}
}