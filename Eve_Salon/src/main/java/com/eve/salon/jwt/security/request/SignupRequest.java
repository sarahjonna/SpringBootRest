package com.eve.salon.jwt.security.request;

import java.util.Set;

import lombok.Data;
@Data
public class SignupRequest {


	private String username;
	private String password;
	private String email;
	private Set<String> roles;
	
}
