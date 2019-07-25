package com.cache.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@RestController
@CrossOrigin()
@Api(value="", description="Operates to Authorize the user")
public class Authorize {
	
	
	@ApiOperation(value = "/authorize",
			authorizations = {
	                @Authorization(value = "JWT", scopes = {}),
	                @Authorization(value = "Bearer")
	        },
		    notes = "Pass the Authorization Bearer Token and News Channel Source",
		    response = String.class)
@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully Authorized"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the response"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
	        @ApiResponse(code = 500, message = "Internal Server Error")
	    })
@ApiImplicitParams({
    @ApiImplicitParam(name = "Authorization", value = "Bearer + Token generated", required = true, dataType = "string", paramType = "header")
  })
	@RequestMapping({ "/authorize" })
	public String hello() {
		return "success";
	}

}
