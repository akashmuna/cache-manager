package com.cache.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class Authorize {
	
	@RequestMapping({ "/authorize" })
	public String hello() {
		return "success";
	}

}
