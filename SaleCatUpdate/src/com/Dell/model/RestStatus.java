package com.Dell.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * <p> RestStatus : A model Class with detects the success of failure of Category Change API
 *
 */
@XmlRootElement
public class RestStatus {
	
	private String result;
	
	private String ListValue; 

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getListValue() {
		return ListValue;
	}

	public void setListValue(String listValue) {
		ListValue = listValue;
	}
	

}
