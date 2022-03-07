package com.theragenbio.mediation2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theragenbio.mediation2.interfaces.ServiceAInterface;
import com.theragenbio.mediation2.interfaces.ServiceBInterface;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MediationController {
	@Autowired
	ServiceAInterface serviceA;

	@Autowired
	ServiceBInterface serviceB;

	@Autowired
	RabbitTemplate rabbitTemplate;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping(path = "/")
	public String getAllUsers() throws Exception
	{
		logger.info("SERVICE 2 --SETTING--");
		return serviceA.getAllUsers();
	}

	@GetMapping(path = "/svc")
	public String getAllDepartments() throws Exception
	{
		logger.info("SERVICE 2 --SETTING--");
		return serviceB.getAllDepartments();
	}


	@GetMapping("/sample/queue")
	public String samplePublish() {
		rabbitTemplate.convertAndSend("spring-boot-exchange", "sample.queue", "RabbitMQ + Springboot = Success!");
		return "message sending!";
	}

}
