<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.folder{
		text-decoration: underline;
		color:blue;
	}
</style>
<script type="text/javascript">
document.addEventListener("dblclick", e =>{
	if(!e.target.classList.contains("folder")) return false;
	let li = e.target;
	console.log(li.id);
	location.href='?base='+li.id
	//location.href= "";
});
</script>
</head>
<body>
	<c:set var="list" value="${fileList }"/>
	<ul>
		<c:forEach items="${fileList }" var="fl">
			<li id="${fl.key }" class="${fl.value.file? 'file' : 'folder'}">${fl.value.name }</li>		
		</c:forEach>		
	</ul>
</body>
</html>