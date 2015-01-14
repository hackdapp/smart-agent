package com.cloudwise.smartagent.utils;


public class DebugTest {

	public static void main(String[] args){
		Debug.log(new Exception(""), "this is a message.");
	}
}
