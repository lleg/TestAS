package ru.digitalspirit.asaka.microzime.dto;

import io.swagger.annotations.ApiModel;
import lombok.ToString;
import java.math.BigInteger;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

/**
 * This DTO class was generated automatically
 */
@ApiModel("Данные по Клиенту, который оформляет кредит (microzime)")
@ToString
public class ClientTypeDTO {

	private BigInteger Id;
	private String ClientUID;
	private String ClientID;
	private String ClientCode;
	private String ClientCodeCrm;
	private String FinalRiskLevel;
	private String FullName;
	private String LastName;
	private String FirstName;
	private String Patronymic;
	private String BirthDate;
	private String PlaceOfBirth;
	private String Sex;
	private String Citizenship;
	private String INN;
	private String PinFl;
	private List<DocumentDTO> Document;
	private List<AddressTypeDTO> Address;
	private Boolean RegAddrEqualseResAddr;
	private Boolean IsCoBorrowed;
	private PhoneTypeDTO Phone;
	private JobInfoTypeDTO JobInfo;
	private Boolean IsAdditionalIncome;
	private List<AdditionalIncomeTypeDTO> AdditionalIncome;
	private Boolean IsCar;
	private Boolean IsRealEstate;
	private List<DepositInfoTypeDTO> DepositInfo;
	private String MaritalStatus;
	private BigInteger ChildrenNum0To3;
	private BigInteger ChildrenNum3To16;
	private BigInteger AdultChildrenNum;
	private String Education;
	private String TypeOfHousing;
	private Boolean IsRetiree;
	private String NinpsAccount;
	private List<CurrentLoanTypeDTO> CurrentLoan;
	private List<RelatedPersonDTO> RelatedPerson;

	@JsonProperty("Id")
	@ApiModelProperty("Идентификатор клиента в BPM")
	public BigInteger getId() {
		return Id;
	}

	public void setId(BigInteger Id) {
		this.Id = Id;
	}

	@JsonProperty("ClientUID")
	@ApiModelProperty("Клиент UID (код АБС)")
	public String getClientUID() {
		return ClientUID;
	}

	public void setClientUID(String ClientUID) {
		this.ClientUID = ClientUID;
	}

	@JsonProperty("ClientID")
	@ApiModelProperty("Клиент ID (код АБС)")
	public String getClientID() {
		return ClientID;
	}

	public void setClientID(String ClientID) {
		this.ClientID = ClientID;
	}

	@JsonProperty("ClientCode")
	@ApiModelProperty("Код клиента")
	public String getClientCode() {
		return ClientCode;
	}

	public void setClientCode(String ClientCode) {
		this.ClientCode = ClientCode;
	}

	@JsonProperty("ClientCodeCrm")
	@ApiModelProperty("Код клиента CRM")
	public String getClientCodeCrm() {
		return ClientCodeCrm;
	}

	public void setClientCodeCrm(String ClientCodeCrm) {
		this.ClientCodeCrm = ClientCodeCrm;
	}

	@JsonProperty("FinalRiskLevel")
	@ApiModelProperty("Рейтинг клиента")
	public String getFinalRiskLevel() {
		return FinalRiskLevel;
	}

	public void setFinalRiskLevel(String FinalRiskLevel) {
		this.FinalRiskLevel = FinalRiskLevel;
	}

	@JsonProperty("FullName")
	@ApiModelProperty("ФИО/Название")
	public String getFullName() {
		return FullName;
	}

	public void setFullName(String FullName) {
		this.FullName = FullName;
	}

	@JsonProperty("LastName")
	@ApiModelProperty("Фамилия (ФЛ)")
	public String getLastName() {
		return LastName;
	}

	public void setLastName(String LastName) {
		this.LastName = LastName;
	}

