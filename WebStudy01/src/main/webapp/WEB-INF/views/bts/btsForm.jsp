<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/includee/preScript.jsp"/>
</head>
<body>


<form method="post" name="btsForm" enctype="application/x-www-form-urlencoded">
	<select name="member" onchange="this.form.requestSubmit();" required>
		<option value>선택</option>
		<c:forEach items="${btsMap}" var="bts" varStatus="status">		
			<option value="${bts.key}" label="${bts.value[0]}"/>			
		</c:forEach>
	</select>
</form>
<div id="bts-area"></div>
<script src="<%=request.getContextPath()%>/resources/js/bts/btsForm.js"></script>
<jsp:include page="/WEB-INF/includee/postScript.jsp"/>
</body>
</html>