package com.cache.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cache.config.ApplicationConfiguration;
import com.cache.model.Headline;
import com.cache.model.NewsAPIResponse;
import com.cache.util.exception.NewsAPIException;

@Service
public class HeadlineService {

	private RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	private ApplicationConfiguration applicationConfiguration;
	
	private ResponseEntity response; 
	
	private static final Logger logger = LoggerFactory.getLogger(HeadlineService.class);
	
	@Cacheable(value= "newsCache", key= "#newschannel")
	public List<Headline> retrieveResults(String newschannel) {
		
		String restUrl = applicationConfiguration.getURL() + newschannel;
		logger.debug("URL used to retrieve the News : "+ restUrl);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setCacheControl("no-cache");
		
		HttpEntity<String> Entity = new HttpEntity<String>(headers);
		
		try {
			response = restTemplate.exchange(restUrl,HttpMethod.GET,Entity,NewsAPIResponse.class);
		}
		catch(RestClientException rce) {
			logger.info("Please check the Debug logs...");
			rce.printStackTrace();
			
			String message = "Call to the NEWSAPI.org failed";
			throw new NewsAPIException(message, rce.getMessage(), restUrl);
		}
		
		
		NewsAPIResponse newsApiResponse = (NewsAPIResponse) response.getBody();
		
		return newsApiResponse.getArticles();
		
	}

	public String authorizeToken(String authorization) {
		
		String authenticationURL = applicationConfiguration.getAUTH_URL();
		logger.debug("URL used to Authorize the Token : "+ authenticationURL);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setCacheControl("no-cache");
		headers.set("Authorization", authorization);
		
		HttpEntity<String> Entity = new HttpEntity<String>(headers);
		
		try {
			response = restTemplate.exchange(authenticationURL,HttpMethod.POST,Entity,String.class);
		}
		catch(RestClientException rce) {
			logger.info("Please check the Debug logs...");
			rce.printStackTrace();
			
			String message = "Call to the Authorize the Token failed";
			throw new NewsAPIException(message, rce.getMessage(), authenticationURL);
		}
		
		return (String) response.getBody();
	}

}
