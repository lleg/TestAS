package ru.digitalspirit.asaka.bpm.model.service;


import com.google.gson.annotations.SerializedName;

public enum ServiceStatus {

    @SerializedName("end")
    END,
    @SerializedName("coach")
    COACH,
    @SerializedName("error")
    ERROR,
    @SerializedName("debug")
    DEBUG

}
