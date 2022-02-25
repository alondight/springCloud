package com.theragenbio.mediation1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Mediation1Application {

	public static void main(String[] args) {
		SpringApplication.run(Mediation1Application.class, args);
	}

}
