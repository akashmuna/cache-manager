package com.citizen.model;

public class InitialScreenSession {
	
	private String session;
	private String transactionId;
	private String priorTransactionId;
	
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getPriorTransactionId() {
		return priorTransactionId;
	}
	public void setPriorTransactionId(String priorTransactionId) {
		this.priorTransactionId = priorTransactionId;
	}	
}
