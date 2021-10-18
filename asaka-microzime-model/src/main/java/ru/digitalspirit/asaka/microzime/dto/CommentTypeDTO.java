package ru.digitalspirit.asaka.microzime.dto;

import io.swagger.annotations.ApiModel;
import lombok.ToString;
import java.math.BigInteger;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * This DTO class was generated automatically
 */
@ApiModel("Комментарий к заявке (microzime)")
@ToString
public class CommentTypeDTO {

	private BigInteger Id;
	private Boolean CriticalFlag;
	private String Comment;
	private String CommentPerson;
	private String CommentDate;

	@JsonProperty("id")
	@ApiModelProperty("Идентификатор комментария")
	public BigInteger getId() {
		return Id;
	}

	public void setId(BigInteger Id) {
		this.Id = Id;
	}

	@JsonProperty("CriticalFlag")
	@ApiModelProperty("Комментарий критичный (true)/некритичный (false)")
	public Boolean getCriticalFlag() {
		return CriticalFlag;
	}

	public void setCriticalFlag(Boolean CriticalFlag) {
		this.CriticalFlag = CriticalFlag;
	}

	@JsonProperty("Comment")
	@ApiModelProperty("Комментарий")
	public String getComment() {
		return Comment;
	}

	public void setComment(String Comment) {
		this.Comment = Comment;
	}

	@JsonProperty("CommentPerson")
	@ApiModelProperty("Признак ")
	public String getCommentPerson() {
		return CommentPerson;
	}

	public void setCommentPerson(String CommentPerson) {
		this.CommentPerson = CommentPerson;
	}

	@JsonProperty("CommentDate")
	@ApiModelProperty("Дата и время комментария")
	public String getCommentDate() {
		return CommentDate;
	}

	public void setCommentDate(String CommentDate) {
		this.CommentDate = CommentDate;
	}
}