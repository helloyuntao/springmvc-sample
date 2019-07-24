package com.yuntao.springmvc.test;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.yuntao.springmvc.entities.User;

@Controller
@RequestMapping("/springmvc")
@SessionAttributes(value = {"user"}, types = {String.class})
public class SpringMVCTest {

	private static final String SUCCESS = "success";
	
	/*
	 * @SessionAttributes 除了可以通过属性名（value/name）指定需要放到 Session 域中的属性外，
	 * 还可以通过模型属性的对象类型（types）指定哪些模型属性需要放到 Session 域中
	 */
	@RequestMapping("/testSessionAttributes")
	public String testSessionAttributes(Map<String, Object> map) {
		map.put("user", new User("Tom", "1234", "tom@gmail.com", 16));
		map.put("pet", "meow");
		return SUCCESS;
	}
	
	/*
	 * 目标方法可以添加 Map 类型的参数
	 * （实际上也可以是 Model 类型或 ModelMap 类型）
	 */
	@RequestMapping("/testMap")
	public String testMap(Map<String, Object> map) {
		// org.springframework.validation.support.BindingAwareModelMap
		System.out.println(map.getClass().getName());
		map.put("names", Arrays.asList("tom", "jerry"));
		return SUCCESS;
	}
	
	/*
	 * 目标方法的返回值可以是 ModelAndView 类型
	 * 其中可以包含视图和模型信息
	 * SpringMVC 会把 ModelAndView 的 model 中数据放入到 request 域对象中
	 */
	@RequestMapping("/testModelAndView")
	public ModelAndView testModelAndView() {
		String viewName = SUCCESS;
		ModelAndView mv = new ModelAndView(viewName);
		mv.addObject("time", new Date());
		return mv;
	}
	
	/*
	 * Handler 方法可接受的如下 ServletAPI 类型的参数：
	 * HttpServletRequest、HttpServletResponse、HttpSession、
	 * java.security.Principal、Locale、
	 * InputStream、OutputStream、
	 * Reader、Writer
	 */
	@RequestMapping("/testServletAPI")
	public void testServletAPI(HttpServletRequest request, HttpServletResponse response,
			Writer out) throws IOException {
		System.out.println("testServletAPI, " + request + ", " + response);
		out.write("test writer");
//		return SUCCESS;
	}
	
	/*
	 * SpringMVC 会按请求参数名和 POJO 属性名进行自动匹配，自动为该对象填充属性值
	 * 支持级联属性，e.g. address.city
	 */
	@RequestMapping("/testPojo")
	public String testPojo(User user) {
		System.out.println("testPojo: " + user);
		return SUCCESS;
	}
	
	/*
	 * @CookieValue 映射一个 Cookie 值
	 * value/name：为 Cookie Name
	 * 其他属性同 @RequestParam
	 */
	@RequestMapping("/testCookieValue")
	public String testCookieValue(@CookieValue("JSESSIONID") String jSessionId) {
		System.out.println("testCookieValue, jSessionId: " + jSessionId);
		return SUCCESS;
	}
	
	/*
	 * @RequestHeader 映射请求头信息
	 * 用法同 @RequestParam
	 */
	@RequestMapping("/testRequestHeader")
	public String testRequestHeader(@RequestHeader(name = "Accept-Language") String acceptLanguage) {
		System.out.println("testRequestHeader, acceptLanguage: " + acceptLanguage);
		return SUCCESS;
	}
	
	/*
	 * @RequestParam 映射请求参数
	 * value/name：为请求参数的参数名
	 * required：该参数是否必须，默认为 true
	 * defaultValue：请求参数的默认值
	 */
	@RequestMapping("/testRequestParam")
	public String testRequestParam(@RequestParam("username") String username,
			@RequestParam(value = "age", required = false, defaultValue = "1") int age) {
		System.out.println("testRequestParam, username: " + username + ", age: " + age);
		return SUCCESS;
	}
	
	/*
	 * 如何发送 PUT 请求和 DELETE 请求呢？
	 * 1.需要配置 org.springframework.web.filter.HiddenHttpMethodFilter
	 * 2.需要发送 POST 请求
	 * 3.需要在发送 POST 请求时携带一个 name="_method" 的隐藏域，值为 "PUT" 或者 "DELETE"
	 * 
	 * REST 风格的 URL
	 * 以 CRUD 为例：
	 * 新增：/order POST
	 * 修改：/order/1 PUT     /update?id=1
	 * 获取：/order/1 GET     /get?id=1
	 * 删除：/order/1 DELETE  /delete?id=1
	 */
	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.DELETE)
	public String testRestDelete(@PathVariable("id") Integer id) {
		System.out.println("testRest DELETE: " + id);
		return SUCCESS;
	}
	
	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.PUT)
	public String testRestPut(@PathVariable("id") Integer id) {
		System.out.println("testRest PUT: " + id);
		return SUCCESS;
	}
	
	@RequestMapping(value = "/testRest", method = RequestMethod.POST)
	public String testRest() {
		System.out.println("testRest POST.");
		return SUCCESS;
	}
	
	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.GET)
	public String testRest(@PathVariable("id") Integer id) {
		System.out.println("testRest GET: " + id);
		return SUCCESS;
	}
	
	/*
	 * @PathVariable 注解可以来映射 URL 中的占位符到目标方法的参数中
	 */
	@RequestMapping("/testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") Integer id) {
		System.out.println("testPathVariable: " + id);
		return SUCCESS;
	}
	
	/*
	 * 支持 Ant 风格的映射路径
	 */
	@RequestMapping("/testAntPath/*/xyz")
	public String testAntPath() {
		System.out.println("testAntPath.");
		return SUCCESS;
	}
	
	/*
	 * 可以使用 params 和 headers 来更加精确的映射请求
	 * params 和 headers 支持简单的表达式
	 */
	@RequestMapping(value = "/testParamsAndHeaders", params = {"username", "age!=18"},
			headers = {"Accept-Language=en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7"})
	public String testParamsAndHeaders() {
		System.out.println("testParamsAndHeaders.");
		return SUCCESS;
	}
	
	/*
	 * 使用 method 属性指定请求方式
	 */
	@RequestMapping(value = {"/testMethod"}, method = RequestMethod.POST)
	public String testMethod() {
		System.out.println("testMethod.");
		return SUCCESS;
	}
	
	/*
	 * @RequestMapping 注解除了修饰方法，还可以修饰类
	 * 类定义处：提供初步的请求映射信息，相对于 web 应用的根目录
	 * 方法定义处：提供进一步的细分映射信息，相对于类定义处的 URL。
	 * 若类定义处未标注 @RequestMapping，则方法处标记的 URL 相对于 web 应用的根目录
	 */
	@RequestMapping("/testRequestMapping")
	public String testRequestMapping() {
		System.out.println("testRequestMapping.");
		return SUCCESS;
	}
	
}
