package com.cloudwise.smartagent.application;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.cloudwise.smartagent.utils.Debug;

public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext arg0) throws Exception {
		Debug.log("start the bundle.");
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		Debug.log("stop the bundle.");
	}
}
