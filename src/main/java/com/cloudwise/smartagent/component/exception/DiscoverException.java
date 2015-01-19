package com.cloudwise.smartagent.component.exception;

public class DiscoverException extends RuntimeException {

    public DiscoverException(String msg) {
        super(msg);
    }

    public DiscoverException(StringBuffer msg) {
        super(msg.toString());
    }

    public DiscoverException(Throwable e) {
        super(e);
    }

	public DiscoverException(String msg, Throwable e) {
        super(msg, e);
    }
}