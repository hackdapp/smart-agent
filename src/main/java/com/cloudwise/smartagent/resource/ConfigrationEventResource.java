package com.cloudwise.smartagent.resource;

import java.util.Map;

import com.cloudwise.smartagent.schedule.IScheduleEvent;

/**
 * @author nolan
 *
 */
public class ConfigrationEventResource implements IScheduleEvent{
	private static final String ENDPOINT = "http://portal.toushibao.com/proxy_discover";
	
	private static final String SERVICE_CFG_PATH = "/getServiceCfgs";
	private static final String APP_CFG_PATH = "/getAppConf";
	
	
	public Object fetchRemote(String hostkey){
		//TODO 起双线程分别取两个配置的配置信息
		return null;
	}
 
	public String getName() {
		return "ConfigrationEvent";
	}

	public void execute(Map map) {
		// TODO 根据配置信息分析出需要采集的服务，以及采集频率，循环更新调度
		
		
	}
}
