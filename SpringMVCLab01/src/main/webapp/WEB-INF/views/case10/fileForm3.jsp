<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fileform3</title>
</head>
<body>
<form method="post" enctype="multipart/form-data">
	<input type="text" name="uploader" placeholder="UPLOADER"/>
	<input type="text" name="uploader" placeholder="UPLOADER"/>
	<input type="number" name="count" placeholder="COUNT" />
	<input type="file" name="uploadFile"/>
	<input type="file" name="uploadFile" multiple/>
	<button type="submit">전송</button>
</form>

<pre>
<c:forEach items="${voList }" var="fileVO">
	${fileVO }
</c:forEach>
</pre>
</body>
</html>