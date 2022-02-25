package com.theragenbio.serviceA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theragenbio.serviceA.entity.User;
import com.theragenbio.serviceA.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(path = "/", produces="application/json")
	public User saveUser(@RequestBody User user)
	{
		return userService.saveUser(user);
	}


	@GetMapping(path = "/", produces="application/json")
	public List<User> getAllUsers()
	{
		return userService.getAllUsers();
	}

	@GetMapping(path = "/{userId}", produces="application/json")
	public User getUserWithDepartment(@PathVariable(name = "userId") Long userId)
	{
		return userService.findByUserId(userId);
	}

}
