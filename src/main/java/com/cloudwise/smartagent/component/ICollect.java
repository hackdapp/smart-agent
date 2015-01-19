package com.cloudwise.smartagent.component;

import com.cloudwise.smartagent.component.exception.CollectException;

/**
 * @author nolan
 *
 */
public interface ICollect {
	public int id();
	
	public String name();
	/**
	 * @return
	 */
	public Object doCollect() throws CollectException;
}
