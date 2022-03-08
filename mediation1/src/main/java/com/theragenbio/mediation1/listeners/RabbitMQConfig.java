package com.theragenbio.mediation1.listeners;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig  {
	private static final String QUEUE_NAME = "rabbitmq-queue";
	private static final String EXCHANGE_NAME = "publish-exchange";

	@Bean
	public  Queue queue() {
		return new Queue(QUEUE_NAME, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(EXCHANGE_NAME, true, false);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		return rabbitTemplate;
	}




	// CUSTOM LISTENER
	@Bean
	Queue queue1() {
		return new Queue("sample.queue1", false);
	}

	@Bean
	Queue queue2() {
		return new Queue("sample.queue2", false); 
	}

	@Bean
	Binding binding1(TopicExchange exchange) {
	    return BindingBuilder.bind(queue1()).to(exchange).with(queue1().getName());
	}

	@Bean
	Binding binding2(TopicExchange exchange) {
	    return BindingBuilder.bind(queue2()).to(exchange).with(queue2().getName());
	}
}