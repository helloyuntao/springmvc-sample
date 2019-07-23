<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<a href="springmvc/testCookieValue">Test CookieValue</a>
	<br><br>
	
	<a href="springmvc/testRequestHeader">Test RequestHeader</a>
	<br><br>
	
	<a href="springmvc/testRequestParam?username=yuntao&age=27">Test RequestParam</a>
	<br><br>

	<form action="springmvc/testRest/1" method="post">
		<input type="hidden" name="_method" value="DELETE">
		<input type="submit" value="Test Rest DELETE">
	</form>
	<br><br>

	<form action="springmvc/testRest/1" method="post">
		<input type="hidden" name="_method" value="PUT">
		<input type="submit" value="Test Rest PUT">
	</form>
	<br><br>

	<form action="springmvc/testRest" method="post">
		<input type="submit" value="Test Rest POST">
	</form>
	<br><br>

	<a href="springmvc/testRest/1">Test Rest GET</a>
	<br><br>

	<a href="springmvc/testPathVariable/123">Test PathVariable</a>
	<br><br>
	
	<a href="springmvc/testAntPath/syt/xyz">Test AntPath</a>
	<br><br>
	
	<a href="springmvc/testParamsAndHeaders?username=yuntao&age=27">Test ParamsAndHeaders</a>
	<br><br>
	
	<form action="springmvc/testMethod" method="post">
		<input type="submit" value="Submit">
	</form>
	<br><br>
	
	<a href="springmvc/testMethod">Test Method</a>
	<br><br>
	
	<a href="springmvc/testRequestMapping">Test RequestMapping</a>
	<br><br>
	
	<a href="hello">Hello SpringMVC</a>

</body>
</html>