package com.assignment.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.assignment.model.User;
import com.assignment.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User registerUser(User user) {
		
		return userRepository.save(user);
	}

	@Override
	public User loginUser() {
			
		SecurityContext sc  = SecurityContextHolder.getContext();
		
		Authentication auth  = sc.getAuthentication();
		
		String userName = auth.getName();
		
		User user = userRepository.findByUsername(userName);
		
		return user;
		
	}
	
}
