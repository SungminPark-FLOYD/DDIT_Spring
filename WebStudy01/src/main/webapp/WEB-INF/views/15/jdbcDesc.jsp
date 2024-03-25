<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="kr.or.ddit.utils.NamingUtils"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.ddit.vo.DbVO"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>15/jdbcDesc.jsp</title>
</head>
<body data-context-path="${pageContext.request.contextPath }">
<h4>JDBC(Java DataBase Connectivity) 프로그래밍 방법론</h4>
<pre>
	1. jdbc 드라이버를 빌드패스에 로딩
		/WEB-INF/lib/ojdbc6.jar
	2. classloader를 이용해 드라이버를 메모리에 로딩
	3. 연결(Connection) 수립
	4. 쿼리 객체 생성
		- Statement(미리 컴파일 안함)
		- PreparedStatement(미리 컴파일함)
		- CallableStatement(PL/SQL function이나 프로시저를 호출할때 사용)
	5. 쿼리 실행
		- ResultSet executeQuery(돌아오는 결과집합이 있을때) : SELECT
		- int executeUpdate(돌아오는 결과집합이 없을때) : INSERT, UPDATE, DELETE
	6. 결과 집합 핸들링
	7. Conection, Statment, ResultSet release
<!-- 		//jstl을 사용해서 반복문 2번이상도릴면 연상배열구조를 적용해서 쓸수있다 -->

</pre>
<table>
	<thead id="head-area">
	<tr>

	</tr>
	</thead>
	<tbody id="data-area">
		
<%-- 		<c:forEach items="${list }" var="vo"> --%>
<!-- 		<tr> -->
<%-- 			<td>${vo.propertyName }</td> --%>
<%-- 			<td>${vo.propertyValue }</td> --%>
<%-- 			<td>${vo.description }</td> --%>
<!-- 		</tr> -->
<%-- 		</c:forEach> --%>
		
	</tbody>
</table>
<script src='<c:url value="/resources/js/app/15/jdbcDesc.js"/>'></script>

	

</body>
</html>