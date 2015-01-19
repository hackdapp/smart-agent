package com.cloudwise.smartagent.resource;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import com.cloudwise.smartagent.communicate.HttpResource;
import com.cloudwise.smartagent.component.ComponentFactory;
import com.cloudwise.smartagent.model.ServiceReportInfo;
import com.cloudwise.smartagent.plugin.api.IDiscover;
import com.cloudwise.smartagent.plugin.api.model.ServiceInfo;
import com.cloudwise.smartagent.schedule.IScheduleEvent;
import com.cloudwise.smartagent.utils.Debug;
import com.cloudwise.smartagent.utils.SystemTool;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DiscoverEventResource implements IScheduleEvent {
	private HttpResource httpResource;

	public DiscoverEventResource(HttpResource httpResource) {
		this.httpResource = httpResource;
	}

	public String getName() {
		return "DiscoverEvent";
	}

	public void execute(Map map) {
		// 1.define the xml's root element
		ServiceReportInfo reportInfo = new ServiceReportInfo(
				SystemTool.getMACAddress());

		// 2.invoke the discoverlist's service.
		List<IDiscover> discoverList = ComponentFactory.getInstance()
				.getDiscoverList();
		for (IDiscover discover : discoverList) {
			List<ServiceInfo> serviceList = discover.doDiscover();
			for (ServiceInfo serviceInfo : serviceList) {
				reportInfo.addService(serviceInfo.getService_qualifier(),
						serviceInfo);
			}
		}
		String writeValueAsString = "";
		try {
			writeValueAsString = new ObjectMapper()
					.writeValueAsString(reportInfo);
			Debug.logVerbose("DiscoverService JsonStr: " + writeValueAsString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		// 3.send the service msg to web portal.
		httpResource.post("/postServices/" + reportInfo.getId(),
				writeValueAsString, MediaType.APPLICATION_JSON_TYPE);
	}

}
