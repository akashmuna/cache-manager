package com.cache.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cache.model.Headline;
import com.cache.model.NewsAPIResponse;

@Service
public class HeadlineServiceTT {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HeadlineServiceTT.class);
	
	private ResponseEntity response;
	private RestTemplate restTemplate = new RestTemplate();

	public List<Headline> getCNNLatestNews(String urlToConsume) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setCacheControl("no-cache");
		
		HttpEntity<String> Entity = new HttpEntity<String>(headers);
		
		try {
			response = restTemplate.exchange(urlToConsume,HttpMethod.GET,Entity,NewsAPIResponse.class);
		}
		catch(RestClientException rce) {
			LOGGER.info("Please check the Debug logs...");
			rce.printStackTrace();
		}
		return ((NewsAPIResponse) response.getBody()).getArticles();
	}
	
	

}
