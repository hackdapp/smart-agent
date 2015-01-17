package com.cloudwise.smartagent.utils;

public class ContextHandle {
	public static String getAccountId() {
		return "test_zhang";
	}
	
	public static String getHostKey(){
		return StringUtil.MD5(getAccountId()+SystemTool.getMACAddress());
	}
}
