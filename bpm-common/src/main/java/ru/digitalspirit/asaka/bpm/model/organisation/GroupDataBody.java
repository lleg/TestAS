package ru.digitalspirit.asaka.bpm.model.organisation;

import com.google.gson.annotations.SerializedName;
import ru.digitalspirit.asaka.bpm.model.common.RestEntity;

import java.util.List;

public class GroupDataBody extends RestEntity {

    @SerializedName("groupID")
    private Integer groupID;

    @SerializedName("groupName")
    private String groupName;

    @SerializedName("displayName")
    private String displayName;

    @SerializedName("description")
    private String description;

    @SerializedName("members")
    private List<String> members;

    @SerializedName("managerGroupName")
    private String managerGroupName;


    public Integer getGroupID() {
        return groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getMembers() {
        return members;
    }

    public String getManagerGroupName() {
        return managerGroupName;
    }


    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public void setManagerGroupName(String managerGroupName) {
        this.managerGroupName = managerGroupName;
    }
}
