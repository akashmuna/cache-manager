package com.citizen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citizen.model.KAResults;
import com.citizen.model.SearchResults;
import com.citizen.service.SearchService;

@RestController
@RequestMapping("/KASearch")
public class SearchController {
	
	@Autowired
	private SearchService searchService ;
	
	@RequestMapping(value ="/jsonresponse",method=RequestMethod.GET)
	public List<KAResults> getTopSearchResults(@RequestParam(value="input_text") String question_box) {
		return searchService.getTopSearchResults(question_box);
		
	}

}
