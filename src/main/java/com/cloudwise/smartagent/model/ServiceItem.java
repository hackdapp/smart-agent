package com.cloudwise.smartagent.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;

/**
 * @author nolan
 * 
 */
public class ServiceItem {
	@JsonIgnore
	private String id;
	
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
	public ServiceItem(String id, String serviceType, int frequency,
			int isStatus, Map<String, String> config) {
		this.id = id;
		this.frequency = frequency;
		this.serviceType = serviceType;
		this.status = isStatus;
		this.config = config;
	}

	public String getId() {
		return id;
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
		return "ServiceItemModel [id=" + id + ", status=" + status
				+ ", frequency=" + frequency + ", probability=" + ", config="
				+ config + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceItem other = (ServiceItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
