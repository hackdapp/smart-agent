/*package com.cloudwise.smartagent.component.discover.model;


import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties({ "id", "mac" })
public class ServiceReportInfo {
	public static final int WINDOWS = 2;
	public static final int LINUX = 1;
	private String hostKey;
	private String id;
	// private int cpuNums;
	// private String cpuVendor;
	// private String cpuModel;
	// private IPAddressInfo addressInfo;
	// private long totalMem;
	// private String host_ip;
	// private String host_name;
	// private int host_type;
	// private String sys_version;
	private String mac;
	private Map<String, ServiceInfo> service_items;

	// public String getHost_ip() {
	// return host_ip;
	// }
	// public void setHost_ip(String host_ip) {
	// this.host_ip = host_ip;
	// }
	// public String getHost_name() {
	// return host_name;
	// }
	// public void setHost_name(String host_name) {
	// this.host_name = host_name;
	// }
	public Map<String, ServiceInfo> getService_items() {
		if (this.service_items == null) {
			return this.service_items = new HashMap<String, ServiceInfo>();
		}
		return this.service_items;
	}

	public void setService_items(Map<String, ServiceInfo> service_items) {
		this.service_items = service_items;
	}

	public void addService(ServiceInfo exe) {
		this.service_items.put(exe.getService_qualifier(), exe);
	}

	public void deleteService(ServiceInfo exe) {
		this.service_items.remove(exe);
	}

	public String getId() {
		if (null == hostKey) {
			hostKey = StringUtil.MD5(CacheCenter.getAccountId() + mac);
		}
		AgentUtils
				.debugMsg("account is is : "
						+ CacheCenter.getAccountId()
						+ " ;;;; mac is : "
						+ mac
						+ " ;;;; hostKey is StringUtil.MD5(CacheCenter.getAccountId() + mac) : "
						+ hostKey);
		return hostKey;
	}

	 
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
}*/