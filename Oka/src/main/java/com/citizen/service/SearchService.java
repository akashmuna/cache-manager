package com.citizen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.citizen.model.AskQuestionResponse;
import com.citizen.model.AuthToken;
import com.citizen.model.AuthenticationToken;
import com.citizen.model.InitialScreenSession;
import com.citizen.model.KAResults;
import com.citizen.model.Results;

@Service
public class SearchService {
	
	private RestTemplate restTemplate;
	
	@Autowired
	private AuthenticationToken authenticationToken;
	
	@Autowired
	private AuthToken authToken; 
	
	@Autowired
	private InitialScreenSession intialScreenSession;
	
	@Autowired
	private Results searchResults;

	public List<KAResults> getTopSearchResults(String question_box) {
		
		// To authenticate and authorize the API User 
		authToken = getAuthToken();
		
		//To authenticate and authorize the Console User
		authenticationToken = getAuthenticationToken(authToken.getAuthenticationToken());
		
		//This method creates a new search session and returns the ID of the session
		intialScreenSession = getSessionValue(authToken.getAuthenticationToken(),authenticationToken.getAuthenticationToken().getIntegrationUserToken());
		
		//This method returns a set of search results for the specified question
		searchResults = AskQuestion(authToken.getAuthenticationToken(),authenticationToken.getAuthenticationToken().getIntegrationUserToken(),intialScreenSession.getSession(),question_box);
		
		return searchResults.getResults();
	}
	
	private Results AskQuestion(String authenticationToken2, String integrationUserToken, String session,String question_box) {
		
		String restURL = "https://citizens--tst2-qp.custhelp.com//srt/api/v1/search/question?question="+question_box;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setCacheControl("no-cache");
		headers.setAccept((List<MediaType>) MediaType.APPLICATION_JSON);
		headers.set("kmauthtoken", 
		        "{\"siteName\":\"citizens__tst1\",\"localeId\":\"en_US\",\"interfaceId\":\"1\",\"integrationUserToken\":\"" + 
		        		authenticationToken + "\",\"userToken\":\"" + integrationUserToken + "\"}");
		
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("session", session);
		map.add("locale", "en-US");
		map.add("resultLocales", "en-US");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		restTemplate = new RestTemplate();
		
		ResponseEntity<AskQuestionResponse> response = restTemplate.postForEntity(restURL, request , AskQuestionResponse.class );
		
		return response.getBody().getResults();
	}

	private InitialScreenSession getSessionValue(String authenticationToken, String integrationUserToken) {
		
		String restURL = "https://citizens--tst1-qp.custhelp.com/srt/api/latest/search/initialScreen";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setCacheControl("no-cache");
		headers.setAccept((List<MediaType>) MediaType.APPLICATION_JSON);
		headers.set("kmauthtoken", 
		        "{\"siteName\":\"citizens__tst1\",\"localeId\":\"en_US\",\"interfaceId\":\"1\",\"integrationUserToken\":\"" + 
		        		authenticationToken + "\",\"userToken\":\"" + integrationUserToken + "\"}");
		
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("transactionId", "0");
		map.add("locale", "en-US");
		map.add("resultLocales", "en-US");
		map.add("session", "1");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		restTemplate = new RestTemplate();
		
		ResponseEntity response = restTemplate.postForEntity(restURL, request , InitialScreenSession.class );
		
		return (InitialScreenSession) response.getBody();
	}

	private AuthenticationToken getAuthenticationToken(String authenticationToken) {
		
		String restURL = "https://citizens--tst1-irs.custhelp.com/km/api/latest/auth/authorize";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setCacheControl("no-cache");
		headers.setAccept((List<MediaType>) MediaType.APPLICATION_JSON);
		headers.set("kmauthtoken", "{\"siteName\":\"citizens__tst1\",\"localeId\":\"en_US\",\"integrationUserToken\":\""+authenticationToken+"\"}");
		
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("userName", "testapi");
		map.add("password", "Test@123");
		map.add("siteName", "citizens__tst1");
		map.add("userExternalType", "CONTACT");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		restTemplate = new RestTemplate();
		
		ResponseEntity response = restTemplate.postForEntity(restURL, request , AuthenticationToken.class );
		
		return (AuthenticationToken) response.getBody();
	}

	public AuthToken getAuthToken()
	{
		String restURL = "https://citizens--tst1-irs.custhelp.com/km/api/latest/auth/integration/authorize";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setCacheControl("no-cache");
		headers.setAccept((List<MediaType>) MediaType.APPLICATION_JSON);
		headers.set("kmauthtoken", "{\"login\":\"testapi\",\"password\":\"Test@123\",\"siteName\":\"citizens__tst1\",\"localeId\":\"en_US\"}");
		
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("login", "testapi");
		map.add("password", "Test@123");
		map.add("siteName", "citizens__tst1");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		restTemplate = new RestTemplate();
		
		ResponseEntity response = restTemplate.postForEntity(restURL, request , AuthToken.class );
		
		return (AuthToken) response.getBody();

	}

}
