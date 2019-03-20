package com.Infosys;

import java.util.ArrayList;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.Infosys.Results;

@RestController
public class RestTemplateController {
	
	RestTemplate restemplate = new RestTemplate();
	
	@RequestMapping(value="/jsonresponse",method=RequestMethod.GET,headers = {"Accept:application/json,Content-Type:application/json"},produces="application/json")
	public ArrayList<Results> getJsonResponse(@RequestParam(required = false, value = "input_text") String query) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setCacheControl("no-cache");
		HttpEntity<String> Entity = new HttpEntity<String>(headers);
		String restUrl = "http://localhost:8080/PublishArticleAutomation/rest/SearchService/question/"+query;
		
		System.out.println(headers.getCacheControl());
		System.out.println(headers.getContentType());
		
		ResponseEntity<Resultss> response = restemplate.exchange(restUrl,HttpMethod.GET,Entity,Resultss.class);
		
		Resultss rl = response.getBody();
		return rl.getResult();
		

	}

}
