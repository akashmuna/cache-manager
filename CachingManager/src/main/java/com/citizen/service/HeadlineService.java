package com.citizen.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.citizen.config.ApplicationConfiguration;
import com.citizen.model.NewsAPIResponse;

@Component
@Service
public class HeadlineService {

	RestTemplate restTemplate = new RestTemplate();
	
	private static final Logger logger = LoggerFactory.getLogger(HeadlineService.class);
	
	public NewsAPIResponse retrieveResults() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setCacheControl("no-cache");
		HttpEntity<String> Entity = new HttpEntity<String>(headers);
		
		ApplicationConfiguration appsConfiguration = new ApplicationConfiguration();
		
		String restUrl = appsConfiguration.getURL(); 
		
		logger.debug("URL used to retrieve the News : "+ restUrl);
		
		ResponseEntity response = restTemplate.exchange(restUrl,HttpMethod.GET,Entity,NewsAPIResponse.class);
		
		NewsAPIResponse newsApiResponse = (NewsAPIResponse) response.getBody();
		
		return newsApiResponse;
		
	}

}
