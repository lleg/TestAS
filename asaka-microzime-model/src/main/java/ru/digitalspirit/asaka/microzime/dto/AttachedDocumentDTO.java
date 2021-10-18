package ru.digitalspirit.asaka.microzime.dto;

import io.swagger.annotations.ApiModel;
import lombok.ToString;
import java.math.BigInteger;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * This DTO class was generated automatically
 */
@ApiModel("Сканы документов клиента и документов по БГ (microzime)")
@ToString
public class AttachedDocumentDTO {

	private BigInteger Id;
	private String GUID;
	private String DocumentType;
	private String DocumentName;
	private String DocumentDate;
	private String OperationType;

	@JsonProperty("id")
	@ApiModelProperty("Идентификатор записи в oBPM")
	public BigInteger getId() {
		return Id;
	}

	public void setId(BigInteger Id) {
		this.Id = Id;
	}

	@JsonProperty("GUID")
	@ApiModelProperty("Уникальный идентификатор документа в oBPM")
	public String getGUID() {
		return GUID;
	}

	public void setGUID(String GUID) {
		this.GUID = GUID;
	}

	@JsonProperty("DocumentType")
	@ApiModelProperty("Тип Документа")
	public String getDocumentType() {
		return DocumentType;
	}

	public void setDocumentType(String DocumentType) {
		this.DocumentType = DocumentType;
	}

	@JsonProperty("DocumentName")
	@ApiModelProperty("Наименование документа")
	public String getDocumentName() {
		return DocumentName;
	}

	public void setDocumentName(String DocumentName) {
		this.DocumentName = DocumentName;
	}

	@JsonProperty("DocumentDate")
	@ApiModelProperty("Дата создания документа")
	public String getDocumentDate() {
		return DocumentDate;
	}

	public void setDocumentDate(String DocumentDate) {
		this.DocumentDate = DocumentDate;
	}

	@JsonProperty("OperationType")
	@ApiModelProperty("Вид операции (Клиент/Банковская гарантия)")
	public String getOperationType() {
		return OperationType;
	}

	public void setOperationType(String OperationType) {
		this.OperationType = OperationType;
	}
}