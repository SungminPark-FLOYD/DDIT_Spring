<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/sessionDesc</title>
</head>
<body data-content-path="<%= request.getContextPath()%>">
<h4>session(HttpSession)</h4>
<pre>
	세션?? - timeout 속성 
	connectFull(의도적으로 연결을 수립하고 종료하는 과정을 하는 방식) 
		: connection과 session이 동일한 의미로 통로로 정의됨
	connectLess(HTTP)(연결을 의도적으로 수립하고 종료할 수 없다) 
		: 시간으로 정의됨
			- 한 세션이 시작되고, 해당 세션내에서 더이상의 명령이 발생하지 않을때까지의 기간.
		      timeout 이란, 마지막 요청이 발생하고, 이후 새로운 요청이 발생할때까지의 시간의 간격
	
	세션의 생명주기
	시작 : 특정 클라이언트가 최초의 요청을 발생시키면 세션 시작. ==> 세션 아이디가 부여됨
		세션 아이디 : <%=session.getId() %>
		<%= session.getCreationTime()%> -> 세션이 만들어진 시간
		세션 생성 시점 <%= new Date(session.getCreationTime()) %>
		세션 timeout : <%=session.getMaxInactiveInterval() %>s
		세션 내에서 마지막 요청 시점 : <%= new Date(session.getLastAccessedTime()) %>
		유지방법(tracking mode) : 클라이언트와 서버가 동일한 세션 아이디를 공유
			1) COOKIE 
			2) URL : <a href="sessionDesc.jsp;jsessionid=<%=session.getId() %>">엣지(쿠키없음)에서 세션 유지</a>
			3) SSL : secure socket layer 구조를 이용해 전송되는 모든 데이터를 암호화하는 방식

	종료
		timeout이내에 새로운 요청이 발생하지 않는 경우. => dummy session이 남게됨
		직접 세션을 invalidation 시키는 경우(가장 확실한 방법)
<%-- 		<% --%>
// 			session.invalidate();
<%-- 		%> --%>
</pre>
<h4>세션 타이머 : <span id="time-area" data-ts-timeout="<%=session.getMaxInactiveInterval() %>"></span></h4>
<!-- 인터벌로 시간 감소 -> 2:00형식으로 포멧팅 -->
<div id="msg-area">
	세션 연장 메시지
	<button class="control" id="yesBtn">예</button>
	<button class="control" id="noBtn">아니오</button>
</div>
<script src="<%=request.getContextPath() %>/resources/js/app/11/sessionDesc.js"></script>
</body>
</html>