package com.cb.employee.config;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration
public class AppConfig {
	@Bean
	public RestTemplate ameen() {
		return new RestTemplate();
	}

	@Bean
	public TimeLimiterConfig timeLimiterConfig() {
		return TimeLimiterConfig.custom().timeoutDuration(java.time.Duration.ofSeconds(4)).build();
	}

	@Bean
	public CircuitBreakerConfig circuitBreakerConfig() {
		return CircuitBreakerConfig.custom().failureRateThreshold(60).waitDurationInOpenState(Duration.ofSeconds(5))// wait
																													// for
																													// 5
																													// secs
																													// before
																													// breaking
																													// it
				.slidingWindowSize(5) // min no of calls to calculate
				.build();
	}

	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> customizer() {
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.timeLimiterConfig(timeLimiterConfig()).circuitBreakerConfig(circuitBreakerConfig()).build());
	}
}
