package com.cloudwise.smartagent.component;

public class ServiceSerialNum {
	 public static final int TYPE_GROUP_OS = 190; //os组
	    public static final int TYPE_HOST = 100; // 主机信息
	    public static final int TYPE_BURDEN = 101; //CPU负载
	    public static final int TYPE_CPU_USE_RATE = 102; //CPU使用率
	    public static final int TYPE_MEM = 103; //内存使用率 
	    public static final int TYPE_LAN_TRAFFIC = 104; //网卡流量
	    public static final int TYPE_DISK_USE_RATE = 105; //磁盘空间
	    public static final int TYPE_DISK_IO = 106; //磁盘IO
	    public static final int TYPE_SYSTEM_PROCESS = 107; //系统进程数
	    public static final int TYPE_TCP = 108; //TCP
	    
	    public static final int TYPE_EXCEPTION_HANDLER = 999; //SmartAgent异常详情
	    public static final int TYPE_MONITOR_HEALTY = 1000; //SmartAgent健康状况
	    
	    public static final int TYPE_CODE_AGENT_PHP = 1001; //PHP代码监控
	    public static final int TYPE_CODE_AGENT_JAVA = 1002; //JAVA代码监控

	    public static final int TYPE_APACHE = 201; //apache
	    public static final int TYPE_LIGHTTPD = 202; //lighttpd
	    public static final int TYPE_NGINX = 203; //nginx
	    public static final int TYPE_MYSQL = 204; //mysql
	    public static final int TYPE_MONGODB = 205; //mongodb
	    public static final int TYPE_REDIS = 206; //redis
	    public static final int TYPE_MEMCACHE = 207; //memcache
	    public static final int TYPE_TOMCAT = 208; //tomcat
	    public static final int TYPE_IIS = 209; //iss
	    public static final int TYPE_SQLSERVER = 210; //sqlserver
	    public static final int TYPE_ORACLE = 211; //oracle
}
