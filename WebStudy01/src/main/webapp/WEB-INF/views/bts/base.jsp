<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<h4><%=request.getHeader("user-agent") %></h4>
<main>
	<%
		String content = (String)request.getAttribute("content");
	%>
	<jsp:include page="<%=content %>"/>
</main>

