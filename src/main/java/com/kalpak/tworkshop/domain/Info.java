package com.kalpak.tworkshop.domain;

import java.io.Serializable;

public class Info implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String key;
	
	String value;
	
	

	public Info(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	
	
}
