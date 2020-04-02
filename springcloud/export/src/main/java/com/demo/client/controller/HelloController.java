package com.demo.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class HelloController {
	@Value("${server.port}")
	private int port;

	@Value("${test.str}")
	private String str;

	@RequestMapping("index")
	public String index(){
		return "Hello World!,端口："+port;
	}
	@RequestMapping("name")
	public String name(){
		return "Hello World!,name："+str;
	}
}


