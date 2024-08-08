<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src='<%=request.getContextPath() %>/js/jquery-3.7.1.min.js'></script>
</head>
<style>
	.main {
		
		text-align: center;
		display: block;
		margin-top : 5rem;
	}
</style>
<body>
<div class="main">
	<a href="<%=request.getContextPath()%>/memberList.do">회원 목록 보기</a>
</div>
</body>
</html>