package com.cb.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NameService {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;

	public String callNameService() {
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("NameCircuitBreaker");
		return circuitBreaker.run(() -> restTemplate.getForObject("http://localhost:8081/api/v1/names", String.class),
				throwable -> fallbackNameService());
		//return restTemplate.getForObject("http://localhost:8081/api/v1/names", String.class);
	}

	private String fallbackNameService() {
		return "Fallback name is returned";
	}
}
