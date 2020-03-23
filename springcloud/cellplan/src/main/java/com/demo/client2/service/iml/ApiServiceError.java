package com.demo.client2.service.iml;

import com.demo.client2.service.ApiService;
import org.springframework.stereotype.Component;

@Component
public class ApiServiceError implements ApiService {
	@Override
	public String index() {
		return "服务发生故障";
	}
}
