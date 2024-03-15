<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/includee/preScript.jsp"/> <!-- custom태그. 서버안에서만 이동하는거기 때문에! WEB-INF접근 가능-->
</head>
<body>
<div class="container">
<div class="card"> 
		<jsp:include page="/09/fragment1.jsp"/> <!-- custom태그 1.namespace가 필요하다(:앞에 있는 단어) 2.서버사이드코드임(java코드임)-ctrl+shift+c 하면 html주석이 아님. 가독성문제를 해결할수 있음 -->
		<%-- <iframe src="<%=request.getContextPath() %>/09/fragment1.jsp"></iframe> --%> <!-- context가 식별이 안되기 때문에 여기에는 path지정해야함 -->
</div>
<div class="card">
 	<jsp:include page="/09/fragment2.jsp"/>
</div>
</div>
<jsp:include page="/WEB-INF/postScript.jsp" />

</body>
</html>