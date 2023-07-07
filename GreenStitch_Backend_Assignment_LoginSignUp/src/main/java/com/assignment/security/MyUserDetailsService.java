package com.assignment.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.assignment.model.User;
import com.assignment.repository.UserRepository;


@Service
public class MyUserDetailsService  implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user  = userRepository.findByUsername(username);
		
		if(user!=null)
		{
			return new MyUserDetails(user);
		}
		
		throw new UsernameNotFoundException("user not found with this username : "+username);
	}
 
}
