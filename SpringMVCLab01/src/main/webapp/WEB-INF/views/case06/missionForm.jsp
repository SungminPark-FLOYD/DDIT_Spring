<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="calForm" method="post">
	<input type="number" name="leftOp" value="${leftOp }"/>
	<input type="number" name="rightOp" value="${rightOp }"/>
	<button type="submit">=</button>
	<span id="result-area">${result }</span>
</form>

<script src="<c:url value='/resources/js/case06/missionForm.js'/>"></script>
</body>
</html>