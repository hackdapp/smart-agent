package com.cloudwise.smartagent.resource;

import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import com.cloudwise.smartagent.communicate.AbstractCommunication;
import com.cloudwise.smartagent.component.ComponentFactory;
import com.cloudwise.smartagent.component.IDiscover;
import com.cloudwise.smartagent.component.discover.model.ServiceInfo;
import com.cloudwise.smartagent.model.ServiceReportInfo;
import com.cloudwise.smartagent.schedule.IScheduleEvent;
import com.cloudwise.smartagent.utils.Debug;
import com.cloudwise.smartagent.utils.SystemTool;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DiscoverEventResource extends AbstractCommunication implements IScheduleEvent {
	private static final String ENDPOINT = "http://portal.toushibao.com/proxy_discover/postServices/{hostkey}";

	
	public DiscoverEventResource(String url){
		super(url);
	}
	public String getName() {
		return "DiscoverEvent";
	}

	public void execute(Map map) {
		//1.define the xml's root element
		ServiceReportInfo reportInfo = new ServiceReportInfo(SystemTool.getMACAddress());
		 
		//2.invoke the discoverlist's service.
		List<IDiscover> discoverList = ComponentFactory.getInstance().getDiscoverList();
		for(IDiscover discover:discoverList){
			List<ServiceInfo> serviceList = discover.doDiscover();
			for(ServiceInfo serviceInfo : serviceList){
				reportInfo.addService(serviceInfo.getService_qualifier(), serviceInfo);
			}
		}
		
		try {
			Debug.logVerbose("DiscoverService JsonStr: "+new ObjectMapper().writeValueAsString(reportInfo));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		//3.send the service msg to web portal.
		super.httpPost("/postServices/"+reportInfo.getId(), Entity.entity(reportInfo,  MediaType.APPLICATION_JSON) );
	}
	
	
}
