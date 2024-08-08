<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	// JSP문서에서 세션은 'session'이라는 이름으로 저장되어 있다.
	
	// 세션에 저장된 로그인 정보를 가져온다.
	String loginID = (String)session.getAttribute("loginID");
	
%>

<%
if(loginID==null){

%>
<form action="<%=request.getContextPath()%>/sessionLogin.do" method="post">
	<table border="1" style="margin: 0 auto;">
		<tr>
			<td>ID :</td>
			<td><input type="text" name="userid" placeholder="ID 입력"></td>
		</tr>
		<tr>
			<td>PASS :</td>
			<td><input type="password" name="userpass" placeholder="PassWord 입력"></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:center;">
			            	<input type="submit" value="Login">
			</td>
		</tr>
	</table>
	</form>
<%
} else {		//로그인이 되었을 때..
%>
<h2><%= loginID %>님 반갑습니다.</h2>

<br><br>

<a href="<%=request.getContextPath()%>/sessionLogout.do">로그아웃</a>
		
	<%
}
	%>
</body>
</html>