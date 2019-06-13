package com.cache;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class CachingManagerApplication extends SpringBootServletInitializer{
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	  {
	    return application.sources(new Class[] { CachingManagerApplication.class });
	  }

	public static void main(String[] args) {
		final SpringApplication app = new SpringApplication(CachingManagerApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

}
