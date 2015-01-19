package com.cloudwise.smartagent.component.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * After Discover scanned all the services included in our platform, report the
 * information to ES.
 * 
 * <br/>
 * <br/>
 * 
 * @author will
 * 
 * @2014-6-30
 */
@JsonIgnoreProperties({ "pid" })
public class ServiceInfo implements Comparable<ServiceInfo> {
	private int pid;
	private int service_type;
	private String service_name;
	/**
	 * To avoid conclusion
	 */
	private String service_qualifier;

	private String attaches;

	private String version;
	private String installPath;
	private String ports;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getService_type() {
		return service_type;
	}

	public void setService_type(int service_type) {
		this.service_type = service_type;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getService_qualifier() {
		return service_qualifier;
	}

	public void setService_qualifier(String service_qualifier) {
		this.service_qualifier = service_qualifier;
	}

	public int compareTo(ServiceInfo o) {
		if (o.getService_qualifier().equals(service_qualifier)) {
			return 0;
		}
		return 1;
	}

	public String getAttaches() {
		return attaches;
	}

	public void setAttaches(String attaches) {
		this.attaches = attaches;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getInstallPath() {
		return installPath;
	}

	public void setInstallPath(String installPath) {
		this.installPath = installPath;
	}

	public String getPorts() {
		return ports;
	}

	public void setPorts(String ports) {
		this.ports = ports;
	}
}