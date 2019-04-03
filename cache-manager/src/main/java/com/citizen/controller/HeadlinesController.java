package com.citizen.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.citizen.model.NewsAPIResponse;

@RestController
public class HeadlinesController {
	
	RestTemplate restTemplate = new RestTemplate();
	
	public NewsAPIResponse getNewResponse()
	{

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setCacheControl("no-cache");
		HttpEntity<String> Entity = new HttpEntity<String>(headers);
		String restUrl = "https://newsapi.org/v2/top-headlines?sources=cnn&apiKey=8fe0a96abf4f40cf91f943be9b20903b";
		
		ResponseEntity response = restTemplate.exchange(restUrl,HttpMethod.GET,Entity,NewsAPIResponse.class);
		
		NewsAPIResponse newsApiResponse = (NewsAPIResponse) response.getBody();
		
		return newsApiResponse;
	}

}
