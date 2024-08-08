<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<h2>cookie 연습용 main페이지 입니다.</h2>
	<br><br>
	<a href="<%=request.getContextPath()%>/cookie/cookieLogin.jsp">Login 창으로 이동</a>
	
	<form action="login"></form>
	<label></label>
</body>
</html>