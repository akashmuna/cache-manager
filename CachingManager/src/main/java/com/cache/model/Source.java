package com.cache.model;

import java.io.Serializable;

public class Source implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4054035522580148602L;
	private String id;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
