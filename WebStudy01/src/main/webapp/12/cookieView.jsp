<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>동일 경로에서 쿠키 확인</h4>
<pre>
<%
	Cookie[] cookies = request.getCookies();
	String findedValue = null;
	if(cookies != null) {
		for(Cookie single : cookies) {
			String name = single.getName();
			String value = URLDecoder.decode(single.getValue(), "UTF-8");
			out.println(
				String.format("%s : %s", name, value)		
			);
			
		}
	}
%>
</pre>
</body>
</html>