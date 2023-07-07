package com.assignment.service;

import com.assignment.exceptions.UserException;
import com.assignment.model.User;

public interface UserService {

	public User registerUser(User user) throws UserException;
	public User loginUser()  throws UserException;
}
