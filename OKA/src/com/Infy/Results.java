package com.Infy;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Results {

	private String title;
	private String globalAnswerId;
	private String score;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGlobalAnswerId() {
		return globalAnswerId;
	}
	public void setGlobalAnswerId(String globalAnswerId) {
		this.globalAnswerId = globalAnswerId;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
}

