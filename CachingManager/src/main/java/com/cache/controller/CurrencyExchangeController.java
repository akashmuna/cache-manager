package com.cache.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cache.model.Headline;
import com.cache.model.RateExchange;
import com.cache.service.ExchangeRateService;
import com.cache.util.exception.RatesAPIException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="/ExchangeRates", description="Operations Related to Authorize the user and share the exchange Rates")
@RestController
@RequestMapping("/ExchangeRates")
public class CurrencyExchangeController {

	@Autowired
	private ExchangeRateService exchangeRateService;
	
	
	@ApiOperation(value = "Exchange Rates of Currency for current date",
		    notes = "Pass the Base Currency and Exchange currency",
		    response = RateExchange.class,
		    responseContainer = "List")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
	        @ApiResponse(code = 500, message = "Internal Server Error")
	    })
	@RequestMapping(value ="/CurrentExchangeRates",method=RequestMethod.GET)
	public Map<String, Double> getCurrencyExchangeRates(@ApiParam(value = "base", required = true) @RequestParam(value="base") String baseCurrency, @ApiParam(value = "symbols", required = false)@RequestParam(value="symbols", required= false) String convertedCurrency) throws RatesAPIException {
		
		return exchangeRateService.getCurrencyExchangeRate(baseCurrency,convertedCurrency);
	}
}
