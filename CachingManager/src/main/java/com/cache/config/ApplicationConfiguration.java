package com.cache.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
public class ApplicationConfiguration {
	
	@Value("{app.URL}")
	private String URL;
	
	@Value("{app.AUTH_URL}")
	private String AUTH_URL;
	
	@Value("{app.NEWS_API_KEY}")
	private String NEWS_API_KEY;

	public String getNEWS_API_KEY() {
		return NEWS_API_KEY;
	}

	public void setNEWS_API_KEY(String nEWS_API_KEY) {
		NEWS_API_KEY = nEWS_API_KEY;
	}

	public String getAUTH_URL() {
		return AUTH_URL;
	}

	public void setAUTH_URL(String aUTH_URL) {
		AUTH_URL = aUTH_URL;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}
}
