<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>14/jstlDesc.jsp</title>
</head>
<body>
<h4>JSTL(Jsp Standard Tag Library)</h4>
<pre>
	: 커스텀 태그 라이브러리
	*** 커스텀 태그 로딩이 필요함. &lt;%@ taglib uri="태그식별" prefix="namespace" %&gt;
	&lt;namespace:tagname attribute_name="attribute_value"&gt;
	
	***Core태그(c 태그)
	1. 속성(EL변수) 지원 : set, remove
 		<c:set var="dummy" value="text" scope="session"/> 
		${dummy }
 		<c:remove var="dummy" scope="session"/>  
		flash attribute 방식
		: 속성을 한번꺼내고 바로 지우는 방식
		${dummy }
		<c:set var="dummyClone" value="${not empty dummy ? dummy: 'default'}"/>
		${dummyClone }
	2. 조건문 : if, chose_when_otherwise(자바에서 switch-case)
		<c:if test="${not empty dummy }">
			${dummy }
		</c:if>
		<c:if test="${empty dummy }">
			default
		</c:if>
		<c:choose>
			<c:when test="${not empty dummy }">
				${dummy }
			</c:when>
			<c:otherwise>
				default
			</c:otherwise>
		</c:choose>
	3. 반복문 : foreach, forTokens
		for(선언절;조건절;증감절)
		<c:forEach var="i" begin="1" step="2" end="5" varStatus="vs">
			첫번째 반복문 여부 : ${vs.first }
			마지막 반복문 여부 : ${vs.last }
			몇번째 반복문 : ${vs.count }
			${i }
		</c:forEach>
		문장의 구성요소중에 최소한의 단위를 유지할 수있는 구성요소 -> token
		<c:forTokens items="하하 호호 히히" delims=" " var="token">
			${token }
		</c:forTokens>
		<c:set var="numbers" value="1,2,3,4,5"/>
		
		<c:forTokens items="${numbers }" delims="," var="num">
			${num*100 }
		</c:forTokens>
	4. 흐름제어용 : redirect
<%-- 		<c:redirect url="/14/elDesc.jsp"/> --%>
	5. 기타 : url, out, import
		<c:url value="/14/elDesc.jsp" var="elDesc">
			<c:param name="q1" value="v1"/>
			<c:param name="q2" value="v2"/>
		</c:url>
		${elDesc }
		<c:out value="<h4>출력텍스트</h4>" escapeXml="false"/>
		
		<c:set var="htmlSource" value="<h4>출력텍스트</h4>"/>
		<c:out value="${htmlSource }" escapeXml="false" />
</pre>
<c:import url="https://www.naver.com" var="naver" />
<c:out value="${naver }" escapeXml="true"></c:out>
</body>
</html>