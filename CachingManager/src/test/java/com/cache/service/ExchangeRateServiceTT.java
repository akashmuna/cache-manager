package com.cache.service;

import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cache.model.RateExchange;
import com.cache.util.exception.RatesAPIException;

public class ExchangeRateServiceTT {
	
	private ResponseEntity response; 
	private RestTemplate restTemplate = new RestTemplate();
	
	private static final String BASECURRENCY = "base";
	private static final String CONVERTEDCURRENCY = "symbols";
	private static final String EXCHANGERATEAPI = "https://api.ratesapi.io/api/latest";

	@Test
	public void getCurrencyExchangeRate() throws RatesAPIException {
	
		String restUrl = EXCHANGERATEAPI + "?" + BASECURRENCY + "="+ "USD&" + CONVERTEDCURRENCY + "=";
		
		System.out.println("Rest URL used : " + restUrl);
		HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		headers.setCacheControl("no-cache");
		
		HttpEntity<String> Entity = new HttpEntity<String>(headers);
		try {
			response = restTemplate.exchange(restUrl,HttpMethod.GET,Entity,RateExchange.class);
		}
		catch(RestClientException rce) {
			rce.printStackTrace();
			
			String message = "Call to the RatesAPI.io failed";
			throw new RatesAPIException(message, rce.getMessage(), restUrl);
		}
		
		System.out.println("Response : " + ((RateExchange)response.getBody()).getBase());
	}

}
