<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>File Upload 연습</h3>
<!-- 파일 업로드 할 때는 method는 반드시 post 방식이어야 하며 -->
<!-- enctype이 반드시 있어야 한다. -->
<form action="<%= request.getContextPath() %>/fileupload/fileupload.do" method="post" enctype="multipart/form-data">	
	- 작성자 이름 : <input type="text" name="username"><br><br>
	- 한 개의 파일 선택 : <input type="file" name="upFile"><br><br>
	- 다중 파일 선택 : <input type="file" name="upFile2" multiple><br><br>
	<input type="submit" value="전 송">
</form>
<br><hr><br>

<a href="<%= request.getContextPath() %>/fileupload/fileupload.do"></a>
</body>
</html>