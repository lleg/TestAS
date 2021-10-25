package ru.digitalspirit.asaka.microloan.dto;

import io.swagger.annotations.ApiModel;
import lombok.ToString;
import java.math.BigInteger;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

/**
 * This DTO class was generated automatically
 */
@ApiModel("Сведения о работе (microzime)")
@ToString
public class JobInfoTypeDTO {

	private BigInteger Id;
	private String Type;
	private String EmployerName;
	private String Phone;
	private String INN;
	private String EmployerActivityType;
	private String Position;
	private String StartJobDate;
	private BigInteger LastJobExperienceMonths;
	private BigInteger TotalJobExperienceMonths;
	private BigDecimal MonthlySalary;
	private String BankSalary;

	@JsonProperty("id")
	@ApiModelProperty("Идентификатор")
	public BigInteger getId() {
		return Id;
	}

	public void setId(BigInteger Id) {
		this.Id = Id;
	}

	@JsonProperty("Type")
	@ApiModelProperty("Тип деятельности (Работа по найму, Предпринимательская деятельность и т.д.)")
	public String getType() {
		return Type;
	}

	public void setType(String Type) {
		this.Type = Type;
	}

	@JsonProperty("EmployerName")
	@ApiModelProperty("Наименование работодателя")
	public String getEmployerName() {
		return EmployerName;
	}

	public void setEmployerName(String EmployerName) {
		this.EmployerName = EmployerName;
	}

	@JsonProperty("Phone")
	@ApiModelProperty("Телефон организации")
	public String getPhone() {
		return Phone;
	}

	public void setPhone(String Phone) {
		this.Phone = Phone;
	}

	@JsonProperty("INN")
	@ApiModelProperty("ИНН работодателя")
	public String getINN() {
		return INN;
	}

	public void setINN(String INN) {
		this.INN = INN;
	}

	@JsonProperty("EmployerActivityType")
	@ApiModelProperty("Вид деятельности работодателя")
	public String getEmployerActivityType() {
		return EmployerActivityType;
	}

	public void setEmployerActivityType(String EmployerActivityType) {
		this.EmployerActivityType = EmployerActivityType;
	}

	@JsonProperty("Position")
	@ApiModelProperty("Должность")
	public String getPosition() {
		return Position;
	}

	public void setPosition(String Position) {
		this.Position = Position;
	}

	@JsonProperty("StartJobDate")
	@ApiModelProperty("Дата начало работы")
	public String getStartJobDate() {
		return StartJobDate;
	}

	public void setStartJobDate(String StartJobDate) {
		this.StartJobDate = StartJobDate;
	}

	@JsonProperty("LastJobExperienceMonths")
	@ApiModelProperty("Стаж на последнем месте работы в месяцах")
	public BigInteger getLastJobExperienceMonths() {
		return LastJobExperienceMonths;
	}

	public void setLastJobExperienceMonths(BigInteger LastJobExperienceMonths) {
		this.LastJobExperienceMonths = LastJobExperienceMonths;
	}

	@JsonProperty("TotalJobExperienceMonths")
	@ApiModelProperty("Общий трудовой стаж в месяцах")
	public BigInteger getTotalJobExperienceMonths() {
		return TotalJobExperienceMonths;
	}

	public void setTotalJobExperienceMonths(BigInteger TotalJobExperienceMonths) {
		this.TotalJobExperienceMonths = TotalJobExperienceMonths;
	}

	@JsonProperty("MonthlySalary")
	@ApiModelProperty("Среднемесячная заработная плата(сум)")
	public BigDecimal getMonthlySalary() {
		return MonthlySalary;
	}

	public void setMonthlySalary(BigDecimal MonthlySalary) {
		this.MonthlySalary = MonthlySalary;
	}

	@JsonProperty("BankSalary")
	@ApiModelProperty("В каком Банке получаете ЗП")
	public String getBankSalary() {
		return BankSalary;
	}

	public void setBankSalary(String BankSalary) {
		this.BankSalary = BankSalary;
	}
}