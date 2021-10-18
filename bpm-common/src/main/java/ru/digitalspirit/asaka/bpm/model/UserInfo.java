package ru.digitalspirit.asaka.bpm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo {

    private String login;

    private String fullName;

    private String position;

    private String emailAddress;

}
