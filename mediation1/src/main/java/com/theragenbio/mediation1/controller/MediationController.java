package com.theragenbio.mediation1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theragenbio.mediation1.interfaces.ServiceAInterface;
import com.theragenbio.mediation1.interfaces.ServiceBInterface;

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
	private String EXCHANGE_NAME = "publish-exchange";




	@GetMapping(path = "/")
	public String getAllUsers() throws Exception
	{
		logger.info("SERVICE 1 --getAllUsers--");
		return serviceA.getAllUsers();
	}

	@GetMapping(path = "/dept")
	public String getAllDepartments() throws Exception
	{
		logger.info("SERVICE 1 --getAllDepartments--");
		return serviceB.getAllDepartments();
	}


	@GetMapping("/sample/queue1")
	public String samplePublish1() {
		logger.info("SERVICE 1 --samplePublish1--");
		rabbitTemplate.convertAndSend(EXCHANGE_NAME, "sample.queue1", "queue1 success!");
		return "message sending!";
	}

	@GetMapping("/sample/queue2")
	public String samplePublish2() {
		logger.info("SERVICE 1 --samplePublish2--");
		rabbitTemplate.convertAndSend(EXCHANGE_NAME, "sample.queue2", "queue2 success!");
		return "message sending!";
	}


}
