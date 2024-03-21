<%@page import="kr.or.ddit.vo.BtsVO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>14/elDesc.jsp</title>
</head>
<body>
<h4>EL(Expression Language, 표현 언어)</h4>
<pre>
	값을 출력하기 위한 목적으로 사용되는 스크립트 언어
	표현식을 대체하는 용도로 사용됨. 
	-> el 안에서 사용되는 데이터는 scope를 통해서 공유되고 있는 Attribute만 사용가능
	: scope 상관없이 속셩 명을 기준으로 값을 찾아서 가져온다.
	EL은 pageContext를 유일하게 사용한다
	EL은 속성명이 일치 하지 않으면 공백이 출력된다.
	연산자 : 할당연산자, concat연산자, 증감연산자 지원안됨
	<%=23 %>, ${23} 
	<%
		String text = "TEXT";
		session.setAttribute("textAttr", text);
	%>
	<%=text %>, ${textAttr1} ,<%= session.getAttribute("textAttr") %>
	pageContext : ${pageContext },
	request : ${pageContext.request },
	session : ${pageContext.session },
	<%
		pageContext.setAttribute("attr", "23");
	%>
	
	산술연산자 : ${23+12 }, ${attr+12 }, ${attr * 12 }, ${attr / 12 }, \${attr++ }
	논리연산자 : &&(and), ||(or), !(not)
		 ${true and true} , ${dummy and true }, ${not true }, ${not dummy }
	비교 연산자 : >(gt), <(lt), ==(eq), >=(ge) , <=(le), !=(ne)
		${23 ne 35}, ${23 le 35}, ${23 ge 35 }
	삼항연산자 : 논리값? 참:거짓
		${23 lt 35 ? '작다' : '크거나 같다' }
	<%
		pageContext.setAttribute("dummy", "  ");
		List sample = new ArrayList();
		sample.add("element");
		pageContext.setAttribute("sample", sample);
		
		Map map = new HashMap();
		map.put("key1", "value1");
		map.put("key2", "value2");
		pageContext.setAttribute("dummyMap", map);
		
		BtsVO bts = new BtsVO("B001","뷔", "path", 100);
		pageContext.setAttribute("bts", bts);
	%>
	단항연산자 : empty
		${empty dummy }, ${not empty dummy }
		sample의 존재여부 : ${empty sample ? "비어있다" : "비어있지 않다" }
		${sample[0] }
		${dummyMap.get("key1") }, ${dummyMap.key1 }, ${dummyMap['key1'] }
		${bts }, ${bts.name }, ${bts['name'] }
</pre>
<script type="text/javascript">
	let attr = 35;
	console.log(`\${attr}`)
	
</script>
</body>
</html>