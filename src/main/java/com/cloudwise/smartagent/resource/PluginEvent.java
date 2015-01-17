package com.cloudwise.smartagent.resource;

import java.util.Map;

import com.cloudwise.smartagent.component.ICollect;
import com.cloudwise.smartagent.schedule.IScheduleEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

public class PluginEvent implements IScheduleEvent {
	private CollectResource collectResource;
	private ICollect collect;

	public PluginEvent(ICollect collect, CollectResource collectResource) {
		this.collectResource = collectResource;
		this.collect = collect;
	}

	public String getName() {
		return "PluginEvent";
	}

	public void execute(Map map) {
		String topicQualifierId = (String) map.get("Topic_QualifierId");
		// 1.调用plugin服务采集数据
		Object collectValue = collect.doCollect();
		// 2.调用collectionResource发送数据
		try {
			String result = new ObjectMapper().writeValueAsString(collectValue);
			Map msgMap = new ImmutableMap.Builder<String, Object>()
					.put("agentTopic", topicQualifierId)
					.put("value", result).build();
			result = new ObjectMapper().writeValueAsString(msgMap);
			this.collectResource.send(result);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
