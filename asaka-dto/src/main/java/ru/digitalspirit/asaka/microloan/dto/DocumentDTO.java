package ru.digitalspirit.asaka.microloan.dto;

import io.swagger.annotations.ApiModel;
import lombok.ToString;
import java.math.BigInteger;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * This DTO class was generated automatically
 */
@ApiModel("Документ (microzime)")
@ToString
public class DocumentDTO {

	private BigInteger Id;
	private String Type;
	private Boolean IdentificationFlag;
	private String DocumentName;
	private String Series;
	private String Number;
	private String Issued;
	private String IssueDate;
	private String DivisionCode;

	@JsonProperty("id")
	@ApiModelProperty("Идентификатор записи в BPM")
	public BigInteger getId() {
		return Id;
	}

	public void setId(BigInteger Id) {
		this.Id = Id;
	}

	@JsonProperty("Type")
	@ApiModelProperty("Тип документа")
	public String getType() {
		return Type;
	}

	public void setType(String Type) {
		this.Type = Type;
	}

	@JsonProperty("IdentificationFlag")
	@ApiModelProperty("Признак удостоверения личности (да/нет)")
	public Boolean getIdentificationFlag() {
		return IdentificationFlag;
	}

	public void setIdentificationFlag(Boolean IdentificationFlag) {
		this.IdentificationFlag = IdentificationFlag;
	}

	@JsonProperty("DocumentName")
	@ApiModelProperty("Наимнование документа")
	public String getDocumentName() {
		return DocumentName;
	}

	public void setDocumentName(String DocumentName) {
		this.DocumentName = DocumentName;
	}

	@JsonProperty("Series")
	@ApiModelProperty("Серия")
	public String getSeries() {
		return Series;
	}

	public void setSeries(String Series) {
		this.Series = Series;
	}

	@JsonProperty("Number")
	@ApiModelProperty("Номер")
	public String getNumber() {
		return Number;
	}

	public void setNumber(String Number) {
		this.Number = Number;
	}

	@JsonProperty("Issued")
	@ApiModelProperty("Кем выдан")
	public String getIssued() {
		return Issued;
	}

	public void setIssued(String Issued) {
		this.Issued = Issued;
	}

	@JsonProperty("IssueDate")
	@ApiModelProperty("Дата выдачи")
	public String getIssueDate() {
		return IssueDate;
	}

	public void setIssueDate(String IssueDate) {
		this.IssueDate = IssueDate;
	}

	@JsonProperty("DivisionCode")
	@ApiModelProperty("Код подразделения")
	public String getDivisionCode() {
		return DivisionCode;
	}

	public void setDivisionCode(String DivisionCode) {
		this.DivisionCode = DivisionCode;
	}
}