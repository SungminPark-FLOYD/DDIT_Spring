<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4>Principal : ${pageContext.request.userPrincipal }</h4>
<c:set value="${pageContext.request.userPrincipal }" var="principal"/>
<c:choose>
	<c:when test="${not empty principal}">
	
	</c:when>
	<c:otherwise>
		<a href="<c:url value='/login/loginForm.jsp'/>">로그인 페이지 이동</a>
		<a href="<c:url value='/member/memberInsert.do'/>">회원가입</a>
	</c:otherwise>

</c:choose>
