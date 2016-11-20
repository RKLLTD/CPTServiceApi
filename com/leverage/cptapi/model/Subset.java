package com.leverage.cptapi.model;

import java.util.StringJoiner;

public class Subset {

	public static final String ID = "id";
	public static final String NAME = "name";
	
	protected Integer id;
	protected String name;
	
	public Subset(){}
	
	public Subset(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		StringJoiner stringJoiner = new StringJoiner(" ");
		stringJoiner.add(ID + ":").add(Integer.toString(id));
		stringJoiner.add(NAME + ":").add(name);
		return stringJoiner.toString();
	}
}
