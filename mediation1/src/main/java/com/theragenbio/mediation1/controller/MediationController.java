package com.theragenbio.mediation1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MediationController {

	@GetMapping(path = "/")
	public void hello()
	{
		System.out.println("SERVICE 1 --SETTING--");
	}

}
