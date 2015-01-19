package com.cloudwise.smartagent.component;

import java.util.List;

import com.cloudwise.smartagent.application.ApplicationActivator;
import com.cloudwise.smartagent.plugin.api.ICollect;
import com.cloudwise.smartagent.plugin.api.IDiscover;

public class ComponentFactory {
	private static ComponentFactory componentFactory = null;

	private ComponentFactory() {
	}

	public static ComponentFactory getInstance() {
		if (componentFactory == null) {
			componentFactory = new ComponentFactory();
		}
		return componentFactory;
	}

	public List<IDiscover> getDiscoverList() {
		return ApplicationActivator.getAgentPlugin(IDiscover.class);
	}

	public IDiscover getDiscoverByServiceType(String serviceType) {
		return ApplicationActivator
				.getAgentPlugin(IDiscover.class, serviceType);
	}

	public ICollect getCollectByServiceType(String serviceType) {
		return ApplicationActivator.getAgentPlugin(ICollect.class, serviceType);
	}

}
