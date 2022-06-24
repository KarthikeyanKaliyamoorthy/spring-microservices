/**
 * 
 */
package com.in28minutes.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

/**
 * @author karthik
 *
 */

@RestController
public class CircuitBreakerController {

	private static Logger log = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("/sample-api")
	//@Retry(name = "sample-api", fallbackMethod = "hardCodedResp")
	//@CircuitBreaker(name = "default", fallbackMethod = "hardCodedResp")
//	@RateLimiter(name="default")
	@Bulkhead(name="default", fallbackMethod = "hardCodedResp")
	public String smapleApi() {
		log.info("inside sample API");
		ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:8080/dummy", String.class);
		return entity.getBody();
		//return "sample api";
	}
	
	public String hardCodedResp(Exception exception) {
		return "hard Coded Response";
	}
}
