package com.yuntao.springmvc.helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloHandler {

	/*
	 * 1.使用 @RequestMapping 来映射请求的 URL
	 * 2.返回值会通过视图解析器解析为实际的物理视图，对于 InternalResourceViewResolver 视图解析器
	 * 会做如下的解析：通过 prefix + 目标方法返回值 + suffix 得到实际的物理视图，然后做转发操作
	 * /WEB-INF/views/success.jsp
	 */
	@RequestMapping(value = {"/hello"})
	public String hello() {
		System.out.println("hello springmvc.");
		return "success";
	}
	
}
