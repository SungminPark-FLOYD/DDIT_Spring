<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/standard.jsp</title>
</head>
<body>
<h4>JSP(Java Server Page)</h4>
<pre>
	: 자바와 서블릿 스펙을 기반으로 SSR을 구현하는 템플릿 엔진.
	SSR(Server Side Rendering) : document의 모든 엘리먼트가 서버에서 텍스트 템플릿으로 생성되는 구조(JSP)
	CSR(Client Side Rendering) : 엘리먼트는 클라이언트측에서 생성되고, 서버에서 데이터만 서비스 받는 구조.(React, Vue.js)
	
	jsp 문법 구성요소
	1. 정적 텍스트 : 일반텍스트, HTML, JS, CSS ->브라우저에서 렌더링 전에는 일반 텍스트
	2. 스크립틀릿(scriptlet, 동적 요소를 위한 백그라운드 코드)
		1) scriptlet : <% //java code %> &lt; // javacode %&gt; ->정적인 텍스트로 전송된다 ->_JspService내의 지역 코드 작성에 사용
		<%
			//블럭변수 > 지역변수 > 인스턴스 변수( 전역변수지만 반드시 값을 생성해야 하는 변수) > static 정적(클래스변수)
			String data = "데이터";
			LocalDate now = LocalDate.now();
			
		%>
		2) directive(지시자) :   &lt;%@ %&gt; 아무것도 입력하지않으면 500에러 발생 -> server side에서 에러발생 -> background 언어		
		--> 실행 코드와 무관하게 jsp 페이지에 대한 부가적인 환경 설정에 사용
			- page(required) 
			- taglib(optional) - custom tag 로딩에 사용
			- include(optional) - 정적 include에 사용
			
		3) expression(표현식) : &lt;%= 출력할 값이나 표현식%&gt;
		<%= data %>, <%= now%>
		<% out.println(now); %>
		4) declaration(선언부) : &lt;%! %&gt;
		<%! 
			static String staticData = "전역 데이터";
			void dummy() {}
		%>
		5) comment(주석) : <%-- --%> -> 주석은 server side언어로 돌아간다
			주석의 종류
			- Client side comment : HTML, JS, CSS comment -> 가능한 사용하지 않기(보안 / 데이터 손실)
			- Server side comment : Java, JPS comment
			<!-- HTML 주석 -->
<!-- 			<style type="text/css"> -->
<!-- /* 				body { */ -->
<!-- /* 					background-color: red;  */ -->
<!-- /* 				} */ -->
<!-- 			</style> -->
			<script type="text/javascript">
// 				var dummy = "더미"
			</script>
			<%-- JSP 주석 : 응답데이터에 포함되지 얺는다 --%>
</pre>
</body>
</html>