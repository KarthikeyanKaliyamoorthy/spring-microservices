package com.rest.webservices.restfulwebservices.versoing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

	@GetMapping("/v1/person")
	public PersonV1 personnv1() {	
		return new PersonV1("Raju Bhai");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 personv2() {
		return new PersonV2(new Name("Raju ", "Bhhai"));
	}
	
	@GetMapping(value = "/person", params = "version=1")
	public PersonV1 paramv1() {	
		return new PersonV1("Raju Bhai");
	}
	
	@GetMapping(value = "/person", params = "version=2")
	public PersonV2 paramv2() {
		return new PersonV2(new Name("Raju ", "Bhhai"));
	}
	
	@GetMapping(value = "/person", headers = "X-API-version=1")
	public PersonV1 headerrv1() {	
		return new PersonV1("Raju Bhai");
	}
	
	@GetMapping(value = "/person", headers = "X-API-version=2")
	public PersonV2 headerv2() {
		return new PersonV2(new Name("Raju ", "Bhhai"));
	}
	
	@GetMapping(value = "/person", produces =  "application/vnd.api.version1+json")
	public PersonV1 producesv1() {	
		return new PersonV1("Raju Bhai");
	}
	
	@GetMapping(value = "/person", produces = "application/vnd.api.version2+json")
	public PersonV2 producesv2() {
		return new PersonV2(new Name("Raju ", "Bhhai"));
	}
}
