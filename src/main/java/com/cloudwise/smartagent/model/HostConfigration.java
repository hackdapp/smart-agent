package com.cloudwise.smartagent.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;

public class HostConfigration {
	@JsonProperty("id")
	private String hostKey;

	@JsonProperty("target_id")
	private String targetId;

	@JsonProperty("target_type")
	private String targetType;

	@JsonProperty("service_items")
	private Map<String,ServiceItem> serviceMapItem = Maps.newHashMap();

	public HostConfigration(){
		 
	}
	public HostConfigration(String hostKey, String targetId, String targetType,
			Map<String,ServiceItem> serviceMapItem) {
		this.hostKey = hostKey;
		this.targetId = targetId;
		this.targetType = targetType;
		this.serviceMapItem = serviceMapItem;
	}

	public void setHostKey(String hostKey) {
		this.hostKey = hostKey;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public void setServiceMapItem(Map<String, ServiceItem> serviceMapItem) {
		this.serviceMapItem = serviceMapItem;
	}

	public String getHostKey() {
		return hostKey;
	}

	public String getTargetId() {
		return targetId;
	}

	public String getTargetType() {
		return targetType;
	}

	public Map<String,ServiceItem> getServiceMapItem() {
		return serviceMapItem;
	}
}
