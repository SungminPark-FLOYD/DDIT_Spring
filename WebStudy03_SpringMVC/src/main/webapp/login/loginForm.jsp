<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<c:if test="${not empty message}">
<script type="text/javascript">
	alert("${message}");
</script>
</c:if>
<c:remove var="message" scope="session"/>
</head>
<body>

<form action="<c:url value='/login/loginProcess.do'/>" method="post" enctype="application/x-www-form-urlencoded">
	<input type="text" name="memId" placeholder="아이디"/>
	<input type="password" name="memPass" placeholder="비밀번호"/>
	<button type="submit">로그인</button>
</form>
</body>
</html>