package com.api.vms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableCircuitBreaker
public class VmsApplication {
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
//	@Bean
//	public CircuitBreakerConfigCustomizer testCustomizer() {
//	    return CircuitBreakerConfigCustomizer
//	        .of("mainService", builder -> builder.slidingWindowSize(100));
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(VmsApplication.class, args);
	}

}
