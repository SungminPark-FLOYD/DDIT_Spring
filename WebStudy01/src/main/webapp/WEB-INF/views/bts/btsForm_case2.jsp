<%@page import="kr.or.ddit.vo.BtsVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.omg.PortableInterceptor.ClientRequestInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/includee/preScript.jsp"/>
</head>
<body>
<%
	ArrayList<BtsVO> btsList = (ArrayList<BtsVO>)request.getAttribute("btsList");
%>
<form method="post" name="btsForm" enctype="application/x-www-form-urlencoded">
	<select name="member" onchange="this.form.requestSubmit();" required>
		<option value>선택</option>
		<%
			for(BtsVO bts : btsList) {
				%>
				<option value="<%=bts.getCode() %>" label="<%=bts.getName()%>"/>
				<%
			}
		%>
	</select>
</form>
<div id="bts-area"></div>
<script src="<%=request.getContextPath()%>/resources/js/bts/btsForm.js"></script>
<jsp:include page="/WEB-INF/includee/postScript.jsp"/>
</body>
</html>