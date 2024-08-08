<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	List<MemberVO> memList = (List<MemberVO>)request.getAttribute("memList");
%>
<body>
<h3>회원 목록 보기</h3>
	<table border="1">
		<thead>
			<tr>
				<th colspan="5" style="text-align: right"><button>회원추가</button></th>
			</tr>
			<tr>
				<th>ID</th>
				<th>비밀번호</th>
				<th>이름</th>
				<th>전화</th>
				<th>주소</th>
			</tr>
		</thead>
		<tbody>
<%
for(MemberVO mvo : memList) {
%>
			<tr>
				<td><%= mvo.getMem_id() %></td>
				<td><%= mvo.getMem_pass()%></td>
				<td><%= mvo.getMem_name() %></td>
				<td><%= mvo.getMem_tel() %></td>
				<td><%= mvo.getMem_addr() %></td>
			</tr>
<%
}
%>
		</tbody>
	</table>
</body>
</html>