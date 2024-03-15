<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h4>MBTI 유형입니다.</h4>
<%

	String content = (String) request.getAttribute("content");

%>
<jsp:include page="<%=content %>"/>