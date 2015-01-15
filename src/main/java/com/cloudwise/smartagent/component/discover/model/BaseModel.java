package com.cloudwise.smartagent.component.discover.model;

public class BaseModel {
	private String cpuVendor;
	private int cpuNums;
	private String cpuModel;
	private IPAddressInfo addressInfo;
	private long totalMem;
	private String host_ip;
	private String host_name;
	private int host_type;
	private String mac;

	public long getTotalMem() {
		return totalMem;
	}

	public void setTotalMem(long totalMem) {
		this.totalMem = totalMem;
	}

	public String getCpuVendor() {
		return cpuVendor;
	}

	public void setCpuVendor(String cpuVendor) {
		this.cpuVendor = cpuVendor;
	}

	public int getCpuNums() {
		return cpuNums;
	}

	public void setCpuNums(int cpuNums) {
		this.cpuNums = cpuNums;
	}

	public String getCpuModel() {
		return cpuModel;
	}

	public void setCpuModel(String cpuModel) {
		this.cpuModel = cpuModel;
	}

	public IPAddressInfo getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(IPAddressInfo addressInfo) {
		this.addressInfo = addressInfo;
	}

	public String getHost_ip() {
		return host_ip;
	}

	public void setHost_ip(String host_ip) {
		this.host_ip = host_ip;
	}

	public String getHost_name() {
		return host_name;
	}

	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}

	public int getHost_type() {
		return host_type;
	}

	public void setHost_type(int host_type) {
		this.host_type = host_type;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
}