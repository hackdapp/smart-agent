package com.cloudwise.smartagent.application;

import java.util.Collection;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.cloudwise.smartagent.communicate.HttpResource;
import com.cloudwise.smartagent.plugin.api.exception.MultiImplementException;
import com.cloudwise.smartagent.plugin.api.exception.NoExistImplementException;
import com.cloudwise.smartagent.resource.ConfigrationEventResource;
import com.cloudwise.smartagent.resource.DiscoverEventResource;
import com.cloudwise.smartagent.resource.UpgradePluginEventResource;
import com.cloudwise.smartagent.schedule.IScheduleService;
import com.cloudwise.smartagent.utils.Debug;
import com.google.common.collect.Lists;

public class ApplicationActivator implements BundleActivator {
	private String baseUrl = "http://localhost/rest/proxy_discover";
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
		registerResource();
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		IScheduleService scheduleService = ApplicationActivator
				.getService(IScheduleService.class);
		scheduleService.clear();
		scheduleService.deleteSchedule(DiscoverEventResource.class.getName());
		scheduleService.deleteSchedule(ConfigrationEventResource.class.getName());
		Debug.log("stop the bundle.");
	}
	
	
	public void registerResource(){
		String cronExp = "0/30 * * * * ?";
		IScheduleService scheduleService = ApplicationActivator
				.getService(IScheduleService.class);
		//1.发现服务.TODO 方式一：定时检测服务器提供的发现服务插件自动安装; 方式二：根据开启的服务自动下载相关的发现插件
		DiscoverEventResource discoverEventResource =  new DiscoverEventResource(new HttpResource(baseUrl));
		String derId = DiscoverEventResource.class.getName();
		String derName = DiscoverEventResource.class.getSimpleName();
		Debug.logVerbose("register the DiscoverEventResource to schedule.");
		scheduleService.addSchedule(derId, derName, cronExp, discoverEventResource,  null);
		
		//2.在线配置同步更新以及调度任务更新.TODO:因处理方式不同，考虑是否拆成两个实现类，分别处理Service服务\APP服务的配置解析
		ConfigrationEventResource configrationEventResource ;
		String cerId ;
		String cerName;
		// 2.1. service
		configrationEventResource =  new ConfigrationEventResource(new HttpResource(baseUrl));
		cerId =  ConfigrationEventResource.class.getName();
		cerName = ConfigrationEventResource.class.getSimpleName();
		Debug.logVerbose("register the ConfigrationEventResource[Service] to schedule.");
		scheduleService.addSchedule(cerId, cerName, cronExp, configrationEventResource, null);
		// 2.2. app
		configrationEventResource =  new ConfigrationEventResource(new HttpResource(baseUrl));
		cerId =  ConfigrationEventResource.class.getName();
		cerName = ConfigrationEventResource.class.getSimpleName();
		Debug.logVerbose("register the ConfigrationEventResource[App] to schedule.");
//		scheduleService.addSchedule(derId, derName, cronExp, configrationEventResource, null);
		
		//3.自动升级插件（最新版本插件：发现服务插件、采集数据插件）.TODO 更新自己的插件考虑是否用外部的升及插件来做，以防失败重新重启.
		UpgradePluginEventResource upgradeEventResource =  new UpgradePluginEventResource(new HttpResource(baseUrl));
		String uerId = UpgradePluginEventResource.class.getName();
		String uerName = UpgradePluginEventResource.class.getSimpleName();
		Debug.logVerbose("register the UpgradePluginEventResource to schedule.");
//		scheduleService.addSchedule(derId, derName, cronExp, upgradeEventResource, null);
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
	
	public static <T> T getAgentPlugin(Class<T> clazz,String serviceType){
		String filter = "(id="+serviceType+")";
		try {
			Collection<ServiceReference<T>> clnList = getContext().getServiceReferences(clazz, filter);
			List<ServiceReference<T>> srfList = Lists.newArrayList(clnList);
			if(srfList.size()==1){
				return getContext().getService(srfList.get(0));
			}else if(srfList.size()>0){
				throw new MultiImplementException("ServiceType["+serviceType +"] has many instances.");
			}
		} catch (InvalidSyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T> List<T> getAgentPlugin(Class<T> clazz){
		String filter = "(group="+clazz.getName()+")";
		List<T> agentList = Lists.newArrayList();
		try {
			Collection<ServiceReference<T>> srflist = getContext().getServiceReferences(clazz, filter);
			for(ServiceReference<T> srf : srflist){
				agentList.add(getContext().getService(srf));
			}
		} catch (InvalidSyntaxException e) {
			e.printStackTrace();
		}
		return agentList;
	}
}
