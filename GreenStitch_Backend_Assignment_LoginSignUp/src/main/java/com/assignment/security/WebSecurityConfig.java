package com.assignment.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.assignment.jwt.JwtGeneratorFilter;
import com.assignment.jwt.JwtValidationFilter;

@Configuration
public class WebSecurityConfig {
	
	@SuppressWarnings("removal")
	@Bean
    SecurityFilterChain mySecurity(HttpSecurity http) throws Exception{
		
		http
	    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	    .and()
	    .csrf().disable()
	    .authorizeHttpRequests()
	    .requestMatchers("/app/**").permitAll()
	    .requestMatchers(HttpMethod.GET, "/authenticated/**").hasAnyRole("USER")
	    .anyRequest().authenticated()
	    .and()
	    .addFilterAfter(new JwtGeneratorFilter(), BasicAuthenticationFilter.class)
	    .addFilterBefore(new JwtValidationFilter(), BasicAuthenticationFilter.class)
	    .formLogin()
	    .and()
	    .httpBasic();

		
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

}
