<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>13/attrView.jsp</title>
</head>
<body>
<pre style="border: 1px solid red;">
	<%= pageContext.getAttribute("pageAttr")%>
	<%= request.getAttribute("requestAttr1")%>
	<%= request.getAttribute("requestAttr2") %>
	<%=session.getAttribute("sessionAttr") %>
	<%=application.getAttribute("applicationAttr") %>
</pre>
</body>
</html>