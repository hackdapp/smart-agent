package com.cloudwise.smartagent.component.exception;

public class CollectException extends RuntimeException {

	public CollectException(String msg) {
		super(msg);
	}

	public CollectException(StringBuffer msg) {
		super(msg.toString());
	}

	public CollectException(Throwable e) {
		super(e);
	}

	public CollectException(String msg, Throwable e) {
		super(msg, e);
	}
}
