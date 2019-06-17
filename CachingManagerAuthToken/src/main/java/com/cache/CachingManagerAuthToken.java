package com.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@ComponentScan
@SpringBootApplication
public class CachingManagerAuthToken {

	public static void main(String[] args) {
		SpringApplication.run(CachingManagerAuthToken.class, args);
	}
}