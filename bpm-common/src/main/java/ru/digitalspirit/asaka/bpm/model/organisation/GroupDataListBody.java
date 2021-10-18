package ru.digitalspirit.asaka.bpm.model.organisation;

import com.google.gson.annotations.SerializedName;
import ru.digitalspirit.asaka.bpm.model.common.RestEntity;

import java.util.List;

public class GroupDataListBody extends RestEntity {

    @SerializedName("groups")
    List<GroupDataBody> groups;


    public List<GroupDataBody> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupDataBody> groups) {
        this.groups = groups;
    }
}
