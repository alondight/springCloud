package com.theragenbio.mediation2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Mediation2Application {

	public static void main(String[] args) {
		SpringApplication.run(Mediation2Application.class, args);
	}

}