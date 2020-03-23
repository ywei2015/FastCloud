package com.demo.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;



@RestController
public class SessionController {

	@GetMapping("getSessionInfo")
	public Mono<Object> mono(ServerWebExchange exchange, @RequestParam String attribute) {
		return Mono.just(1);
	}

}
