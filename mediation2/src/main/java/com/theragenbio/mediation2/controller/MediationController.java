package com.theragenbio.mediation2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MediationController {

	@GetMapping(path = "/", produces="application/json")
	public void hello()
	{
		System.out.println("SERVICE 2 --SETTING--");
	}

}
