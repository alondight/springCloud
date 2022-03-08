package com.theragenbio.auth.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.theragenbio.auth.config.JwtTokenUtil;
import com.theragenbio.auth.model.JwtResponse;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@RequestMapping(value = "/auth", method = {RequestMethod.GET,RequestMethod.POST})
	public ResponseEntity<?> createAuthenticationToken(Authentication authentication, HttpServletResponse response)
			throws Exception {

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String name = userDetails.getUsername();

		if(!authentication.isAuthenticated()) {
			response.sendRedirect("logout");
		}

		final String token = jwtTokenUtil.generateToken(name);
		return ResponseEntity.ok(new JwtResponse(token));

	}

}