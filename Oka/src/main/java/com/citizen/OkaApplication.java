package com.citizen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class OkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(OkaApplication.class, args);
	}

}
