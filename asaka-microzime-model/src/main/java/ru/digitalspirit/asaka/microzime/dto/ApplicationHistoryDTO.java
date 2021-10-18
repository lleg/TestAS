package ru.digitalspirit.asaka.microzime.dto;

import io.swagger.annotations.ApiModel;
import lombok.ToString;
import java.math.BigInteger;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * This DTO class was generated automatically
 */
@ApiModel("История обработки Заявки (microzime)")
@ToString
public class ApplicationHistoryDTO {

	private BigInteger Id;
	private String CurrentStatus;
	private String ChangeUser;
	private String DateTimeChange;
	private String TaskName;
	private String CreateUser;
	private String DateTimeCreate;
	private String DeleteUser;
	private String DateTimeDelete;
	private String Comment;

	@JsonProperty("Id")
	@ApiModelProperty("Идентификатор записи в NFO")
	public BigInteger getId() {
		return Id;
	}

	public void setId(BigInteger Id) {
		this.Id = Id;
	}

	@JsonProperty("CurrentStatus")
	@ApiModelProperty("Текущий статус Заявки")
	public String getCurrentStatus() {
		return CurrentStatus;
	}

	public void setCurrentStatus(String CurrentStatus) {
		this.CurrentStatus = CurrentStatus;
	}

	@JsonProperty("ChangeUser")
	@ApiModelProperty("Пользователь последнего изменения")
	public String getChangeUser() {
		return ChangeUser;
	}

	public void setChangeUser(String ChangeUser) {
		this.ChangeUser = ChangeUser;
	}

	@JsonProperty("DateTimeChange")
	@ApiModelProperty("Дата и время последнего изменения")
	public String getDateTimeChange() {
		return DateTimeChange;
	}

	public void setDateTimeChange(String DateTimeChange) {
		this.DateTimeChange = DateTimeChange;
	}

	@JsonProperty("TaskName")
	@ApiModelProperty("Наименование задачи")
	public String getTaskName() {
		return TaskName;
	}

	public void setTaskName(String TaskName) {
		this.TaskName = TaskName;
	}

	@JsonProperty("CreateUser")
	@ApiModelProperty("Пользователь ввода записи")
	public String getCreateUser() {
		return CreateUser;
	}

	public void setCreateUser(String CreateUser) {
		this.CreateUser = CreateUser;
	}

	@JsonProperty("DateTimeCreate")
	@ApiModelProperty("Дата и время создания записи")
	public String getDateTimeCreate() {
		return DateTimeCreate;
	}

	public void setDateTimeCreate(String DateTimeCreate) {
		this.DateTimeCreate = DateTimeCreate;
	}

	@JsonProperty("DeleteUser")
	@ApiModelProperty("Пользователь удаления")
	public String getDeleteUser() {
		return DeleteUser;
	}

	public void setDeleteUser(String DeleteUser) {
		this.DeleteUser = DeleteUser;
	}

	@JsonProperty("DateTimeDelete")
	@ApiModelProperty("Дата и время удаления записи")
	public String getDateTimeDelete() {
		return DateTimeDelete;
	}

	public void setDateTimeDelete(String DateTimeDelete) {
		this.DateTimeDelete = DateTimeDelete;
	}

	@JsonProperty("Comment")
	@ApiModelProperty("Комментарий пользователя")
	public String getComment() {
		return Comment;
	}

	public void setComment(String Comment) {
		this.Comment = Comment;
	}
}