package com.demo.client2.service;

import com.demo.client2.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@SpringBootTest(classes = App.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ApiServiceTest {

	@Autowired
	private ApiService apiService;

	@Test
	public void test(){
		try {
			System.out.println(apiService.index());
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}