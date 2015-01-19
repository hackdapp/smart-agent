package com.cloudwise.smartagent.component;

import java.util.List;

import com.cloudwise.smartagent.component.exception.DiscoverException;
import com.cloudwise.smartagent.component.model.ServiceInfo;



public interface IDiscover {
	 
	public int id();
	
	public List<ServiceInfo> doDiscover() throws DiscoverException;
}
