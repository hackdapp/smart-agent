package com.cloudwise.smartagent.component;

import java.util.Map;

import com.cloudwise.smartagent.component.discover.model.ServiceInfo;

public interface IDiscover {
	
	public Map<String,ServiceInfo> doDiscover();
}
