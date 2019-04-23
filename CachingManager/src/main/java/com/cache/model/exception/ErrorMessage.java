package com.cache.model.exception;

public class ErrorMessage {

	private String status_code;
	private String url;
	private String message;
	
	public ErrorMessage(String status_code,String url,String message) {
		this.status_code = status_code;
		this.url = url;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
