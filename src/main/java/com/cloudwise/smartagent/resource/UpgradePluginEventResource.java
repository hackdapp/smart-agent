package com.cloudwise.smartagent.resource;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.cloudwise.smartagent.communicate.HttpResource;
import com.cloudwise.smartagent.schedule.IScheduleEvent;
import com.google.common.collect.Lists;

public class UpgradePluginEventResource implements IScheduleEvent {
	private HttpResource httpResource;
	private static List<String> downloading = Lists.newLinkedList();
	private static Lock lock = new ReentrantLock();

	public UpgradePluginEventResource(HttpResource httpResource) {
		this.httpResource = httpResource;
	}

	public String getName() {
		return "UpgradeEvent";
	}

	public void execute(Map map) {
		this.httpResource = null;
	}

	public static void addDownloadPlugin(String serviceType) {
		lock.lock();
		downloading.add(serviceType);
		lock.unlock();
	}
}
