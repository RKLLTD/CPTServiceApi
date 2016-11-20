package com.leverage.cptapi.model;

public enum Services {
	SUBSET ("subset"),
	SERVICE ("service");
	
	private final String value;
	
	private Services(String value)
	{
		this.value = value;
	}
	
	public String value() {
		return value;
	}
}
