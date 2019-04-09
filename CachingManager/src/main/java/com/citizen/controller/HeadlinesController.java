package com.citizen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citizen.model.Headline;
import com.citizen.model.NewsAPIResponse;
import com.citizen.service.HeadlineService;

@RestController
@RequestMapping("/NewsAPI")
public class HeadlinesController {
	
	@Autowired
	private HeadlineService headlineService;
	
	@RequestMapping(value ="/HeadLines",method=RequestMethod.GET)
	public List<Headline> getNewResponse()
	{
		return headlineService.retrieveResults();
	}

}
