package com.citizen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.citizen.model.NewsAPIResponse;
import com.citizen.service.HeadlineService;

@RestController
public class HeadlinesController {
	
	@Autowired
	private HeadlineService headlineService;
	
	@RequestMapping("/HeadLines")
	public NewsAPIResponse getNewResponse()
	{
		return headlineService.retrieveResults();
	}

}
