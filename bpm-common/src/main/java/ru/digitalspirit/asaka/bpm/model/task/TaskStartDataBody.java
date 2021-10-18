package ru.digitalspirit.asaka.bpm.model.task;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;
import com.google.gson.annotations.SerializedName;
import ru.digitalspirit.asaka.bpm.model.common.RestEntity;

import java.util.Map;

public class TaskStartDataBody extends RestEntity {
	
	//private static final TaskStartDataBodyParameters NULL_OBJECT = new TaskStartDataBodyParameters();

	/*
	Change to Map because:

	return: {											return: {
		serviceStatus: "suspend",							serviceStatus: "coach",
		data: {												data: "componentName = CoachFlow, componentId = Component.CoachFlow.70dc0c96-25c1-46a1-92dc-fd1175b75912, symbolTable = null",
			numVar: null,									key: "7"
			sequent: null,								}
			strVar: null,
			nvpVar: null
		}
	}

	@SerializedName("return")
	private TaskStartDataBodyParameters parameters;

	public TaskStartDataBodyParameters getParameters() {
		return MoreObjects.firstNonNull(parameters, NULL_OBJECT);
	}

	public void setParameters(TaskStartDataBodyParameters parameters) {
		this.parameters = parameters;
	}*/

	private static final Map<String, Object> EMPTY_MAP = Maps.newHashMap();

	@SerializedName("return")
	private Map<String, Object> parameters = Maps.newHashMap();

    public Map<String, Object> getParameters() {
		return MoreObjects.firstNonNull(parameters, EMPTY_MAP);
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

}
