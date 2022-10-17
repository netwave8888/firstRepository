package com.netwave.microservices.currencyconversionservice.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.netwave.microservices.currencyconversionservice.model.CurrencyConversion;


// @FeignClient(name="currency-exchange", url="localhost:8000")
// @FeignClient(name="currency-exchange")
@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_SERVICE_HOST:http://localhost}:8000")
//CHANGE-KUBERNETES
// @FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_SERVICE_HOST:http://localhost}:8000")
// @FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_URI:http://localhost}:8000")

public interface CurrencyExchangeProxy {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(
			@PathVariable String from,
			@PathVariable String to);
	
	@GetMapping("/currency-exchange/exchange-rates")
	public List<CurrencyConversion> getExchangeRates();

}