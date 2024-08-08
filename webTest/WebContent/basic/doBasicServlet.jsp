<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	window.onloa = function() {
		document.getElementById("getBtn").addEventListener("click", function() {
			location.href = "http://localhost/webTest/servletTest03.do";
		});
	}
</script>
</head>

<body>
<h2>Servlet 요청 연습</h2>
<hr><br>

<h3>1) Get방식 요청 1 ==> 링크 방식</h3>
<a href="http://localhost/webTest/servletTest03.do">Get 방식 요청1</a>
<br><br>

<!-- 
<form>태그의 method속성을 생략하거나 'get'으로 설정하면 GET방식으로 요청한다.
 -->
<h3>2) Get방식 요청 2 ==> form태그의 method속성 이용하기 </h3>
<form action="http://localhost/webTest/servletTest03.do" method="get">
	<input type="submit" value="Get방식 요청2">
</form> 
<br><br>

<h3>3) Get방식 요청 3 ==> JavaScript의 location속성 이용하기 </h3>
<form>
	<input type="button" value="Get방식 요청3" id="getBtn">
</form> 
<br><hr><br>

<h3>4) Post방식 요청 ==> form태그의 method속성을'post'로 설정하기</h3>
<form action="http://localhost/webTest/servletTest03.do" method="post">
	<input type="submit" value="Post방식 요청">
</form>
</body> 
</html>