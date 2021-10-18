package ru.digitalspirit.asaka.bpm.model.process;

import com.google.gson.annotations.SerializedName;

public class ExecutionTree {

    @SerializedName("executionStatus")
    private String executionStatus;

    @SerializedName("root")
    private ExecutionTreeNode root;

    public String getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(String executionStatus) {
        this.executionStatus = executionStatus;
    }

    public ExecutionTreeNode getRoot() {
        return root;
    }

    public void setRoot(ExecutionTreeNode root) {
        this.root = root;
    }
}
