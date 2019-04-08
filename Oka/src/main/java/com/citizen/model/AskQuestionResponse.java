package com.citizen.model;

public class AskQuestionResponse{

	private Results results;
	private String session;
	private Integer transactionId;
	private Integer priorTransactionId;
	
	public Results getResults() {
		return results;
	}
	public void setResults(Results results) {
		this.results = results;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public Integer getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	public Integer getPriorTransactionId() {
		return priorTransactionId;
	}
	public void setPriorTransactionId(Integer priorTransactionId) {
		this.priorTransactionId = priorTransactionId;
	}
}
