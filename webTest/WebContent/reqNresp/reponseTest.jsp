<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>forward, redirect 방식 연습</h3><hr>
<!-- 
  * forward 방식은 '/forwardTest01.do'에서 '/forwardTest02.do'로 
	이동하는 예제이고
  * redirect 방식은 '/redirectTest01.do'에서  '/redirectTest02.do'로
  	이동하는 예제이다
 -->
<form action="/webTest/forwardTest01.do" method = "post">
	forward 방식 요청 : <input type="text" name="username">
	<input type="submit" value="확인">
</form>
<br><hr><br>

<form action="/webTest/redirectTest01.do" method="post">
	redirect 방식 요청 : <input type="text" name="username">
	<input type="submit" value="확인">
</form>
</body>
</html>