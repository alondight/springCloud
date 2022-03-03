package com.theragenbio.mediation1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theragenbio.mediation1.interfaces.ServiceAInterface;

@RestController
public class MediationController {
	@Autowired
	ServiceAInterface serviceA;

	@GetMapping(path = "/")
	public String getAllUsers() throws Exception
	{
		System.out.println("SERVICE 1 --SETTING--");
		return serviceA.getAllUsers();
		
	}

}
