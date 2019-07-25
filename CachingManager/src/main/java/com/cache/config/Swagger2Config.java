package com.cache.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

	private static final String DESCRIPTION = "Getting News HeadLines from various sources using NewsAPI.org and using Redis Cache for faster response";
	private static final String TITLE = "News Headlines Rest API";
	private static final String NAME = "Akash Mohapatra";
	private static final String URL = "https://www.linkedin.com/in/akash-mohapatra-877238b1/";
	private static final String EMAIL = "akash.zlatan@gmail.com"; 
	private static final String PACKAGENAME = "com.cache.controller";
	
	 @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2).select()
	            .apis(RequestHandlerSelectors
	                .basePackage(PACKAGENAME))
	            .paths(PathSelectors.regex("/.*"))
	            .build().apiInfo(apiEndPointsInfo());
	    }
	    private ApiInfo apiEndPointsInfo() {
	        return new ApiInfoBuilder().title(TITLE)
	            .description(DESCRIPTION)
	            .contact(new Contact(NAME,URL,EMAIL))
	            .license("Apache 2.0")
	            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
	            .version("1.0.0")
	            .build();
	    }
}
