package com.cloudwise.smartagent.component;

public enum HostProperties {
	TYPE_GROUP_OS("os_group", 190), // os组
	TYPE_HOST("base", 100), // 主机信息
	TYPE_BURDEN("cpu_burden", 101), // CPU负载
	TYPE_CPU_USE_RATE("cpu_usage", 102), // CPU使用率
	TYPE_MEM("mem", 103), // 内存使用率
	TYPE_LAN_TRAFFIC("lan", 104), // 网卡流量
	TYPE_DISK_USE_RATE("disk_usage", 105), // 磁盘空间
	TYPE_DISK_IO("disk_io", 106), // 磁盘IO
	TYPE_SYSTEM_PROCESS("proc", 107), // 系统进程数
	TYPE_TCP("tcp", 108); // TCP

	private String key;
	private int value;

	HostProperties(String key, int value) {
		this.key = key;
		this.value = value;
	}

	public String key() {
		return this.key;
	}

	public int value() {
		return this.value;
	}
}
