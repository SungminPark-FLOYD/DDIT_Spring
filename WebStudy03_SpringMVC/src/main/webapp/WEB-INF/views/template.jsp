<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<tiles:insertAttribute name="preScript"/>
	<c:if test="${not empty message}">
		<script type="text/javascript">
			alert("${message}");
		</script>
	</c:if>
</head>
<body data-context-path="${pageContext.request.contextPath }">
	<div class="container">
		<main>
			<tiles:insertAttribute name="content"/>
		</main>
	</div>
	<tiles:insertAttribute name="postScript"/>	
</body>
</html>