package com.in28minutes.microservices.currencyexchangeservice;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyCnvrsnController {
	
	@Autowired
	CurrencyExchangeProxy currencyExchangeProxy;

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurncyConvrsn(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		HashMap<String,String> urlmap = new HashMap<>();
		urlmap.put("from", from);
		urlmap.put("to",to);
		
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate()
				.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, urlmap);
		CurrencyConversion curncyExcng = responseEntity.getBody();
		
		CurrencyConversion conversion = new CurrencyConversion();
		conversion.setId(curncyExcng.getId());
		conversion.setConversionMultiple(curncyExcng.getConversionMultiple());
		conversion.setEnvironment(curncyExcng.getEnvironment());
		conversion.setFrom(from);
		conversion.setTo(to);
		conversion.setQuantity(quantity);
		conversion.setTotalCalculatedAmount(curncyExcng.getConversionMultiple().multiply(quantity));
		
		//return new CurrencyConversion(1000L, from, to, BigDecimal.ONE, quantity, BigDecimal.ONE, "");
		
		return conversion;
	}
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurncyConvrsnFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		
		CurrencyConversion curncyExcng = currencyExchangeProxy.retriveCurncyExchange(from, to);
		
		CurrencyConversion conversion = new CurrencyConversion();
		conversion.setId(curncyExcng.getId());
		conversion.setConversionMultiple(curncyExcng.getConversionMultiple());
		conversion.setEnvironment(curncyExcng.getEnvironment()+" feign");
		conversion.setFrom(from);
		conversion.setTo(to);
		conversion.setQuantity(quantity);
		conversion.setTotalCalculatedAmount(curncyExcng.getConversionMultiple().multiply(quantity));
		
		//return new CurrencyConversion(1000L, from, to, BigDecimal.ONE, quantity, BigDecimal.ONE, "");
		
		return conversion;
	}
}
