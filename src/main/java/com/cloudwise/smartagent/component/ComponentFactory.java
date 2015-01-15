package com.cloudwise.smartagent.component;

import java.util.List;
import java.util.Map;

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
			
		}
		return ImmutableList.copyOf(discoverMap.values());
	}
	
	public IDiscover getDiscover(String discoverId){
		return discoverMap.get(discoverId);
	}
	
	public List<ICollect> getCollectList(){
		if(collectMap==null || collectMap.size()==0){
			//TODO dynamic load the bundle<IDiscover,ICollect> from bundleContext
			
		}
		return ImmutableList.copyOf(collectMap.values());
	}
	public ICollect getCollect(String collectId){
		return collectMap.get(collectId);
	}
}
