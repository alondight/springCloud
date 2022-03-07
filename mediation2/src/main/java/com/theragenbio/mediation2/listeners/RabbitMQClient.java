package com.theragenbio.mediation2.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQClient {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RabbitListener(queues = "sample.queue")
	public void receiveMessage(Message  message ) throws Exception{
		logger.info(message.toString());
	}
}
