package ru.digitalspirit.asaka.bpm.model.task;

import com.google.gson.annotations.SerializedName;

public enum AssignedType {
	
	@SerializedName("group")
	GROUP,
	@SerializedName("user")
	USER
	
}
