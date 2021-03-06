package ru.digitalspirit.asaka.bpm.model.other.processapp;

import com.google.gson.annotations.SerializedName;
import ru.digitalspirit.asaka.bpm.model.common.RestEntity;

import java.util.Date;

public class InstalledSnapshot extends RestEntity {
	
	//Name of installed snapshot.
	@SerializedName("name")
	private String name;
	
	//Acronym of installed snapshot.
	@SerializedName("acronym")
	private String acronym;
	
	//Shows if snapshot is activated.
	@SerializedName("active")
	private Boolean isActive;
	
	//Snapshot creation date;
	@SerializedName("createdOn")
	private Date createdOn;
	
	//Shows if snapshot is tip.
	@SerializedName("snapshotTip")
	private Boolean isTip;
	
	//Date of snapshot activation.
	@SerializedName("activeSince")
	private Date activeSince;
	
	//The snapshot branch identifier.
	@SerializedName("branchID")
	private String branchId;
	
	//The snapshot branch name.
	@SerializedName("branchName")
	private String branchName;
	
	//The snapshot identifier.
	@SerializedName("ID")
	private String id;

	/**
	 * @return Name of installed snapshot.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Acronym of installed snapshot.
	 */
	public String getAcronym() {
		return acronym;
	}

	/**
	 * @return Shows if snapshot is activated.
	 */
	public Boolean isActive() {
		return isActive;
	}

	/**
	 * @return Snapshot creation date;
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * @return Shows if snapshot is tip.
	 */
	public Boolean isTip() {
		return isTip;
	}

	/**
	 * @return Date of snapshot activation.
	 */
	public Date getActiveSince() {
		return activeSince;
	}

	/**
	 * @return The snapshot branch identifier.
	 */
	public String getBranchId() {
		return branchId;
	}

	/**
	 * @return The snapshot branch name.
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * @return The snapshot identifier.
	 */
	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public void setIsTip(Boolean isTip) {
		this.isTip = isTip;
	}

	public void setActiveSince(Date activeSince) {
		this.activeSince = activeSince;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public void setId(String id) {
		this.id = id;
	}

}
