package com.cloudwise.smartagent.utils;

public enum OSType {
	WINDOW(2),LINUX(1);
	private int type;
	OSType(int type){
		this.type = type;
	}
	public int value(){
		return this.type;
	}
}
