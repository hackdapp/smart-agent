package com.cloudwise.smartagent.resource;

import java.util.Map;

import com.cloudwise.smartagent.component.ICollect;
import com.cloudwise.smartagent.schedule.IScheduleEvent;

public class PluginEvent implements IScheduleEvent{
	private CollectResource collectResource;
	private ICollect plugin;
	
	public PluginEvent(ICollect plugin,CollectResource collectResource){
		this.collectResource = collectResource;
		this.plugin = plugin;
	}
	public String getName() {
		return "PluginEvent";
	}

	public void execute(Map map) {
		// TODO 插件采集并发送数据
		//1.调用plugin服务采集数据
		//2.调用collectionResource发送数据
	}

}
