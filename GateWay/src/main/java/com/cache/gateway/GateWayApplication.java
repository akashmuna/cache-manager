package com.cache.gateway;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class GateWayApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(GateWayApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

}
