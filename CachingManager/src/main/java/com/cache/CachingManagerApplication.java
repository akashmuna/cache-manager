package com.cache;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CachingManagerApplication {

	public static void main(String[] args) {
		final SpringApplication app = new SpringApplication(CachingManagerApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
		//SpringApplication.run(CachingManagerApplication.class, args);
	}

}
