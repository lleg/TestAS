package ru.digitalspirit.asaka.bpm.model;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class UserContextData implements Serializable {

    private String userLogin;

    private String branch;

    private String userName;

    private String userRole;

    private String department;

    private String customerDepartment;

    private String prospectID;

    private Boolean prospectFlag;

    private String clientType;

    private String bisID;

    private String relatedPersonsID;

    private String operationID;

    private String subjectsID;

    private String sessionId;

    private Set<String> bpmRoles;

}
