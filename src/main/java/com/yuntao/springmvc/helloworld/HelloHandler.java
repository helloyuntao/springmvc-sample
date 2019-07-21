package com.yuntao.springmvc.helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloHandler {

	@RequestMapping(value = {"/hello"})
	public String hello() {
		return "success";
	}
	
}
