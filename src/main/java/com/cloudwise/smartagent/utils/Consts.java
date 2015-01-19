package com.cloudwise.smartagent.utils;


/**
 * @author Will
 * @created at 2014-6-17 下午1:37:42
 */
public class Consts {
    /**
     * Default values
     */
    public final static boolean DEBUG = true; 
    public static final String CHARSET = "UTF-8";
    public static final String LINE_SEPERATOR = System.getProperty("line.separator");
    public static final int TASK_PROPERBIABLIEY = 100;
    
    /**
     * To get the IP info by calling remote public api.
     */
    public static final String ipInfoInterface = "http://ip.taobao.com/service/getIpInfo.php?ip=";
    public static final int TARGET_TYPE = 3;
    public static final String REGEX_NOT_LETTER = "[^A-Za-z0-9]";
    
	/**
	 * Command suffix on different OS.
	 */
    public static final int OS_TYPE_WINDOWS = 2;
    public static final int OS_TYPE_LINUX = 1;
    public static final String WINDOWS_SUFFIX = "bat";
    public static final String LINUX_SUFFIX = "sh";
    
    /**
     * Configs for server things, like suro.
     */
    public static final String MYSQL_CONN_PREFIX = "jdbc:mysql://";
    public static final String LOCAL = "127.0.0.1";
    public static final String COLON = ":";
    public static final String MYSQL_CONN_SUFFIX = "/information_schema?&useUnicode=true&characterEncoding=UTF-8";
    /**
     * For kafka
     */
    public static final String AGENT_TOPIC = "agentTopic";
    public static final String CODE_TOPIC = "codeTopic";
    public static final String AGENT_TYPE = "type";
    public static final String AGENT_VALUE = "value";
    
    /**
     * Service ID
     */
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
    
    /**
     * Configuration info from remote API.
     * */
    public static final int SERVICE_STATUS_ON = 1;	// Service on.
    public static final int APP_STATUS_ON = 1;  // App on.

    /**
     * agent.properties keys
     * */
    public static final String SUROCLIENT_CLIENTTYPE = "SuroClient.clientType";
    public static final String HOST_EXCEPTION_HANDLER_ALIVE = "host.exception.handler.alive";
    public static final String HOST_MONITOR_HANDLER_ALIVE = "host.monitor.handler.alive";
    public static final String REMOTE_GET_URI = "remote.get.uri";
    public static final String HOST_EXCEPTION_HANDLER_FREQUENCY = "host.exception.handler.frequency";
    public static final String REMOTE_POST_URI = "remote.post.uri";
    public static final String SUROCLIENT_LOADBALANCERTYPE = "SuroClient.loadBalancerType";
    public static final String SUROCLIENT_LOADBALANCERSERVER = "SuroClient.loadBalancerServer";
    public static final String HOST_MONITOR_HANDLER_FREQUENCY = "host.monitor.handler.frequency";
    
    /**
     * agent.json keys.
     * XXX Or we could use object instead of Map.
     */
    public static final String MONITOR_TASKS = "tasks";
    public static final String MONITOR_ACTIVE = "active";
    public static final String MONITOR_SERVICE_TYPE = "service_type";
    public static final String MONITOR_CLASS = "class";
    public static final String MONITOR_SURO = "suro";
    public static final String MONITOR_SUROCLIENT_CLIENTTYPE = "SuroClient.clientType";
    public static final String MONITOR_SUROCLIENT_LOADBALANCERSERVER = "SuroClient.loadBalancerServer";
    public static final String MONITOR_SUROCLIENT_LOADBALANCERTYPE = "SuroClient.loadBalancerType";
    public static final String MONITOR_REMOTEAPI = "remoteAPI";
    public static final String MONITOR_REMOTE_GET_URI = "remote.get.uri";
    public static final String MONITOR_REMOTE_POST_URI = "remote.post.uri";
    public static final String MONITOR_ES = "ES";

    
    /**
     * Supported services' names
     */
    public static final String SERVICE_APACHE ="apache";
    public static final String SERVICE_TOMCAT = "tomcat";
    public static final String SERVICE_MYSQL = "mysql";
    public static final String SERVICE_REDIS = "redis";
    public static final String SERVICE_MEMCACHE = "memcache";
    
    /**
     * Configuration field Consts.
     */
    public static final String FILED_SERVICE_CONFIG = "service_config";
    public static final String FIELD_SERVICE_ITEMS = "service_items";
    public static final String FIELD_SERVICE_TYPE = "service_type";
    public static final String FIELD_STATUS = "status";
    public static final String FIELD_FREQUENCY = "frequency";
    
    
    public static final String FIELD_TARGET_ID = "target_id";
    public static final String FIELD_MAC = "mac";
    
    /**
     * Send Msg field Consts.
     */
    public static final String FIELD_REQUEST_ID = "request_id";
    public static final String FIELD_FILE_CONTENT = "content";
    public static final String FIELD_ACCOUNT_ID = "account_id";
    public static final String FIELD_HOST_ID = "host_id";
    public static final String FIELD_DOMAIN_ID = "domain_id";
    public static final String FIELD_PAGE_ID = "page_id";
    public static final String FIELD_MICTIME = "mictime";
    public static final String FIELD_TARGET_TYPE = "target_type";
    public static final String FIELD_SERVICE_QUALIFIER = "service_qualifier";
    
    /**
     * Configuration info from service configuration files.
     * */
    public static final String TOMCAT_RUM_PLUGIN_ON = "on";
}
