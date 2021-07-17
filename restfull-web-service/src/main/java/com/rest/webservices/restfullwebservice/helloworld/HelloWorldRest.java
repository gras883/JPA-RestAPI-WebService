package com.rest.webservices.restfullwebservice.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class HelloWorldRest {
	@Autowired
	private MessageSource messageSource;
	
	// URI -- Unique resourse indentifier
	//@RequestMapping(method = RequestMethod.GET ,path = "/hello")
	@GetMapping(path = "/hello")
	public String helloword() {
		return "Hello World Rest";
	}

	
	// Using bean 
	@GetMapping(path = "/hello-bean" )
	public HelloWorldBean hellobean() {
		return new HelloWorldBean("This is generated hello world BEAN!!!!!!!!!!!!!!!");
	}
	
	@GetMapping(path = "/hello-internationalized")
	public String helloword(@RequestHeader(name = "Accept-Language",required = false) Locale locale) {
		
		return messageSource.getMessage("good.morning.message",null,  locale);
	}
	
	
}
