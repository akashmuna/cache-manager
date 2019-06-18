package com.cache.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cache.model.Headline;
import com.cache.service.HeadlineService;
import com.cache.util.exception.NewsAPIException;

@RestController
@RequestMapping("/NewsAPI")
public class HeadlinesController {
	
	@Autowired
	private HeadlineService headlineService;
	
	private static String tokenAuthorization = "failure" ; 
	
	@RequestMapping(value ="/HeadLines",method=RequestMethod.POST)
	public List<Headline> getNewResponse(
			@RequestParam(value="newschannel") String newschannel,
			@RequestHeader(name="Authorization") String Authorization)
	{
		if (Authorization.equals("") || Authorization == null)
			throw new NewsAPIException("Token Not Provided","401","/NewsAPI/HeadLines");
		
		// Authorize the Token
		tokenAuthorization = headlineService.authorizeToken(Authorization);
		
		if (tokenAuthorization.equals("failure"))
			throw new NewsAPIException("Token Not Valid","401","/NewsAPI/HeadLines");
		
		return headlineService.retrieveResults(newschannel);
	}

}
