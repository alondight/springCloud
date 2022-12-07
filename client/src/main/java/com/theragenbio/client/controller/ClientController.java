package com.theragenbio.client.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ClientController {


	private Logger logger = LoggerFactory.getLogger(this.getClass());


	@GetMapping(value = {"", "/", "/index", "/login"})
	public String login() throws Exception
	{
		logger.info("--login--");
		return "login";
	}

	@PostMapping(path = "/loginProc")
	public String loginProc(String username, String password, HttpServletRequest request) throws Exception
	{
		logger.info("--loginProc--");
		String token = "";

		// response redirect condition
		RestTemplate restTemplate = new RestTemplate(); 
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(); 
		HttpClient httpClient = HttpClientBuilder.create()
				.setRedirectStrategy(new LaxRedirectStrategy()) 
				.build(); 
		factory.setHttpClient(httpClient); 
		restTemplate.setRequestFactory(factory);


		// Parameters
		String loginUrl = "http://localhost:8887/login";
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("username", username);
		parameters.add("password", password);

		// Header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		// HTTP
		HttpEntity formEntity = new HttpEntity<>(parameters, headers);

		// return
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(loginUrl, formEntity, String.class);

		try {
			JSONParser parser = new JSONParser(); 
			JSONObject json = (JSONObject) parser.parse(responseEntity.getBody().toString());
			token = json.get("token").toString();
			logger.info(token);

			// 로그인 성공 후 session에 token 저장
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("token", token);

		} catch(ParseException  e) {
			e.printStackTrace();
			logger.error("JSON parse Error");
			return "redirect:login";
		}

		return "redirect:main";
	}

	@GetMapping(path = "/main")
	public ModelAndView main(HttpServletRequest request) throws Exception
	{
		logger.info("--main--");

		// 로그인 성공 후 session에 token 저장
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		String token =    (String)session.getAttribute("token");

		if(username == null || token == null ) {
			logger.error("Login Error");

			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("redirect:login");
			return modelAndView;
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("main");

		Map<String, Object> map = new HashMap<>();
		map.put("username", username);
		map.put("token", token);
		modelAndView.addObject("data", map);

		return modelAndView;
	}

	@ResponseBody
	@GetMapping(path = "/queue")
	public String queue(HttpServletRequest request) throws Exception
	{
		logger.info("--queue--");

		// 로그인 성공 후 session에 token 저장
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		String token =    (String)session.getAttribute("token");

		if(username == null || token == null ) {
			logger.error("Login Error");

			return null;
		}

		// Parameters
		String loginUrl = "http://localhost:9090/api/sample/queue1";
		try {
			URL url = new URL(loginUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", "Bearer " + token);
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
			}
	
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(in);
			return br.toString();

		} catch (Exception e) {
			logger.info("Exception in NetClientGet:- " + e);
		}

		return null;
	}	
	
}
