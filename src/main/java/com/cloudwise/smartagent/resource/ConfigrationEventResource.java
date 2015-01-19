package com.cloudwise.smartagent.resource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.MediaType;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

import com.cloudwise.smartagent.application.ApplicationActivator;
import com.cloudwise.smartagent.communicate.HttpResource;
import com.cloudwise.smartagent.component.ComponentFactory;
import com.cloudwise.smartagent.model.HostConfigration;
import com.cloudwise.smartagent.model.ServiceItem;
import com.cloudwise.smartagent.plugin.api.ICollect;
import com.cloudwise.smartagent.schedule.IScheduleEvent;
import com.cloudwise.smartagent.schedule.IScheduleService;
import com.cloudwise.smartagent.utils.ContextHandle;
import com.cloudwise.smartagent.utils.Debug;
import com.cloudwise.smartagent.utils.IOUtil;
import com.cloudwise.smartagent.utils.StringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author nolan
 * 
 */
public class ConfigrationEventResource implements IScheduleEvent {
	private HttpResource httpResource;
	private String lastModifyMd5 = "";

	public ConfigrationEventResource(HttpResource httpResource) {
		this.httpResource = httpResource;
	}

	private Object fetchServiceRemote(String hostkey) {
		// TODO 起双线程分别取两个配置的配置信息
		String remoteCfg = httpResource.get("/getServiceCfgs", hostkey,
				MediaType.APPLICATION_JSON_TYPE);

		return null;
	}

	private Object fetchAPPRemote(String hostkey) {
		// TODO 起双线程分别取两个配置的配置信息
		// 1.查询
		String remoteCfg = httpResource.get("/getAppConf", hostkey,
				MediaType.APPLICATION_JSON_TYPE);

		// 同步修改本地配置

		// 同步调度任务
		return null;
	}

	public String getName() {
		return "ConfigrationEvent";
	}

	public void execute(Map map) {
		Debug.logVerbose("Execute the ConfigrationEventResource.");
		String hostKey = ContextHandle.getHostKey();
		// TODO 根据配置信息分析出需要采集的服务，以及采集频率，循环更新调度
		// 1.远程调用配置文件
		String remoteCfg = httpResource.get("/getServiceCfgs", hostKey,
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
		// 3.进行配置本地存储
		// TODO 采用hsql

		Debug.logVerbose("the remote configration: " + remoteCfg);
		if (hcfg != null && !lastModifyMd5.equals(StringUtil.MD5(remoteCfg))) {
			lastModifyMd5 = StringUtil.MD5(remoteCfg);
			// 4.根据同步的配置信息同步调度任务
			for (Entry<String, ServiceItem> tmpItem : hcfg.getServiceMapItem()
					.entrySet()) {
				String id = tmpItem.getKey();
				ServiceItem serviceItem = tmpItem.getValue();
				ICollect collect = ComponentFactory.getInstance()
						.getCollectByServiceType(serviceItem.getServiceType());
				if (collect == null) {
					Debug.logVerbose("find the ICollect plugin error ,downloading.........");
					String jarName = httpResource.get("/download",
							serviceItem.getServiceType(),
							MediaType.TEXT_PLAIN_TYPE);
					try {
						URL website = new URL("http://localhost/repository/"
								+ jarName);
						ByteArrayOutputStream bao = new ByteArrayOutputStream();
						IOUtil.transfer(website.openStream(), bao);
						Debug.logVerbose("download success!");
						Bundle bundle = ApplicationActivator.getContext()
								.installBundle(
										jarName,
										new ByteArrayInputStream(bao
												.toByteArray()));

						Debug.logVerbose("install  the bundle success!");
						bundle.start();
						Debug.logVerbose("start the bundle success!");
					} catch (MalformedURLException e) {
						Debug.logVerbose(e, "find the ICollect plugin error.",
								"MalformedURLException");
					} catch (FileNotFoundException e) {
						Debug.logVerbose(e, "find the ICollect plugin error.",
								"FileNotFoundException");
					} catch (IOException e) {
						Debug.logVerbose(e, "find the ICollect plugin error.",
								"IOException");
					} catch (BundleException e) {
						Debug.logVerbose(e, "find the ICollect plugin error.",
								"BundleException");
					}
					collect = ComponentFactory.getInstance()
							.getCollectByServiceType(
									serviceItem.getServiceType());
					Debug.logVerbose("find the ICollect plugin error ,downloading finish.");
				}

				Debug.logVerbose("find the ICollect plugin and syn to the schedule.");
				IScheduleService scheduleService = ApplicationActivator
						.getService(IScheduleService.class);
				// **************调**度**配**置*****************
				String scheduleName = "[ServiceType:"
						+ serviceItem.getServiceType() + "]";

				String expression = "0/30 * * * * ?";
				PluginEvent pluginEvent = new PluginEvent(serviceItem.getServiceType(), httpResource);
				Map<String, String> configMap = serviceItem.getConfig();
				// *******************************************

				// pattern
				String qualifierId = ContextHandle.getAccountId() + "_"
						+ hcfg.getTargetType() + "_" + hcfg.getTargetId() + "_"
						+ serviceItem.getServiceType() + "_" + id;
				configMap.put("Topic_QualifierId", qualifierId);

				if (scheduleService.exist(id)) {
					if (serviceItem.getStatus() == 1) {
						scheduleService.updateSchedule(id, scheduleName,
								expression, pluginEvent, configMap);
					} else {
						scheduleService.deleteSchedule(id);
					}
				} else {
					if (serviceItem.getStatus() == 1) {
						scheduleService.addSchedule(id, scheduleName,
								expression, pluginEvent, configMap);
					}
				}
			}
		}
	}
}
