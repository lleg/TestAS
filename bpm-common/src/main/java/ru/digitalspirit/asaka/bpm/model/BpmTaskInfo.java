package ru.digitalspirit.asaka.bpm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BpmTaskInfo {

    private String id;

    private String subject;

    private String instanceId;

    private String instanceName;

    private String assignedToUser;

    private String assignedToRole;

    private String assignedToDisplayRole;

    private String status;

    private String priority;

    private Date dueDate;

    private Date closedDate;

    private Date createdDate;

    private String info;

    private String applicationId;

    private String name;

}
