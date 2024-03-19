<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>12/servletContextDesc.jsp</title>
</head>
<body>
<h4>servletContext application 기본 객체</h4>
<pre>
	: 서버와 현재 컨텍스트에 대한 정보를 가진 싱글톤 객체. 서블릿 스펙의 모든 객체를 ServletContext 참조를 가지고 있음
	
	1. 서버 정보 확보 : <%= application %>, <%=request.getServletContext() %>, <%=session.getServletContext() %>
		<%= application.getServerInfo() %>
		<%= application.getMajorVersion() %>.<%= application.getMinorVersion() %>
	2. 특정 파일의 mime 타입 조회
		<%= application.getMimeType("dummy.jpg") %>
	3. 컨텍스트 파라미터 확보(컨텍스트 -> 하나의 어플리케이션)
		<%= application.getInitParameter("param1") %>
	4. 로그 기록
		<%
			application.log("로그 기록 메시지");
		%>
	<a href="resourceType.jsp">5.(*****) 웹 리소스 확보</a>
</pre>
</body>
</html>