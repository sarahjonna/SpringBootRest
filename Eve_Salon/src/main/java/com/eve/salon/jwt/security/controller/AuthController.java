package com.eve.salon.jwt.security.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eve.salon.jwt.security.models.ERole;
import com.eve.salon.jwt.security.models.Role;
import com.eve.salon.jwt.security.models.User;
import com.eve.salon.jwt.security.repository.RoleRepository;
import com.eve.salon.jwt.security.repository.UserRepository;
import com.eve.salon.jwt.security.request.LoginRequest;
import com.eve.salon.jwt.security.request.SignupRequest;
import com.eve.salon.jwt.security.response.MessageResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

		
		return (ResponseEntity<?>) ResponseEntity.ok();
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
		if(userRepository.existsByUsername(signupRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken"));
		}
		
		if(userRepository.existsByEmail(signupRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: EmailId is already taken"));
		}
		
		User user = new User(signupRequest.getUsername(), 
				signupRequest.getEmail(), passwordEncoder.encode(signupRequest.getPassword()));
		
		Set<String> strRoles= signupRequest.getRoles();
		Set<Role> roles= new HashSet<>();
		
		if(strRoles == null) {
			Role userRole =roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow( () -> new RuntimeException("Error: Role is not found"));
			roles.add(userRole);
		}else {
			
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole =roleRepository.findByName(ERole.ROLE_ADMIN)
											.orElseThrow( () -> new RuntimeException("Error: Role is not found"));
					roles.add(adminRole);
					break;

				default:
					Role userRole =roleRepository.findByName(ERole.ROLE_USER)
											.orElseThrow( () -> new RuntimeException("Error: Role is not found"));
					roles.add(userRole);
					break;
				}
			});
		}
		user.setRoles(roles);
	    userRepository.save(user);
	
		return ResponseEntity.ok(new MessageResponse("User Registered successfully"));
	}
}
