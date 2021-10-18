package ru.digitalspirit.asaka.bpm.model.organisation;

import com.google.gson.annotations.SerializedName;
import ru.digitalspirit.asaka.bpm.model.common.RestEntity;

import java.util.List;
import java.util.Map;

public class UserDataBody extends RestEntity {

    @SerializedName("userID")
    private Integer userId;

    @SerializedName("userName")
    private String userName;

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("isDisabled")
    private Boolean isDisabled;

    @SerializedName("primaryGroup")
    private String primaryGroup;

    @SerializedName("emailAddress")
    private String emailAddress;

    @SerializedName("userPreferences")
    private Map<String, String> userPreferences;

    @SerializedName("editableUserPreferences")
    private List<String> editableUserPreferences;

    @SerializedName("memberships")
    private List<String> memberships;



    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public Boolean getDisabled() {
        return isDisabled;
    }

    public String getPrimaryGroup() {
        return primaryGroup;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Map<String, String> getUserPreferences() {
        return userPreferences;
    }

    public List<String> getEditableUserPreferences() {
        return editableUserPreferences;
    }

    public List<String> getMemberships() {
        return memberships;
    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDisabled(Boolean disabled) {
        isDisabled = disabled;
    }

    public void setPrimaryGroup(String primaryGroup) {
        this.primaryGroup = primaryGroup;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setUserPreferences(Map<String, String> userPreferences) {
        this.userPreferences = userPreferences;
    }

    public void setEditableUserPreferences(List<String> editableUserPreferences) {
        this.editableUserPreferences = editableUserPreferences;
    }

    public void setMemberships(List<String> memberships) {
        this.memberships = memberships;
    }
}
