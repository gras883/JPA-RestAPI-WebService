package com.rest.webservices.restfullwebservice;

import java.util.Locale;

import org.hibernate.validator.spi.messageinterpolation.LocaleResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class RestfullWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfullWebServiceApplication.class, args);
	}

	
	@Bean
	public SessionLocaleResolver localResolver() {
		SessionLocaleResolver localResolver = new SessionLocaleResolver();
		localResolver.setDefaultLocale(Locale.US);
		return localResolver;
	}
	
	@Bean 
	public ResourceBundleMessageSource bundleMessageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("message");
		return messageSource;
	}
}
