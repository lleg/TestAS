package ru.digitalspirit.asaka.microloan.dto;

import io.swagger.annotations.ApiModel;
import lombok.ToString;
import java.math.BigInteger;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

/**
 * This DTO class was generated automatically
 */
@ApiModel(" (microzime)")
@ToString
public class ApplicationDTO {

	private BigInteger ApplicationID;
	private String ClaimNumBpm;
	private String ClaimNumCrm;
	private String Branch;
	private String Status;
	private String Decision;
	private CommentTypeDTO VerificationComment;
	private CommentTypeDTO CreditAdministratorComment;
	private String ClaimDate;
	private ClientTypeDTO Client;
	private LoanTypeDTO Loan;
	private InsuranceTypeDTO Insurance;
	private List<AdditionalContactTypeDTO> AdditionalContact;
	private String ManagerName;
	private List<AttachedDocumentDTO> AttachedDocuments;
	private List<ApplicationHistoryDTO> ApplicationHistory;

	@JsonProperty("ApplicationID")
	@ApiModelProperty("Идентификатор Заявки")
	public BigInteger getApplicationID() {
		return ApplicationID;
	}

	public void setApplicationID(BigInteger ApplicationID) {
		this.ApplicationID = ApplicationID;
	}

	@JsonProperty("ClaimNumBpm")
	@ApiModelProperty("Номер Заявки в BPM")
	public String getClaimNumBpm() {
		return ClaimNumBpm;
	}

	public void setClaimNumBpm(String ClaimNumBpm) {
		this.ClaimNumBpm = ClaimNumBpm;
	}

	@JsonProperty("ClaimNumCrm")
	@ApiModelProperty("Номер Заявки в CRM")
	public String getClaimNumCrm() {
		return ClaimNumCrm;
	}

	public void setClaimNumCrm(String ClaimNumCrm) {
		this.ClaimNumCrm = ClaimNumCrm;
	}

	@JsonProperty("Branch")
	@ApiModelProperty("Код филиала операции/Точка продаж")
	public String getBranch() {
		return Branch;
	}

	public void setBranch(String Branch) {
		this.Branch = Branch;
	}

	@JsonProperty("Status")
	@ApiModelProperty("Статус Заявки")
	public String getStatus() {
		return Status;
	}

	public void setStatus(String Status) {
		this.Status = Status;
	}

	@JsonProperty("Decision")
	@ApiModelProperty("Решение по Заявке")
	public String getDecision() {
		return Decision;
	}

	public void setDecision(String Decision) {
		this.Decision = Decision;
	}

	@JsonProperty("VerificationComment")
	@ApiModelProperty("Комментарий Верификатора")
	public CommentTypeDTO getVerificationComment() {
		return VerificationComment;
	}

	public void setVerificationComment(CommentTypeDTO VerificationComment) {
		this.VerificationComment = VerificationComment;
	}

	@JsonProperty("CreditAdministratorComment")
	@ApiModelProperty("Комментарий Кредитного администратора")
	public CommentTypeDTO getCreditAdministratorComment() {
		return CreditAdministratorComment;
	}

	public void setCreditAdministratorComment(
			CommentTypeDTO CreditAdministratorComment) {
		this.CreditAdministratorComment = CreditAdministratorComment;
	}

	@JsonProperty("ClaimDate")
	@ApiModelProperty("Дата Заявки")
	public String getClaimDate() {
		return ClaimDate;
	}

	public void setClaimDate(String ClaimDate) {
		this.ClaimDate = ClaimDate;
	}

	@JsonProperty("Client")
	@ApiModelProperty("Данные по клиенту")
	public ClientTypeDTO getClient() {
		return Client;
	}

	public void setClient(ClientTypeDTO Client) {
		this.Client = Client;
	}

	@JsonProperty("Loan")
	@ApiModelProperty("Данные по Продукту")
	public LoanTypeDTO getLoan() {
		return Loan;
	}

	public void setLoan(LoanTypeDTO Loan) {
		this.Loan = Loan;
	}

	@JsonProperty("Insurance")
	@ApiModelProperty("Данные по Страховке")
	public InsuranceTypeDTO getInsurance() {
		return Insurance;
	}

	public void setInsurance(InsuranceTypeDTO Insurance) {
		this.Insurance = Insurance;
	}

	@JsonProperty("AdditionalContact")
	@ApiModelProperty("Список доп контактов")
	public List<AdditionalContactTypeDTO> getAdditionalContact() {
		return AdditionalContact;
	}

	public void setAdditionalContact(
			List<AdditionalContactTypeDTO> AdditionalContact) {
		this.AdditionalContact = AdditionalContact;
	}

	@JsonProperty("ManagerName")
	@ApiModelProperty("ФИО Менеджера")
	public String getManagerName() {
		return ManagerName;
	}

	public void setManagerName(String ManagerName) {
		this.ManagerName = ManagerName;
	}

	@JsonProperty("AttachedDocuments")
	@ApiModelProperty("Приложенные к заявке сканы документов")
	public List<AttachedDocumentDTO> getAttachedDocuments() {
		return AttachedDocuments;
	}

	public void setAttachedDocuments(List<AttachedDocumentDTO> AttachedDocuments) {
		this.AttachedDocuments = AttachedDocuments;
	}

	@JsonProperty("ApplicationHistory")
	@ApiModelProperty("История обработки Заявки")
	public List<ApplicationHistoryDTO> getApplicationHistory() {
		return ApplicationHistory;
	}

	public void setApplicationHistory(
			List<ApplicationHistoryDTO> ApplicationHistory) {
		this.ApplicationHistory = ApplicationHistory;
	}
}