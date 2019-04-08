package com.citizen.model;

import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationToken {
	
	private AuthorizationUserToken authenticationToken;

	public AuthorizationUserToken getAuthenticationToken() {
		return authenticationToken;
	}

	public void setAuthenticationToken(AuthorizationUserToken authenticationToken) {
		this.authenticationToken = authenticationToken;
	}

	

}
