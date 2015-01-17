package com.cloudwise.smartagent.component;

import java.util.List;
import java.util.Map;

import com.cloudwise.smartagent.component.discover.HostDiscover;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

public class ComponentFactory {
	private static ComponentFactory componentFactory = null;

	private ComponentFactory() {
		//TODO dynamic load the bundle<IDiscover,ICollect> from bundleContext
	}

	private static Map<String, IDiscover> discoverMap = Maps.newConcurrentMap();
	private static Map<String, ICollect> collectMap = Maps.newConcurrentMap();

	public static ComponentFactory getInstance() {
		if (componentFactory == null) {
			componentFactory = new ComponentFactory();
		}
		return componentFactory;
	}
	
	public List<IDiscover> getDiscoverList(){
		if(discoverMap==null || discoverMap.size()==0){
			//TODO dynamic load the bundle<IDiscover,ICollect> from bundleContext
			discoverMap.put("host", new HostDiscover());
		}
		return ImmutableList.copyOf(discoverMap.values());
	}
	
	public IDiscover getDiscoverByServiceType(String serviceType){
		return discoverMap.get(serviceType);
	}
	
	public List<ICollect> getCollectList(){
		if(collectMap==null || collectMap.size()==0){
			//TODO dynamic load the bundle<IDiscover,ICollect> from bundleContext
			
		}
		return ImmutableList.copyOf(collectMap.values());
	}
	public ICollect getCollectByServiceType(String serviceType){
		return collectMap.get(serviceType);
	}
}
