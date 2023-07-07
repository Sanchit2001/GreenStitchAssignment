package com.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.exceptions.UserException;
import com.assignment.model.User;
import com.assignment.repository.UserRepository;
import com.assignment.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// register user with given details
	
	@PostMapping("/api/signup")
	public ResponseEntity<User> registerUser(@Validated @RequestBody User user) throws UserException 
	{
		user.setPassword(passwordEncoder.encode(user.getPassword())); 
         
		User newUser = userService.registerUser(user);
		
		return new ResponseEntity<User>(newUser,HttpStatus.CREATED);
	}
	
	// first time user login with Email and password and got JWT token 
	
	@GetMapping("/signIn")
	public ResponseEntity<User> getLoggedInCustomerDetailsHandler(Authentication auth) throws BadCredentialsException{
		
		User customer= userRepository.findByUsername(auth.getName());
		
		if(customer!=null)
		{
			 return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
		}
		
		throw new BadCredentialsException("Invalid Username or password");
		

	}
	
	// Authentication with JWT token 
	
	@GetMapping("/authenticated/user")
	public ResponseEntity<String> LoginUser() throws UserException
	{
		User user =  userService.loginUser();
		
		String message = "Welcome  : " +user.getFullName();
		
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
}
