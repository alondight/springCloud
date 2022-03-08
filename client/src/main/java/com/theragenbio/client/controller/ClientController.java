package com.theragenbio.client.controller;

import java.util.Collections;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ClientController {


	private Logger logger = LoggerFactory.getLogger(this.getClass());


	@GetMapping(path = "/login")
	public String login() throws Exception
	{
		logger.info("--login--");
		return "login";
	}

	@PostMapping(path = "/loginProc")
	public String loginProc(String username, String password) throws Exception
	{
		String token = "";
		logger.info("--loginProc--");

		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8887/auth"; 
		String name = username;
		String pass = password;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		JSONObject obj = new JSONObject();
		obj.put("username",name);
		obj.put("password",pass);

		logger.info(obj.toString());
		HttpEntity<String> entity = new HttpEntity<String>(obj.toString(),headers);
		String answer = restTemplate.postForObject(url, entity, String.class);

		logger.info(answer);
		logger.info("--JWT:--"+token);
		return "main";
	}
}
