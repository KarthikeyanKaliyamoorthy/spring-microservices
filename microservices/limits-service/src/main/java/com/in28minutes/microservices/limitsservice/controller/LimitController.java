package com.in28minutes.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.limitsservice.bean.Limit;
import com.in28minutes.microservices.limitsservice.config.Configratn;

@RestController
public class LimitController {

	@Autowired
	Configratn configratn;

	@GetMapping("/limits")
	public Limit retrivelimits() {
		// return new Limit(1, 100);

		return new Limit(configratn.getMin(), configratn.getMax());
	}
}
