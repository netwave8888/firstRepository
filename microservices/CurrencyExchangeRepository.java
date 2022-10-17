package com.netwave.microservices.currencyexcnangeservice.service;

import com.netwave.microservices.currencyexcnangeservice.model.CurrencyExchange;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository 
	extends JpaRepository<CurrencyExchange, Long> {
		CurrencyExchange findByFromAndTo(String from, String to);

		List<CurrencyExchange> findAll();
	}
