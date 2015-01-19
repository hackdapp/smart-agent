package com.cloudwise.smartagent.resource;

import java.util.Map;

import javax.ws.rs.core.MediaType;

import com.cloudwise.smartagent.communicate.HttpResource;
import com.cloudwise.smartagent.component.ComponentFactory;
import com.cloudwise.smartagent.plugin.api.ICollect;
import com.cloudwise.smartagent.schedule.IScheduleEvent;
import com.cloudwise.smartagent.utils.ContextHandle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

public class PluginEvent implements IScheduleEvent {
	private HttpResource collectResource;
	private String serviceType;

	public PluginEvent(String serviceType, HttpResource collectResource) {
		this.collectResource = collectResource;
		this.serviceType = serviceType;
	}

	public String getName() {
		return "PluginEvent";
	}

	public void execute(Map map) {
		String topicQualifierId = (String) map.get("Topic_QualifierId");
		// 1.调用plugin服务采集数据
		ICollect collect = ComponentFactory.getInstance().getCollectByServiceType(serviceType);
		Object collectValue = "";
		if(collect!=null){
			collectValue = collect.doCollect();
		}
		// 2.调用collectionResource发送数据
		try {
			String result = new ObjectMapper().writeValueAsString(collectValue);
			Map msgMap = new ImmutableMap.Builder<String, Object>()
					.put("agentTopic", topicQualifierId)
					.put("value", result).build();
			result = new ObjectMapper().writeValueAsString(msgMap);
			this.collectResource.post("/postCollect/"+ContextHandle.getHostKey(),result,MediaType.APPLICATION_JSON_TYPE);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
