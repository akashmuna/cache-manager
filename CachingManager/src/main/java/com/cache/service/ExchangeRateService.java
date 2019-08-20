package com.cache.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cache.model.RateExchange;

import com.cache.util.exception.RatesAPIException;

@Service
public class ExchangeRateService {
	
	private ResponseEntity response; 
	private RestTemplate restTemplate = new RestTemplate();
	
	private static final String BASECURRENCY = "base";
	private static final String CONVERTEDCURRENCY = "symbols";
	private static final String EXCHANGERATEAPI = "https://api.ratesapi.io/api/latest";
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36";
	
	private static final Logger logger = LoggerFactory.getLogger(ExchangeRateService.class);

	public Map<String, Double> getCurrencyExchangeRate(String baseCurrency, String convertedCurrency) throws RatesAPIException {
	
		if (convertedCurrency == null)
			convertedCurrency = "";
		String restUrl = EXCHANGERATEAPI + "?" + BASECURRENCY + "="+ baseCurrency + "&" + CONVERTEDCURRENCY + "="+ convertedCurrency;
		logger.debug("URL used to retrieve the Currency Exchange Rates : "+ restUrl);
		
		HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("user-agent", USER_AGENT);
		headers.setCacheControl("no-cache");
		
		HttpEntity<String> Entity = new HttpEntity<String>(headers);
		try {
			response = restTemplate.exchange(restUrl,HttpMethod.GET,Entity,RateExchange.class);
		}
		catch(RestClientException rce) {
			logger.info("Please check the Debug logs...");
			rce.printStackTrace();
			
			String message = "Call to the RatesAPI.io failed";
			throw new RatesAPIException(message, rce.getMessage(), restUrl);
		}
		
		return ((RateExchange)response.getBody()).getRates();
	}

}
