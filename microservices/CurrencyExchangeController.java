package com.netwave.microservices.currencyexcnangeservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netwave.microservices.currencyexcnangeservice.model.CurrencyExchange;
import com.netwave.microservices.currencyexcnangeservice.service.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;
	
	@GetMapping("/currency-exchange/exchange-rates")
	public List<CurrencyExchange> getAll() {
		List<CurrencyExchange> currencyExchangeList = new ArrayList<CurrencyExchange>();
		currencyExchangeList = currencyExchangeRepository.findAll();
		return currencyExchangeList;
	}
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue( 
			@PathVariable String from, @PathVariable String to) {
				
		// CurrencyExchange currencyExchange = new CurrencyExchange(10001L, from, to, new BigDecimal(50));
		CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
		String port = env.getProperty("server.port");
		String host = env.getProperty("HOSTNAME");
		String version = "v11";
		currencyExchange.setEnvironment(port + " " + version + " " + host);
		
		return currencyExchange;
		
	}
	
}
