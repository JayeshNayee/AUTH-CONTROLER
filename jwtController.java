package com.newVjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newVjwt.RepositryInterface.RepositryInterFace;
import com.newVjwt.implemenTation.jwtImplemenTation;
import com.newVjwt.jwtRequestResponce.jwtReponce;
import com.newVjwt.jwtRequestResponce.jwtRequest;
import com.newVjwt.jwtUtil.jwtUtil;
import com.newVjwt.loginEntity.login;

@RestController
public class jwtController {
	
	@Autowired RepositryInterFace repoObj;
	
	@Autowired jwtUtil jwtUtil;
	
	@Autowired AuthenticationManager authenticationManager;
	
	@Autowired jwtImplemenTation uDetailsService;
	

	@PostMapping(value = "/loginApi")
	public ResponseEntity<String> loginPage(){
		return new ResponseEntity<String>("Home page !! working will be done ", HttpStatus.OK);
	}
	
	@PostMapping(value = "/cheack-token")
	public ResponseEntity<String>cheackSecurity(){
		return new ResponseEntity<String>("Working Success-fully", HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value = "/get-token")
	public ResponseEntity<jwtReponce> loginProfile(@RequestBody jwtRequest jwtRequest) throws BadCredentialsException{
		
	try {
		// first Authenticate
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassWord()));
		
	} catch (Exception e) {
		e.getMessage();
	}
	 // cheack userName  using USERDETAILSERVICE
	UserDetails UserDetails =this.uDetailsService.loadUserByUsername(jwtRequest.getUserName());
	// third step : generate-token pass it Userdetails  
	String token = jwtUtil.generateToken(UserDetails);
		// set jwtReponse 
	jwtReponce jwtReponce  = new jwtReponce();
	jwtReponce.setToken(token);
	// return it
		return new ResponseEntity<jwtReponce>(jwtReponce, HttpStatus.OK);
		
	}
}
