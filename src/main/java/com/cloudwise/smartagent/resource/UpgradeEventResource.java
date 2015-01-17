package com.cloudwise.smartagent.resource;

import java.util.Map;

import com.cloudwise.smartagent.schedule.IScheduleEvent;

public class UpgradeEventResource implements IScheduleEvent {
	private static final String ENDPOINT = "http:www.baidu.com/download";

	public UpgradeEventResource(String url) {
	}

	public String getName() {
		return "UpgradeEvent";
	}

	public void execute(Map map) {

	}

}
