package ru.digitalspirit.asaka.bpm.model.process;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class ExecutionTreeNode {

    private static final List<String> EMPTY_TASK_IDS = Lists.newArrayList();
    private static final List<ExecutionTreeNode> EMPTY_CHILDREN = Lists.newArrayList();
    private static final Map<String,Object> EMPTY_VARIABLES = Maps.newHashMap();

    //The name of the process
    @SerializedName("name")
    private String name;

    //The process instance ID
    @SerializedName("nodeId")
    private String nodeId;

    //Flow Object Id
    @SerializedName("flowObjectId")
    private String flowObjectId;

    //Alternate Flow Object Id used for converted processes
    @SerializedName("alternateFlowObjectId")
    private String alternateFlowObjectId;

    //Task Type
    @SerializedName("taskType")
    private String taskType;

    //Versioning context for sub process
    @SerializedName("processId")
    private String processId;

    //Versioning context for sub process
    @SerializedName("snapshotId")
    private String snapshotId;

    //Versioning context for sub process
    @SerializedName("branchId")
    private String branchId;

    //Token ID
    @SerializedName("tokenId")
    private String tokenId;

    //Created Task IDs
    @SerializedName("createdTaskIDs")
    private List<String> createdTaskIDs;

    //The variables associated with this node in the execution tree
    @SerializedName("variables")
    private Map<String,Object> variables;

    //Child Nodes
    @SerializedName("children")
    private List<ExecutionTreeNode> children;

    /**
     * @return The name of the process
     */
    public String getName() {
        return name;
    }

    /**
     * @return The process instance ID
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * @return Flow Object Id
     */
    public String getFlowObjectId() {
        return flowObjectId;
    }

    /**
     * @return Alternate Flow Object Id used for converted processes
     */
    public String getAlternateFlowObjectId() {
        return alternateFlowObjectId;
    }

    /**
     * @return Task Type
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * @return Versioning context for sub process
     */
    public String getProcessId() {
        return processId;
    }

    /**
     * @return Versioning context for sub process
     */
    public String getSnapshotId() {
        return snapshotId;
    }

    /**
     * @return Versioning context for sub process
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * @return Token ID
     */
    public String getTokenId() {
        return tokenId;
    }

    /**
     * @return Created Task IDs
     */
    public List<String> getCreatedTaskIDs() {
        return  MoreObjects.firstNonNull(createdTaskIDs, EMPTY_TASK_IDS);
    }

    /**
     * @return The variables associated with this node in the execution tree
     */
    public Map<String,Object> getVariables() {
        return MoreObjects.firstNonNull(variables, EMPTY_VARIABLES);
    }

    /**
     * @return Child Nodes
     */
    public List<ExecutionTreeNode> getChildren() {
        return MoreObjects.firstNonNull(children, EMPTY_CHILDREN);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public void setFlowObjectId(String flowObjectId) {
        this.flowObjectId = flowObjectId;
    }

    public void setAlternateFlowObjectId(String alternateFlowObjectId) {
        this.alternateFlowObjectId = alternateFlowObjectId;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public void setSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public void setCreatedTaskIDs(List<String> createdTaskIDs) {
        this.createdTaskIDs = createdTaskIDs;
    }

    public void setVariables(Map<String,Object> variables) {
        this.variables = variables;
    }

    public void setChildren(List<ExecutionTreeNode> children) {
        this.children = children;
    }
}
