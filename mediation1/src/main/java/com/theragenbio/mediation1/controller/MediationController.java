package com.theragenbio.mediation1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theragenbio.mediation1.interfaces.ServiceAInterface;
import com.theragenbio.mediation1.interfaces.ServiceBInterface;

@RestController
public class MediationController {
	@Autowired
	ServiceAInterface serviceA;

	@Autowired
	ServiceBInterface serviceB;

	@GetMapping(path = "/")
	public String getAllUsers() throws Exception
	{
		System.out.println("SERVICE 1 --SETTING--");
		return serviceA.getAllUsers();
	}

	@GetMapping(path = "/svc")
	public String getAllDepartments() throws Exception
	{
		System.out.println("SERVICE 1 --SETTING--");
		return serviceB.getAllDepartments();
	}

}
