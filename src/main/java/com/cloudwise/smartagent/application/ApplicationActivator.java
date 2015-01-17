package com.cloudwise.smartagent.application;

import java.util.Map;
import java.util.Map.Entry;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.cloudwise.smartagent.component.ComponentFactory;
import com.cloudwise.smartagent.component.ICollect;
import com.cloudwise.smartagent.model.HostConfigration;
import com.cloudwise.smartagent.model.ServiceItem;
import com.cloudwise.smartagent.resource.CollectResource;
import com.cloudwise.smartagent.resource.ConfigrationEventResource;
import com.cloudwise.smartagent.resource.DiscoverEventResource;
import com.cloudwise.smartagent.resource.PluginEvent;
import com.cloudwise.smartagent.resource.UpgradeEventResource;
import com.cloudwise.smartagent.schedule.IScheduleService;
import com.cloudwise.smartagent.utils.ContextHandle;
import com.cloudwise.smartagent.utils.Debug;
import com.google.common.collect.Maps;

public class ApplicationActivator implements BundleActivator {
	/**
	 * 上下文<br>
	 */
	private static BundleContext context;

	/**
	 * 上下文
	 * 
	 * @return BundleContext
	 * @see
	 */
	public static BundleContext getContext() {
		return context;
	}

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		this.context = bundleContext;
		Debug.log("start agent service.");

		String cronExp = "0 */1 * * * ?";
		IScheduleService scheduleService = ApplicationActivator
				.getService(IScheduleService.class);
		//1.发现服务
		String endpoint = "";
		DiscoverEventResource discoverEventResource =  new DiscoverEventResource(endpoint);
		String derId = DiscoverEventResource.class.getName();
		String derName = DiscoverEventResource.class.getSimpleName();
		scheduleService.addSchedule(derId, derName, cronExp, discoverEventResource, Maps.newHashMap());
		
		//2.在线配置同步更新以及调度任务更新
		endpoint = "";
		ConfigrationEventResource configrationEventResource =  new ConfigrationEventResource(endpoint);
		String cerId =  ConfigrationEventResource.class.getName();
		String cerName = ConfigrationEventResource.class.getSimpleName();
		scheduleService.addSchedule(derId, derName, cronExp, configrationEventResource, Maps.newHashMap());
		
		//3.自动升级插件
		endpoint = "";
		UpgradeEventResource upgradeEventResource =  new UpgradeEventResource(endpoint);
		String uerId = UpgradeEventResource.class.getName();
		String uerName = UpgradeEventResource.class.getSimpleName();
		scheduleService.addSchedule(derId, derName, cronExp, upgradeEventResource, Maps.newHashMap());
		

		HostConfigration hcfg = null;
		for (Entry<String, ServiceItem> tmpItem : hcfg.getServiceMapItem()
				.entrySet()) {
			String id = tmpItem.getKey();
			ServiceItem serviceItem = tmpItem.getValue();
			ICollect collect = ComponentFactory.getInstance()
					.getCollectByServiceType(serviceItem.getServiceType());
			if (collect != null) {
				// **************调**度**配**置*****************
				String scheduleId = serviceItem.getId();
				String scheduleName = "[ServiceType:"
						+ serviceItem.getServiceType() + "]";

				String expression = "0 */2 * * * ?";
				PluginEvent pluginEvent = new PluginEvent(collect,
						new CollectResource());
				Map<String, String> configMap = serviceItem.getConfig();
				// *******************************************

				// pattern
				String qualifierId = ContextHandle.getAccountId() + "_"
						+ hcfg.getTargetType() + "_" + hcfg.getTargetId() + "_"
						+ serviceItem.getServiceType() + "_"
						+ id;
				configMap.put("Topic_QualifierId", qualifierId);

				scheduleService.addSchedule(scheduleId, scheduleName, cronExp,
						pluginEvent, configMap);
			} else {
				// TODO download the plugin from reposity
			}
		}
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		Debug.log("stop the bundle.");
	}

	/**
	 * @param cls
	 *            Class
	 * @return <T> 类实例
	 * @see
	 */
	public static <T> T getService(Class<T> cls) {
		ServiceReference srf = getContext().getServiceReference(cls);
		if (srf != null) {
			return (T) getContext().getService(srf);
		}
		return null;
	}
}
