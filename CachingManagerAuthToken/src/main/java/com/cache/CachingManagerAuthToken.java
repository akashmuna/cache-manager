package com.cache;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@EnableDiscoveryClient
@ComponentScan
@SpringBootApplication
public class CachingManagerAuthToken {

	public static void main(String[] args) {
		final SpringApplication app = new SpringApplication(CachingManagerAuthToken.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}