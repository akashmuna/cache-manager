package com.cache.model;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

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
