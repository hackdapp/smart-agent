package com.cloudwise.smartagent.model;

import java.util.Map;


import com.cloudwise.smartagent.plugin.api.model.ServiceInfo;
import com.cloudwise.smartagent.utils.ContextHandle;
import com.cloudwise.smartagent.utils.Debug;
import com.cloudwise.smartagent.utils.StringUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Maps;

@JsonIgnoreProperties({ "id", "mac" })
public class ServiceReportInfo {
	private String hostKey;
	private String mac;
	private Map<String, ServiceInfo> service_items = Maps.newHashMap();

	public ServiceReportInfo(String mac) {
		this.mac = mac;
	}

	public Map<String, ServiceInfo> getService_items() {
		return this.service_items;
	}

	public void addService(String serviceId, ServiceInfo serviceInfo) {
		this.service_items.put(serviceId, serviceInfo);
	}

	public void deleteService(String serviceId) {
		this.service_items.remove(serviceId);
	}

	public String getId() {
		if (hostKey == null) {
			hostKey = StringUtil.MD5(ContextHandle.getAccountId() + mac);
			Debug.logVerbose("AcountId: " + ContextHandle.getAccountId()
					+ " ; Mac: " + mac + "; hostKey MD5(AcountId + mac) : "
					+ hostKey);
		}
		return hostKey;
	}

	public String getMac() {
		return mac;
	}
}