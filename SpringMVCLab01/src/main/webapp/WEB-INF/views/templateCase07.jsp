<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles"  prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<tiles:insertAttribute name="preScript" />
</head>
<body>
<h4>공통 레이아웃 적용 Case07(Tiles)</h4>
<main>
	<tiles:insertAttribute name="content"/>
</main>
</body>
</html>