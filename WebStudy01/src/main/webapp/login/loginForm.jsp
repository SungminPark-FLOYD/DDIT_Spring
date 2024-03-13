<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	
%>
</head>
<body>
<form action="<%=request.getContextPath() %>/login/loginProcess.do" method="post" enctype="application/x-www-form-urlencoded">
	<input type="text" name="memId" placeholder="아이디"/>
	<input type="pass" name="memPass" placeholder="비밀번호"/>
	<button type="submit">로그인</button>
</form>
</body>
</html>