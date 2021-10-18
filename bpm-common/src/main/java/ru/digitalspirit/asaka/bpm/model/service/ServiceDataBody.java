package ru.digitalspirit.asaka.bpm.model.service;

import com.google.gson.annotations.SerializedName;
import ru.digitalspirit.asaka.bpm.model.common.RestEntity;

import java.util.Map;


public class ServiceDataBody  extends RestEntity {

    @SerializedName("serviceStatus")
    private ServiceStatus serviceStatus;

    @SerializedName("key")
    private String key;

    @SerializedName("step")
    private String step;

    @SerializedName("data")
    private Map<String, Object> data;

    @SerializedName("coach")
    private String coach;

    @SerializedName("coachEvals")
    private Map<String, Object> coachEvals;

    @SerializedName("actions")
    private String actions;

    @SerializedName("actionsMap")
    private Map<String, Object> actionsMap;



    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public String getKey() {
        return key;
    }

    public String getStep() {
        return step;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public String getCoach() {
        return coach;
    }

    public Map<String, Object> getCoachEvals() {
        return coachEvals;
    }

    public String getActions() {
        return actions;
    }

    public Map<String, Object> getActionsMap() {
        return actionsMap;
    }



    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public void setCoachEvals(Map<String, Object> coachEvals) {
        this.coachEvals = coachEvals;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public void setActionsMap(Map<String, Object> actionsMap) {
        this.actionsMap = actionsMap;
    }
}
