package com.theragenbio.gateway.config;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.ExpiredJwtException;
import reactor.core.publisher.Mono;

@Component
public class JwtRequestFilter extends AbstractGatewayFilterFactory<JwtRequestFilter.Config> {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	public JwtRequestFilter() {
		super(Config.class);
	}


	public static class Config {

	}


	private Mono<Void> handleUnAuthorized(ServerWebExchange exchange) {
		ServerHttpResponse response = exchange.getResponse();

		response.setStatusCode(HttpStatus.UNAUTHORIZED);
		return response.setComplete();
	}


	@Override
	public GatewayFilter apply(Config config) {
		// Custom Pre Filter
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			//ServerHttpResponse response = exchange.getResponse();


			// Request Header 에 Authorization 이 존재하지 않을 때
			if(!request.getHeaders().containsKey("Authorization")){
				return handleUnAuthorized(exchange); // 401 Error
			}


			// Request Header 에서 Authorization 문자열 받아오기
			List<String> token = request.getHeaders().get("Authorization");
			String requestTokenHeader = Objects.requireNonNull(token).get(0);


			String username = null;
			String jwtToken = null;
			// JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
			if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
				jwtToken = requestTokenHeader.substring(7);
				try {
					username = jwtTokenUtil.getUsernameFromToken(jwtToken);
				} catch (IllegalArgumentException e) {
					return handleUnAuthorized(exchange); // 토큰이 일치하지 않을 때
				} catch (ExpiredJwtException e) {
					return handleUnAuthorized(exchange); // 토큰이 만료되었을 때
				}
			} else {
				return handleUnAuthorized(exchange);
			}

			// Once we get the token validate it.
			if (username == null) {
				return handleUnAuthorized(exchange);
			}

			// Custom Post Filter
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				//System.out.println("Post ---");
			}));
		};
	}
}
