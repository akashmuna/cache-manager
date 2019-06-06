package com.cache.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cache.model.Headline;
import com.cache.model.NewsAPIResponse;
import com.cache.service.HeadlineService;

@RestController
@RequestMapping("/NewsAPI")
public class HeadlinesController {
	
	@Autowired
	private HeadlineService headlineService;
	
	@RequestMapping(value ="/HeadLines",method=RequestMethod.GET)
	public List<Headline> getNewResponse(@RequestParam(value="newschannel") String newschannel)
	{
		return headlineService.retrieveResults(newschannel);
	}

}
