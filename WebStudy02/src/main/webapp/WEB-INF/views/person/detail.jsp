<%@page import="kr.or.ddit.vo.PersonVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<th>아이디</th>
		<td>${person.id}</td>
	</tr>
	<tr>
		<th>이름</th>
		<td>${person.name }</td>
	</tr>
	<tr>
		<th>성별</th>
		<td>${person.gender }</td>
	</tr>
	<tr>
		<th>나이</th>
		<td>${person.age }</td>
	</tr>
	<tr>
		<th>주소</th>
		<td>${person.address }</td>
	</tr>
</table>
</body>
</html>