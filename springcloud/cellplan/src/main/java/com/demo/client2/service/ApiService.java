package com.demo.client2.service;

import com.demo.client2.service.iml.ApiServiceError;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "export" ,fallback = ApiServiceError.class)
public interface ApiService {

	@RequestMapping(value = "/index",method = RequestMethod.GET)
	String index();
}
