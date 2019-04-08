package com.citizen.model;

import java.util.List;

public class Title {

	private String url;
	private String type;
	private List<Snippet> snippets = null;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Snippet> getSnippets() {
		return snippets;
	}
	public void setSnippets(List<Snippet> snippets) {
		this.snippets = snippets;
	}
	
}
