package com.theragenbio.serviceA.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theragenbio.serviceA.entity.User;
import com.theragenbio.serviceA.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;


	public User saveUser(User user)
	{
		return userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User findByUserId(long id) {
		return userRepository.findByUserId(id);
	}

}
