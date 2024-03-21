<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="url" value="${pageContext.request.parameterMap.url[0] }"/>
<c:set var="view" value="${pageContext.request.parameterMap['view-source'][0] }"/>

<form>
	<input type="text" name="url" value="${url }"/>
	<input type="checkbox" name="view-source" value="true" checked="${view ? 'checked' : '' }"/>소스로 보기
	<button type="submit">브라우징</button>
</form>

<div>
	<c:if test="${not empty url }">
		<c:import url="${url }" var="site" />
		<c:out value="${site }" escapeXml="${view eq true }"/>
	</c:if>
</div>
</body>
</html>