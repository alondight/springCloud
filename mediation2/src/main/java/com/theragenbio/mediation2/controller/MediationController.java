package com.theragenbio.mediation2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theragenbio.mediation2.interfaces.ServiceAInterface;

@RestController
public class MediationController {
	@Autowired
	ServiceAInterface serviceA;

	@GetMapping(path = "/")
	public String getAllUsers() throws Exception
	{
		System.out.println("SERVICE 2 --SETTING--");
		return serviceA.getAllUsers();
		
	}

}
