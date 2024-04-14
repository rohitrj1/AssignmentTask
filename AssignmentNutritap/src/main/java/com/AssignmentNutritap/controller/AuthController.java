package com.AssignmentNutritap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AssignmentNutritap.Exception.ApiException;
import com.AssignmentNutritap.payload.JwtAuthRequest;
import com.AssignmentNutritap.payload.JwtAuthResponse;
import com.AssignmentNutritap.payload.UserDto;
import com.AssignmentNutritap.security.JwtHelper;
import com.AssignmentNutritap.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private JwtHelper jwtTokenHelper;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest) throws Exception{
			this.authenticate(jwtAuthRequest.getMobileNo(),jwtAuthRequest.getPassword());
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtAuthRequest.getMobileNo());
			JwtAuthResponse authResponse = new JwtAuthResponse();
			String generateToken = this.jwtTokenHelper.generateToken(userDetails);
			authResponse.setToken(generateToken);
			return new ResponseEntity<JwtAuthResponse>(authResponse , HttpStatus.OK);
	}
	
	@PostMapping("/signUp")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) throws Exception{
		UserDto registerNewUser = this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(registerNewUser,HttpStatus.CREATED);
	}
	
	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		try {
			this.authenticationManager.authenticate(authenticationToken);
		}catch(BadCredentialsException e) {
			System.out.println("Invalid Details !!");
			throw new ApiException("Invalid username and password");
		}
	}
}
