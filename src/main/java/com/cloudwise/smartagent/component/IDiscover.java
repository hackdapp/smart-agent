package com.cloudwise.smartagent.component;

import java.util.List;

import com.cloudwise.smartagent.component.discover.model.ServiceInfo;



public interface IDiscover {
	
	public List<ServiceInfo> doDiscover();
}
