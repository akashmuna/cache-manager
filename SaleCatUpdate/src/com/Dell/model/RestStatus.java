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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	

}
