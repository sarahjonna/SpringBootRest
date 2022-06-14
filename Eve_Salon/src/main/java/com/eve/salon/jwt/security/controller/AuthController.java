package com.eve.salon.jwt.security.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.eve.salon.jwt.security.response.JwtResponse;
import com.eve.salon.jwt.security.response.MessageResponse;
import com.eve.salon.jwt.security.service.UserDetailsImpl;
import com.eve.salon.jwt.security.utils.JwtUtils;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtils jwtUtils;

	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()) );
         SecurityContextHolder.getContext().setAuthentication(authentication);
         UserDetailsImpl userDetails =  (UserDetailsImpl) authentication.getPrincipal();
         List<String> roles= userDetails.getAuthorities().stream().map(item-> item.getAuthority()).collect(Collectors.toList());
         String token=jwtUtils.generateJwtToken(authentication);
         
		return ResponseEntity.ok(new JwtResponse(token, userDetails.getId(), userDetails.getUsername(), userDetails.getPassword(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
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
