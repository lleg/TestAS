package ru.digitalspirit.asaka.bpm.model.process;

import java.util.HashMap;
import java.util.Map;

public class MessageEvent {

    private String processApp;

    private String snapshot;

    private String ucaName;

    private String eventName;

    private String queue;

    private Map<String, String> parameters;

    public String getProcessApp() {
        return processApp;
    }

    public void setProcessApp(String processApp) {
        this.processApp = processApp;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

    public String getUcaName() {
        return ucaName;
    }

    public void setUcaName(String ucaName) {
        this.ucaName = ucaName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public Map<String, String> getParameters() {
        if (parameters == null) {
            parameters = new HashMap<>();
        }
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
}
