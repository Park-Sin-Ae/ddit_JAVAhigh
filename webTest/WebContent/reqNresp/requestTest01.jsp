<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
	HTML 주석
 -->
 
<%--
 	JSP 주석
 --%>  
 
 <%
 	// 이 영역은 JSP문서에서 JAVA 명령을 사용할 수 있는 영역이다.
 	// 이 영역은 '스크립트릿' 이라고 한다.
 	String name = "홍길동";
 	
 %>
  
<%--

  <%=변수나 수식 %> ==> JSP문서에서 변수값 또는 수식의 결과를 출력할 때 사용한다.
  // 이것을 '표현식(Expression)'이라 한다.
  
 --%>
 
 <!-- 
 <form>태그의 속성
 1) action ==> <form>태그 안에서 구성된 데이터를 받아서 처리할 문서명 또는 서블릿URL
 				생략하면 현재문서가 설정된다.
 2) method ==> 전송방식( GET 또는 POST ) ==> 기본 : GET
 3) target ==> 응답이 나타날 프레임 이름 설정 ==> 기본 : <form>태그가 있는 프레임 영역
 4) enctype ==> 서버로 파일을 전송할 때 사용하는 속성으로 
 				파일을 전송할 때는 속성값을 'multipart/form-data'로 설정한다.
  -->
 
 <h2 style="color:royalblue"><%=name %>의Request객체 연습용 Form <%= 3+4 %></h2>
 
 <form action="/webTest/requestTest01.do" method="get">
 <table border="1">
 <tr>
 	<td>이름</td>
 	<td><input type="text" size="10" name="username"></td>
 </tr>
 <tr>
 	<td>직업</td>
 	<td>
 		<select name="job">
 			<option value="학생">학생</option>
 			<option value="회사원">회사원</option>
 			<option value="전문직">전문직</option>
 			<option value="무직">무직</option>
 		</select>
 	</td>
 </tr>
 <tr>
 	<td>취미</td>
 	<td>
 		<input type="checkbox" name="hobby" value="여행">여행
 		<input type="checkbox" name="hobby" value="독서">독서
 		<input type="checkbox" name="hobby" value="게임">게임
 		<input type="checkbox" name="hobby" value="테니스">테니스
 		<input type="checkbox" name="hobby" value="배드민턴">배드민턴
 	</td>
 </tr>
 	<tr>
 		<td colspan="2" style="text-align:center;">
 			<input type="submit" value="전송">
 			<input type="reset" value="취소">
 		</td>
 	</tr>
 	
 </table>
 </form>
</body>
</html>