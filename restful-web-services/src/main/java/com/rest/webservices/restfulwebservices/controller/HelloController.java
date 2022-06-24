/**
 * 
 */
package com.rest.webservices.restfulwebservices.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.rest.webservices.restfulwebservices.bean.HelloWorld;

/**
 * @author karthik
 *
 */
@RestController
public class HelloController {
	
	@Autowired
	MessageSource messageSource;

	@GetMapping(path = "/hello")
	public String hello() {
		return "Hello world";
	}
	
	@GetMapping(path = "/hello-bean")
	public HelloWorld helloBean() {
		return new HelloWorld("Hello world");
	}
	
	@GetMapping(path = "/hello-bean/{name}")
	public HelloWorld helloBeanPath(@PathVariable String name) {
		return new HelloWorld(String.format("Hello %s",name));
	}
	
	@GetMapping(path = "/hello-i18n")
	public String helloI18n() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
}
