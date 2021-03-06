package ru.digitalspirit.asaka.bpm.model.task;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.annotations.SerializedName;
import ru.digitalspirit.asaka.bpm.model.common.RestEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class TaskDetailsBody extends RestEntity {
	
	private static final List<String> EMPTY_CLIENT_TYPES = Lists.newArrayList();
	private static final Map<String, Object> EMPTY_DATA = Maps.newHashMap();
	
	//Time the task instance is set into the ready state - this also happens when the task is restarted or when a claim is canceled.
	@SerializedName("activationTime")
	private Date activationTime;

	//The task owner.
	@SerializedName("assignedTo")
	private String assignedTo;

	//Group or User.
	@SerializedName("assignedToType")
	private AssignedType assignedToType;

	//A user-defined client type to specify UI client information, fixed value IBM_WLE_COACH.
	@SerializedName("clientTypes")
	private List<String> clientTypes = Lists.newArrayList();

	//Time when the task instance reached an end state.
	@SerializedName("completionTime")
	private Date completionTime;

	//ID of the context which embraces the task instance.
	@SerializedName("containmentContextID")
	private String containmentContextId;

	//Task instance data.
	@SerializedName("data")
	private Map<String, Object> data = Maps.newHashMap();

	//Description of the task.
	@SerializedName("description")
	private String description;

	//Display name of the task.
	@SerializedName("displayName")
	private String displayName;

	//Time when the task is due.
	@SerializedName("dueTime")
	private Date dueTime;

	//ID of the external activity.
	@SerializedName("externalActivityID")
	private String externalActivityId;

	//Kind of the task.
	@SerializedName("kind")
	private String kind;

	//Last time a property of the task instance changed.
	@SerializedName("lastModificationTime")
	private Date lastModificationTime;

	//The milestone the task is associated with.  This is reserved for future use.
	@SerializedName("milestone")
	private String milestone;

	//Name of the task instance.
	@SerializedName("name")
	private String name;

	//Namespace that categorizes the task instance.
	@SerializedName("namespace")
	private String namespace;
	
	//ID of the user that created the task instance or on whose behalf the task instance was created.
	@SerializedName("originator")
	private String originator;

	//Owner of the task instance.
	@SerializedName("owner")
	private String owner;

	//Priority of the task instance.
	@SerializedName("priority")
	private Integer priority;

	//Task priority (e.g., Low, Normal, High).
	@SerializedName("priorityName")
	private TaskPriority priorityName;

	//Data of the process instance containing the task, currently business data.
	@SerializedName("processData")
	private Object processData;

	@SerializedName("runURL")
	private String runUrl;

	//ID of the ru.digitalspirit.nbu.ws.service.
	@SerializedName("serviceID")
	private String serviceId;

	//Time when the task was claimed or when an invocation task enters the running state.
	@SerializedName("startTime")
	private Date startTime;

	//State of the task instance.
	@SerializedName("state")
	private TaskState state;

	//Status of the task.
	@SerializedName("status")
	private String status;

	//ID of the task template this instance is derived from.
	@SerializedName("tktid")
	private String tktid;

	//Task instance ID.
	@SerializedName("tkiid")
	private String tkiid;

	//Process instance ID.
	@SerializedName("piid")
	private String piid;
	
	/**
	 * @return Time the task instance is set into the ready state - this also happens when the task is restarted or when a claim is canceled.
	 */
	public Date getActivationTime() {
		return activationTime;
	}

	/**
	 * @return The task owner.
	 */
	public String getAssignedTo() {
		return assignedTo;
	}

	/**
	 * @return Group or User.
	 */
	public AssignedType getAssignedToType() {
		return assignedToType;
	}

	/**
	 * @return A user-defined client type to specify UI client information, fixed value IBM_WLE_COACH.
	 */
	public List<String> getClientTypes() {
		return MoreObjects.firstNonNull(clientTypes, EMPTY_CLIENT_TYPES);
	}

	/**
	 * @return Time when the task instance reached an end state.
	 */
	public Date getCompletionTime() {
		return completionTime;
	}

	/**
	 * @return ID of the context which embraces the task instance.
	 */
	public String getContainmentContextId() {
		return containmentContextId;
	}

	/**
	 * @return Task instance data.
	 */
	public Map<String, Object> getData() {
		return MoreObjects.firstNonNull(data, EMPTY_DATA);
	}

	/**
	 * @return Description of the task.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return Display name of the task.
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @return Time when the task is due.
	 */
	public Date getDueTime() {
		return dueTime;
	}

	/**
	 * @return ID of the external activity.
	 */
	public String getExternalActivityId() {
		return externalActivityId;
	}

	/**
	 * @return Kind of the task.
	 */
	public String getKind() {
		return kind;
	}

	/**
	 * @return Last time a property of the task instance changed.
	 */
	public Date getLastModificationTime() {
		return lastModificationTime;
	}

	/**
	 * @return The milestone the task is associated with.  This is reserved for future use.
	 */
	public String getMilestone() {
		return milestone;
	}

	/**
	 * @return Name of the task instance.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Namespace that categorizes the task instance.
	 */
	public String getNamespace() {
		return namespace;
	}

	/**
	 * @return ID of the user that created the task instance or on whose behalf the task instance was created.
	 */
	public String getOriginator() {
		return originator;
	}

	/**
	 * @return Owner of the task instance.
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @return Priority of the task instance.
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * @return Task priority (e.g., Low, Normal, High).
	 */
	public TaskPriority getPriorityName() {
		return priorityName;
	}

	/**
	 * @return Data of the process instance containing the task, currently business data.
	 */
	public Object getProcessData() {
		return processData;
	}

	/**
	 */
	public String getRunUrl() {
		return runUrl;
	}

	/**
	 * @return ID of the ru.digitalspirit.nbu.ws.service.
	 */
	public String getServiceId() {
		return serviceId;
	}

	/**
	 * @return Time when the task was claimed or when an invocation task enters the running state.
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @return State of the task instance.
	 */
	public TaskState getState() {
		return state;
	}

	/**
	 * @return Status of the task.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return ID of the task template this instance is derived from.
	 */
	public String getTktid() {
		return tktid;
	}

	/**
	 * @return ID of the process instance.
	 */
	public String getPiid() {
		return piid;
	}

	/**
	 * @return Task instance ID.
	 */
	public String getTkiid() {
		return tkiid;
	}

	public void setActivationTime(Date activationTime) {
		this.activationTime = activationTime;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public void setAssignedToType(AssignedType assignedToType) {
		this.assignedToType = assignedToType;
	}

	public void setClientTypes(List<String> clientTypes) {
		this.clientTypes = clientTypes;
	}

	public void setCompletionTime(Date completionTime) {
		this.completionTime = completionTime;
	}

	public void setContainmentContextId(String containmentContextId) {
		this.containmentContextId = containmentContextId;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setDueTime(Date dueTime) {
		this.dueTime = dueTime;
	}

	public void setExternalActivityId(String externalActivityId) {
		this.externalActivityId = externalActivityId;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public void setLastModificationTime(Date lastModificationTime) {
		this.lastModificationTime = lastModificationTime;
	}

	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public void setOriginator(String originator) {
		this.originator = originator;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public void setPriorityName(TaskPriority priorityName) {
		this.priorityName = priorityName;
	}

	public void setProcessData(Object processData) {
		this.processData = processData;
	}

	public void setRunUrl(String runUrl) {
		this.runUrl = runUrl;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setState(TaskState state) {
		this.state = state;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTktid(String tktid) {
		this.tktid = tktid;
	}

	public void setTkiid(String tkiid) {
		this.tkiid = tkiid;
	}

	public void setPiid(String piid) {
		this.piid = piid;
	}

}
