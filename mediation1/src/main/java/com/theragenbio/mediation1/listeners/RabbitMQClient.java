package com.theragenbio.mediation1.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQClient {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RabbitListener(queues = "sample.queue1")
	public void receiveMessage1(Message  message ) throws Exception{
		logger.info(message.toString());
	}

	@RabbitListener(queues = "sample.queue2")
	public void receiveMessage2(Message  message ) throws Exception{
		logger.info(message.toString());
	}


}
