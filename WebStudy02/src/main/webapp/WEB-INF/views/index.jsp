<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>Principal : ${pageContext.request.userPrincipal }</h4>
<c:choose>
	<c:when test="${not empty sessionScope.authMember}">
		<h1>환영합니다 <a href="<c:url value='/mypage' />">${authMember.memName }</a> 님.</h1>
		<form id="logoutForm" method="post"></form>
		<a href="<c:url value='/login/logout.do'/>" class="logoutBtn" data-target-form="#logoutForm">로그아웃</a>
		<script type="text/javascript">
			document.querySelector("a[data-target-form]").addEventListener("click", e => {
				e.preventDefault();
				let aTag = e.target;
				let formSelector = aTag.dataset.targetForm;
				
				let url = aTag.href;
				let form = document.querySelector(formSelector);
				form.action = url;
				form.requestSubmit();
				
			});
		</script>
	</c:when>
	<c:otherwise>
		<a href="<c:url value='/login/loginForm.jsp'/>">로그인 페이지 이동</a>
		<a href="<c:url value='/member/memberInsert.do'/>">회원가입</a>
	</c:otherwise>

</c:choose>
<!-- 로그인 여부를 판단하고, -->
<!-- 로그인이 된경우 해당 사용자의 이름을 출력 -->
<!-- 로그인 전인 경우 로그인 페이지로 이동할 수 있는 a태그 출력 -->
</body>
</html>