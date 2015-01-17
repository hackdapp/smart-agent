package com.cloudwise.smartagent.resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.MediaType;

import com.cloudwise.smartagent.application.ApplicationActivator;
import com.cloudwise.smartagent.communicate.AbstractCommunication;
import com.cloudwise.smartagent.component.ComponentFactory;
import com.cloudwise.smartagent.component.ICollect;
import com.cloudwise.smartagent.model.HostConfigration;
import com.cloudwise.smartagent.model.ServiceItem;
import com.cloudwise.smartagent.schedule.IScheduleEvent;
import com.cloudwise.smartagent.schedule.IScheduleService;
import com.cloudwise.smartagent.utils.ContextHandle;
import com.cloudwise.smartagent.utils.Debug;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

/**
 * @author nolan
 * 
 */
public class ConfigrationEventResource extends AbstractCommunication implements
		IScheduleEvent {

	public ConfigrationEventResource(String baseUrl) {
		super(baseUrl);
	}

	private static final String SERVICE_CFG_PATH = "/getServiceCfgs";
	private static final String APP_CFG_PATH = "/getAppConf";

	private Object fetchServiceRemote(String hostkey) {
		// TODO 起双线程分别取两个配置的配置信息
		String remoteCfg = super.httpGet(SERVICE_CFG_PATH, hostkey,
				MediaType.APPLICATION_JSON_TYPE);

		return null;
	}

	private Object fetchAPPRemote(String hostkey) {
		// TODO 起双线程分别取两个配置的配置信息
		// 1.查询
		String remoteCfg = super.httpGet(APP_CFG_PATH, hostkey,
				MediaType.APPLICATION_JSON_TYPE);

		// 同步修改本地配置

		// 同步调度任务
		return null;
	}

	public String getName() {
		return "ConfigrationEvent";
	}

	public void execute(Map map) {
		String hostKey = ContextHandle.getHostKey();
		// TODO 根据配置信息分析出需要采集的服务，以及采集频率，循环更新调度
		// 1.远程调用配置文件
		String remoteCfg = super.httpGet(SERVICE_CFG_PATH, hostKey,
				MediaType.APPLICATION_JSON_TYPE);

		// 2.解析到实体对象
		HostConfigration hcfg = null;
		try {
			hcfg = new ObjectMapper().readValue(remoteCfg,
					HostConfigration.class);
		} catch (IOException e) {
			Debug.logError(e, "parse the remote configration error. cfg:"
					+ remoteCfg);
		}
		hcfg.getTargetType();
		// 3.进行配置本地存储
		// TODO 采用hsql

		// 4.根据同步的配置信息同步调度任务
		for (Entry<String, ServiceItem> tmpItem : hcfg.getServiceMapItem()
				.entrySet()) {
			String id = tmpItem.getKey();
			ServiceItem serviceItem =tmpItem.getValue();
			ICollect collect = ComponentFactory.getInstance().getCollectByServiceType(serviceItem.getServiceType());
			if(collect!=null){
				IScheduleService scheduleService = ApplicationActivator.getService(IScheduleService.class);
				//**************调**度**配**置*****************
				String scheduleId = serviceItem.getId();
				String scheduleName = "[ServiceType:"+serviceItem.getServiceType()+"]";
				
				String cronExp = "0 0/15 * * ? *";
				PluginEvent pluginEvent = new PluginEvent(collect, new CollectResource());
				Map<String, String> configMap = serviceItem.getConfig();
				//*******************************************
				
				//pattern
				String qualifierId = ContextHandle.getAccountId() + "_" + hcfg.getTargetType() + "_"
				+ hcfg.getTargetId() + "_" + serviceItem.getServiceType() + "_" + serviceItem.getId();
				configMap.put("Topic_QualifierId", qualifierId);
				
				if(scheduleService.exist(scheduleId)){
					scheduleService.updateSchedule(scheduleId,scheduleName, cronExp,pluginEvent , configMap);
				}else{
					scheduleService.addSchedule(scheduleId, scheduleName, cronExp, pluginEvent, configMap);
				}
			}else{
				//TODO download the plugin from reposity
			}
		}
	}
}
