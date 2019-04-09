package com.citizen.model;

public class ResultItem {

	private String rule;
	private String type;
	private String fileType;
	private Integer answerId;
	private Integer docId;	
	private Double score;
	private Title title;
	private String link;
	private String clickThroughLink;
	private String similarResponseLink;
	private String highlightedLink;

	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public Integer getAnswerId() {
		return answerId;
	}
	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}
	public Integer getDocId() {
		return docId;
	}
	public void setDocId(Integer docId) {
		this.docId = docId;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getClickThroughLink() {
		return clickThroughLink;
	}
	public void setClickThroughLink(String clickThroughLink) {
		this.clickThroughLink = clickThroughLink;
	}
	public String getSimilarResponseLink() {
		return similarResponseLink;
	}
	public void setSimilarResponseLink(String similarResponseLink) {
		this.similarResponseLink = similarResponseLink;
	}
	public String getHighlightedLink() {
		return highlightedLink;
	}
	public void setHighlightedLink(String highlightedLink) {
		this.highlightedLink = highlightedLink;
	}
	
}
