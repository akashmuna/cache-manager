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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@Api(value="/NewsAPI", description="Operations Related to Authorize the user and share the News Headlines")
@RestController
@RequestMapping("/NewsAPI")
public class HeadlinesController {
	
	@Autowired
	private HeadlineService headlineService;
	
	private static String tokenAuthorization = "failure" ; 
	
	@ApiOperation(value = "/NewsAPI/HeadLines",
				authorizations = {
		                @Authorization(value = "JWT", scopes = {}),
		                @Authorization(value = "Bearer")
		        },
			    notes = "Multiple status values can be provided with comma seperated strings",
			    response = Headline.class,
			    responseContainer = "List")
	@ApiResponses(value = {
		        @ApiResponse(code = 200, message = "Successfully retrieved list"),
		        @ApiResponse(code = 401, message = "You are not authorized to view the response"),
		        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
		        @ApiResponse(code = 500, message = "Internal Server Error")
		    })
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Bearer + Token generated", required = true, dataType = "string", paramType = "header")
	  })
	@RequestMapping(value ="/HeadLines",method=RequestMethod.POST)
	public List<Headline> getNewResponse(
			@ApiParam(value = "news source code", required = true) @RequestParam(value="newschannel") String newschannel,
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
