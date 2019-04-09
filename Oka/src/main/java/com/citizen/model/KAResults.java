package com.citizen.model;

import java.util.List;

public class KAResults {
	
	private String name;
	private Integer pageNumber;
	private Integer pageMore;
	private Integer pageStart;
	private Double score;
	private Integer pageSize;
	private Integer unshownResults;
	private Integer totalResults;
	private List<ResultItem> resultItems;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getPageMore() {
		return pageMore;
	}
	public void setPageMore(Integer pageMore) {
		this.pageMore = pageMore;
	}
	public Integer getPageStart() {
		return pageStart;
	}
	public void setPageStart(Integer pageStart) {
		this.pageStart = pageStart;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getUnshownResults() {
		return unshownResults;
	}
	public void setUnshownResults(Integer unshownResults) {
		this.unshownResults = unshownResults;
	}
	public Integer getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}
	public List<ResultItem> getResultItems() {
		return resultItems;
	}
	public void setResultItems(List<ResultItem> resultItems) {
		this.resultItems = resultItems;
	}

}
