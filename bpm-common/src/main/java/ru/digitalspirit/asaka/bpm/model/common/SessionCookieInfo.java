package ru.digitalspirit.asaka.bpm.model.common;

import java.io.Serializable;

public class SessionCookieInfo implements Serializable {

    private String jSessionId;

    private String domain;

    private String path;

    public String getJSessionId() {
        return jSessionId;
    }

    public void setJSessionId(String jSessionId) {
        this.jSessionId = jSessionId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
