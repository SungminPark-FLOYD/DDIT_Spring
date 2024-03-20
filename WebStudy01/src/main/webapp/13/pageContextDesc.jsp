<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	public String data = "DATA";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>13/pageContextDesc.jsp</title>
</head>
<body>
<h4>pageContext(PageContext)</h4>
<pre>
	기본객체 중 가장 먼저 생성되고, 나머지 기본객체에 대한 참조를 가진 객체.
	<%= request == pageContext.getRequest()%>
	<%= session == pageContext.getSession()%>
	<%= request.getContextPath() %>, ${pageContext.request.contextPath }
	
	Scope(Map) : 웹 어플리케이션에서 공유 데이터를 저장하기 위한 저장소.
		 -> 생명주기가 다른 4개의 기본객체가 가진 map.
		scope 를 통해 공유되는 name/value로 구성된 데이터 : attribute
	1. page scope(pageContext) : 한 페이지 안에서 접근 가능
		pageContext가 관리하는 map
	2. request scope : 한 request안에서만 접근 가능
		request가 관리하는 map
	3. session scope : 한 session 안에서만 접근 가능
		session이 관리하는 map
	4. application scope(servletContext) : 같은 application안에서 접근 가능(servletContext는 싱글톤으로 생성되기 때문에 저장소가 하나다)
		servletContext가 관리하는 map
	
	setAttribute(name, value), getAttribute(name), removeAttribute(name)
	
	<%
		pageContext.setAttribute("pageAttr", "페이지 속성");
		request.setAttribute("requestAttr1", "요청 속성1");
		pageContext.setAttribute("requestAttr2", "요청 속성2", PageContext.REQUEST_SCOPE);
		session.setAttribute("sessionAttr","세션 속성");
		application.setAttribute("applicationAttr" , "어플리케이션 속성");
		
		//request.getRequestDispatcher("/13/attrView.jsp").include(request, response);
		response.sendRedirect(request.getContextPath() + "/13/attrView.jsp");
	%>
<!-- 	<a href="attrView.jsp">속성 확인</a> -->
</pre>
</body>
</html>