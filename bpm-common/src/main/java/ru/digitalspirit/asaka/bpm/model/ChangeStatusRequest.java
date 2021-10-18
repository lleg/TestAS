package ru.digitalspirit.asaka.bpm.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeStatusRequest {

    private String appAcronym;

    private String applicationId;

    private String statusCode;

    private String taskName;

    private String user;

}
