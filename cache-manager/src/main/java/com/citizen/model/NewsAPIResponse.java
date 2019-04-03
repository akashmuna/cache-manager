package com.citizen.model;

import java.util.List;

public class NewsAPIResponse {
	
	private String status;
	private String totalResults;
	private List<Headline> articles;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(String totalResults) {
		this.totalResults = totalResults;
	}
	public List<Headline> getArticles() {
		return articles;
	}
	public void setArticles(List<Headline> articles) {
		this.articles = articles;
	}
	

}
