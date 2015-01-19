package com.cloudwise.smartagent.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;

/**
 * @author nolan
 * 
 */
@JsonIgnoreProperties({"id"})
public class ServiceItem {
	@JsonProperty("service_type")
	private String serviceType;
	
	@JsonProperty("status")
	private int status;
	
	@JsonProperty("frequency")
	private int frequency;
	
	@JsonProperty("service_config")
	private Map<String, String> config = Maps.newHashMap();

	public ServiceItem(){
		
	}
	public ServiceItem(String serviceType, int frequency,
			int isStatus, Map<String, String> config) {
		this.frequency = frequency;
		this.serviceType = serviceType;
		this.status = isStatus;
		this.config = config;
	}

	public String getServiceType() {
		return serviceType;
	}

	public int getStatus() {
		return status;
	}

	public int getFrequency() {
		return frequency;
	}

	public Map<String, String> getConfig() {
		return config;
	}

	@Override
	public String toString() {
		return "ServiceItemModel [ServiceType=" + serviceType + ", status=" + status
				+ ", frequency=" + frequency + ", probability=" + ", config="
				+ config + "]";
	}

}
