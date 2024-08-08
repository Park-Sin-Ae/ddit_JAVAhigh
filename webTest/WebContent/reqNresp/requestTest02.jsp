<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/webTest/requestTest02.do" method="get">
	<h2>Request 연습 form(숫자 입력은 정수로 입력하세요.)</h2>
	<hr>
	<input type="number" name="oprnd1">
	<select name="operation">
		<option value="+">+</option>
		<option value="-">-</option>
		<option value="*">*</option>
		<option value="/">/</option>
		<option value="%">%</option>
	</select>
	<input type="number" name="oprnd2">
	<input type="submit" value="확인">
</form>
</body>
</html>