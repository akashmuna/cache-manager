package com.citizen.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.citizen.config.ApplicationConfiguration;
import com.citizen.model.Headline;
import com.citizen.model.NewsAPIResponse;

@Service
public class HeadlineService {

	RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	private ApplicationConfiguration applicationConfiguration;
	
	private static final Logger logger = LoggerFactory.getLogger(HeadlineService.class);
	
	public List<Headline> retrieveResults() {
		
		String restUrl = applicationConfiguration.getURL();
		logger.debug("URL used to retrieve the News : "+ restUrl);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setCacheControl("no-cache");
		
		HttpEntity<String> Entity = new HttpEntity<String>(headers);
		
		ResponseEntity response = restTemplate.exchange(restUrl,HttpMethod.GET,Entity,NewsAPIResponse.class);
		
		NewsAPIResponse newsApiResponse = (NewsAPIResponse) response.getBody();
		
		return newsApiResponse.getArticles();
		
	}

}
