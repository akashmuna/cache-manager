package com.cache.util.exception;

public class NewsAPIException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String status_code;
	private String url;
	
	public NewsAPIException(String message) {
		super(message);
	}
	
	public NewsAPIException(String message,String status_code, String url) {
		super(message);
		this.status_code = status_code;
		this.url = url;
	}

	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
