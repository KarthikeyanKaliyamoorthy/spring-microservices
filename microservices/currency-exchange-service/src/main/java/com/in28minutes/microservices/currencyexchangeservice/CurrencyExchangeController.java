/**
 * 
 */
package com.in28minutes.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author karthik
 *
 */
@RestController
public class CurrencyExchangeController {
	
	private static Logger log =  LoggerFactory.getLogger(CurrencyExchangeController.class);

	@Autowired
	Environment environment;
	
	@Autowired
	CurrencyExcngRepo currencyExcngRepo; 
	
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retriveCurncyExchange(@PathVariable String from, 
			@PathVariable String to) {
		
		log.info("retriveCurncyExchange input from {}, to {}", from, to);
		
		String port = environment.getProperty("local.server.port");
		CurrencyExchange currencyExcng = currencyExcngRepo.findByFromAndTo(from, to);
		
		if(currencyExcng ==null)
			throw new RuntimeException("Currency exchange not found ");
		
		currencyExcng.setEnvironment(port);
		
		return currencyExcng;
	}
}
