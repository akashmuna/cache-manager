package com.citizen.service;

import java.util.Collections;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.citizen.config.ApplicationConfiguration;
import com.citizen.model.AskQuestionResponse;
import com.citizen.model.AuthToken;
import com.citizen.model.AuthenticationToken;
import com.citizen.model.InitialScreenSession;
import com.citizen.model.KAResults;
import com.citizen.model.Results;

@Service
public class SearchService {
	
	private RestTemplate restTemplate;
	
	private AuthenticationToken authenticationToken;
	
	private AuthToken authToken; 
	
	private InitialScreenSession intialScreenSession;
	
	private Results searchResults;
	
	@Autowired
	private ApplicationConfiguration applicationConfiguration;

	public List<KAResults> getTopSearchResults(String question_box) {
		
		// To authenticate and authorize the API User 
		try {
			authToken = getAuthToken();
		}
		catch(JSONException e)
		{
			e.printStackTrace();
		}
		//To authenticate and authorize the Console User
		authenticationToken = getAuthenticationToken(authToken.getAuthenticationToken());
		
		//This method creates a new search session and returns the ID of the session
		intialScreenSession = getSessionValue(authToken.getAuthenticationToken(),authenticationToken.getAuthenticationToken().getIntegrationUserToken());
		
		//This method returns a set of search results for the specified question
		searchResults = AskQuestion(authToken.getAuthenticationToken(),authenticationToken.getAuthenticationToken().getIntegrationUserToken(),intialScreenSession.getSession(),question_box);
		
		return searchResults.getResults();
	}
	
	private Results AskQuestion(String authenticationToken2, String integrationUserToken, String session,String question_box) {
		
		String restURL = "https://"+applicationConfiguration.getASK_QUESTION_URL()+"/srt/api/v1/search/question?question="+question_box;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setCacheControl("no-cache");
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set("kmauthtoken", 
		        "{\"siteName\":\""+applicationConfiguration.getSiteName()+"\",\"localeId\":\"en_US\",\"interfaceId\":\"1\",\"integrationUserToken\":\"" + 
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
		
		String restURL = "https://"+applicationConfiguration.getASK_QUESTION_URL()+"/srt/api/latest/search/initialScreen";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setCacheControl("no-cache");
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set("kmauthtoken", 
		        "{\"siteName\":\""+applicationConfiguration.getSiteName()+"\",\"localeId\":\"en_US\",\"interfaceId\":\"1\",\"integrationUserToken\":\"" + 
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
		
		String restURL = "https://"+applicationConfiguration.getAUTH_IRS_URL()+"/km/api/latest/auth/authorize";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setCacheControl("no-cache");
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set("kmauthtoken", "{\"siteName\":\""+applicationConfiguration.getSiteName()+"\",\"localeId\":\"en_US\",\"integrationUserToken\":\""+authenticationToken+"\"}");
		
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("userName",applicationConfiguration.getAPI_USERID());
		map.add("password", applicationConfiguration.getAPI_PASSWORD());
		map.add("siteName",applicationConfiguration.getSiteName());
		map.add("userExternalType", applicationConfiguration.getUserExternalType() );

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		restTemplate = new RestTemplate();
		
		ResponseEntity response = restTemplate.postForEntity(restURL, request , AuthenticationToken.class );
		
		return (AuthenticationToken) response.getBody();
	}

	public AuthToken getAuthToken() throws JSONException
	{
		String restURL = "https://"+ applicationConfiguration.getAUTH_IRS_URL()+"/km/api/latest/auth/integration/authorize";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		//headers.set("Content-Type", "application/json");
		//headers.set("cache-control", "no-cache");
		
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		//headers.set("Accept",MediaType.APPLICATION_JSON_VALUE);
		
		headers.set("kmauthtoken", "{\"login\":\""+applicationConfiguration.getAPI_USERID()+"\",\"password\":\""+applicationConfiguration.getAPI_PASSWORD()+"\",\"siteName\":\""+applicationConfiguration.getSiteName()+"\",\"localeId\":\"en_US\"}");
		
		
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("login",applicationConfiguration.getAPI_USERID());;
		map.add("password", applicationConfiguration.getAPI_PASSWORD());
		map.add("siteName", applicationConfiguration.getSiteName());
		
		JSONObject json = new JSONObject();
		json.put("login", applicationConfiguration.getAPI_USERID());
		json.put("password", applicationConfiguration.getAPI_PASSWORD());
		json.put("siteName", applicationConfiguration.getSiteName());
		
		//final HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map ,headers);

		HttpEntity <?> request = new HttpEntity <Object> (json, headers);
		
		restTemplate = new RestTemplate();
		
		//ResponseEntity response = restTemplate.exchange(restURL, HttpMethod.POST, request, AuthToken.class); 
		ResponseEntity response = restTemplate.postForEntity(restURL, request , AuthToken.class );
		
		return (AuthToken)response.getBody(); 

	}

}
