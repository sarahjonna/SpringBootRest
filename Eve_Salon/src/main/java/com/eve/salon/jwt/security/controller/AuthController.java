package com.eve.salon.jwt.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eve.salon.jwt.security.repository.RoleRepository;
import com.eve.salon.jwt.security.repository.UserRepository;
import com.eve.salon.jwt.security.request.LoginRequest;
import com.eve.salon.jwt.security.request.SignupRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

		return (ResponseEntity<?>) ResponseEntity.ok();
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {

		return (ResponseEntity<?>) ResponseEntity.ok();
	}
}