	@JsonProperty("FirstName")
	@ApiModelProperty("Имя (ФЛ)")
	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}

	@JsonProperty("Patronymic")
	@ApiModelProperty("Отчество (ФЛ)")
	public String getPatronymic() {
		return Patronymic;
	}

	public void setPatronymic(String Patronymic) {
		this.Patronymic = Patronymic;
	}

	@JsonProperty("BirthDate")
	@ApiModelProperty("Дата рождения (ФЛ) ")
	public String getBirthDate() {
		return BirthDate;
	}

	public void setBirthDate(String BirthDate) {
		this.BirthDate = BirthDate;
	}

	@JsonProperty("PlaceOfBirth")
	@ApiModelProperty("Место рождения (ФЛ)")
	public String getPlaceOfBirth() {
		return PlaceOfBirth;
	}

	public void setPlaceOfBirth(String PlaceOfBirth) {
		this.PlaceOfBirth = PlaceOfBirth;
	}

	@JsonProperty("Sex")
	@ApiModelProperty("Пол")
	public String getSex() {
		return Sex;
	}

	public void setSex(String Sex) {
		this.Sex = Sex;
	}

	@JsonProperty("Citizenship")
	@ApiModelProperty("Наименование страны гражданства")
	public String getCitizenship() {
		return Citizenship;
	}

	public void setCitizenship(String Citizenship) {
		this.Citizenship = Citizenship;
	}

	@JsonProperty("INN")
	@ApiModelProperty("ИНН")
	public String getINN() {
		return INN;
	}

	public void setINN(String INN) {
		this.INN = INN;
	}

	@JsonProperty("PinFl")
	@ApiModelProperty("ПИНФЛ")
	public String getPinFl() {
		return PinFl;
	}

	public void setPinFl(String PinFl) {
		this.PinFl = PinFl;
	}

	@JsonProperty("Document")
	@ApiModelProperty("Документ Клиента/связ. Лица")
	public List<DocumentDTO> getDocument() {
		return Document;
	}

	public void setDocument(List<DocumentDTO> Document) {
		this.Document = Document;
	}

	@JsonProperty("Address")
	@ApiModelProperty("Адрес")
	public List<AddressTypeDTO> getAddress() {
		return Address;
	}

	public void setAddress(List<AddressTypeDTO> Address) {
		this.Address = Address;
	}

	@JsonProperty("RegAddrEqualseResAddr")
	@ApiModelProperty("Адрес проживания совпадает с адресом регистрации?")
	public Boolean getRegAddrEqualseResAddr() {
		return RegAddrEqualseResAddr;
	}

	public void setRegAddrEqualseResAddr(Boolean RegAddrEqualseResAddr) {
		this.RegAddrEqualseResAddr = RegAddrEqualseResAddr;
	}

	@JsonProperty("IsCoBorrowed")
	@ApiModelProperty("Наличие созаемщика?")
	public Boolean getIsCoBorrowed() {
		return IsCoBorrowed;
	}

	public void setIsCoBorrowed(Boolean IsCoBorrowed) {
		this.IsCoBorrowed = IsCoBorrowed;
	}

	@JsonProperty("Phone")
	@ApiModelProperty("Основной мобильный телефон")
	public PhoneTypeDTO getPhone() {
		return Phone;
	}

	public void setPhone(PhoneTypeDTO Phone) {
		this.Phone = Phone;
	}

	@JsonProperty("JobInfo")
	@ApiModelProperty("Информация о месте работы")
	public JobInfoTypeDTO getJobInfo() {
		return JobInfo;
	}

	public void setJobInfo(JobInfoTypeDTO JobInfo) {
		this.JobInfo = JobInfo;
	}

	@JsonProperty("IsAdditionalIncome")
	@ApiModelProperty("Наличие дополнительного дохода")
	public Boolean getIsAdditionalIncome() {
		return IsAdditionalIncome;
	}

	public void setIsAdditionalIncome(Boolean IsAdditionalIncome) {
		this.IsAdditionalIncome = IsAdditionalIncome;
	}

	@JsonProperty("AdditionalIncome")
	@ApiModelProperty("Информация о дополнительном доходе")
	public List<AdditionalIncomeTypeDTO> getAdditionalIncome() {
		return AdditionalIncome;
	}

	public void setAdditionalIncome(
			List<AdditionalIncomeTypeDTO> AdditionalIncome) {
		this.AdditionalIncome = AdditionalIncome;
	}

	@JsonProperty("IsCar")
	@ApiModelProperty("Наличие автомобиля")
	public Boolean getIsCar() {
		return IsCar;
	}

	public void setIsCar(Boolean IsCar) {
		this.IsCar = IsCar;
	}

	@JsonProperty("IsRealEstate")
	@ApiModelProperty("Наличие недвижимого имущества")
	public Boolean getIsRealEstate() {
		return IsRealEstate;
	}

	public void setIsRealEstate(Boolean IsRealEstate) {
		this.IsRealEstate = IsRealEstate;
	}

	@JsonProperty("DepositInfo")
	@ApiModelProperty("Информация о депозитах клиента")
	public List<DepositInfoTypeDTO> getDepositInfo() {
		return DepositInfo;
	}

	public void setDepositInfo(List<DepositInfoTypeDTO> DepositInfo) {
		this.DepositInfo = DepositInfo;
	}

	@JsonProperty("MaritalStatus")
	@ApiModelProperty("Семейное положение")
	public String getMaritalStatus() {
		return MaritalStatus;
	}

	public void setMaritalStatus(String MaritalStatus) {
		this.MaritalStatus = MaritalStatus;
	}

	@JsonProperty("ChildrenNum0To3")
	@ApiModelProperty("Кол-во детей в возрасте от 0 до 3 лет")
	public BigInteger getChildrenNum0To3() {
		return ChildrenNum0To3;
	}

	public void setChildrenNum0To3(BigInteger ChildrenNum0To3) {
		this.ChildrenNum0To3 = ChildrenNum0To3;
	}

	@JsonProperty("ChildrenNum3To16")
	@ApiModelProperty("Кол-во детей в возрасте от 3 до 16 лет")
	public BigInteger getChildrenNum3To16() {
		return ChildrenNum3To16;
	}

	public void setChildrenNum3To16(BigInteger ChildrenNum3To16) {
		this.ChildrenNum3To16 = ChildrenNum3To16;
	}

	@JsonProperty("AdultChildrenNum")
	@ApiModelProperty("Кол-во детей в возрасте от 16 лет и более")
	public BigInteger getAdultChildrenNum() {
		return AdultChildrenNum;
	}

	public void setAdultChildrenNum(BigInteger AdultChildrenNum) {
		this.AdultChildrenNum = AdultChildrenNum;
	}

	@JsonProperty("Education")
	@ApiModelProperty("Образование")
	public String getEducation() {
		return Education;
	}

	public void setEducation(String Education) {
		this.Education = Education;
	}

	@JsonProperty("TypeOfHousing")
	@ApiModelProperty("Тип жилья")
	public String getTypeOfHousing() {
		return TypeOfHousing;
	}

	public void setTypeOfHousing(String TypeOfHousing) {
		this.TypeOfHousing = TypeOfHousing;
	}

	@JsonProperty("IsRetiree")
	@ApiModelProperty("Клиент достиг пенсионного возраста?")
	public Boolean getIsRetiree() {
		return IsRetiree;
	}

	public void setIsRetiree(Boolean IsRetiree) {
		this.IsRetiree = IsRetiree;
	}

	@JsonProperty("NinpsAccount")
	@ApiModelProperty("Банк в котором открыт счет НИНПС открыт")
	public String getNinpsAccount() {
		return NinpsAccount;
	}

	public void setNinpsAccount(String NinpsAccount) {
		this.NinpsAccount = NinpsAccount;
	}

	@JsonProperty("CurrentLoan")
	@ApiModelProperty("Информация о действующих кредитах")
	public List<CurrentLoanTypeDTO> getCurrentLoan() {
		return CurrentLoan;
	}

	public void setCurrentLoan(List<CurrentLoanTypeDTO> CurrentLoan) {
		this.CurrentLoan = CurrentLoan;
	}

	@JsonProperty("RelatedPerson")
	@ApiModelProperty("Связ. лица")
	public List<RelatedPersonDTO> getRelatedPerson() {
		return RelatedPerson;
	}

	public void setRelatedPerson(List<RelatedPersonDTO> RelatedPerson) {
		this.RelatedPerson = RelatedPerson;
	}
}