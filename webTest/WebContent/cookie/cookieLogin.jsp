<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<!-- 쿠기값 가져오가 -->
<%-- <% --%>

// Cookie[] = cookieArr = request.getCookies();

// String cookieUserId = "";		// 쿠키값(id)이 저장될 변수
// String strCheck = "";			// 체크박스 체크용 변수


// if(cookieArr!=null){
// 	for(Cookie cookie : cookieArr) {
// 		ir("USERID".equals(cooki.getName())){	// 쿠키가 있는지 검사
// 			cookieUserId = cookie.getValue();
// 			strCheck = "checked";
// 		}
// 	}
// }



<%-- %> --%>
	<form action="<%=request.getContextPath()%>/cookieLoginServelet.do/" method="post">
	<table style="margin: 0 auto;">
		<tr>
			<td>ID :</td>
			<td><input type="text" name="userid" placeholder="ID 입력"></td>
		</tr>
		<tr>
			<td>PASS :</td>
			<td><input type="password" name="userpass" placeholder="PassWord 입력"></td>
		</tr>
		
<!-- 		<tr> -->
<%-- 			<td colspan="2"><input type="checkbox" name="chkid" value="check" <%= strCheck %>>ID 기억하기</td> --%>
<!-- 		</tr> -->
		
		<tr>
			<td colspan="2" style="text-align:center;">
			            	<input type="submit" value="Login">
			</td>
		</tr>
	</table>
	</form>
	
	
</body>
</html>