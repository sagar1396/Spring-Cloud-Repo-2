package com.api.vms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VmsApplication {
	public static void main(String[] args) {
		SpringApplication.run(VmsApplication.class, args);
	}

}
