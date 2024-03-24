<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/src/jquery-ui-dependencies/jquery.fancytree.ui-deps.js"></script>
<link href="${pageContext.request.contextPath }/resources/dist/skin-lion/ui.fancytree.min.css" rel="stylesheet">
<jsp:include page="/WEB-INF/includee/preScript.jsp"/>
<style type="text/css">
	.folder{
		text-decoration: underline;
		color:blue;
	}
</style>
<script type="text/javascript">
document.addEventListener("dblclick", e =>{
	
});
</script>
</head>
<body data-context-path="${pageContext.request.contextPath }">
<form action="${pageContext.request.contextPath }/case2/fileInfo"></form>
	<div class="row">
	<ul class="col-6">
	<c:set var="list" value="${wrapperList }"/>
		<c:forEach items="${list }" var="fl">
			<li id="${fl.path }" class="${fl.file? 'file' : 'folder'}">
				<c:choose>
					<c:when test="${not fl.file }">
						<c:url value="/case2/serverFile" var="fileURL">
							<c:param name="base"  value="${fl.path }"/>
						</c:url>
						<a data-click="all" data-dblclick="folder" href="${fileURL }">
							${fl.name }
						</a>
					</c:when>
					<c:otherwise>
						${fl.name }
					</c:otherwise>
				</c:choose>
			</li>		
		</c:forEach>		
	</ul>
	
	<div id="tree" data-url="<c:url value='/case2/fileInfo'/>" class="card col">
	</div>
	</div>
	
<script src="${pageContext.request.contextPath }/resources/dist/jquery.fancytree-all.min.js"></script>
<script src="<c:url value='/resources/js/explorer/fileView_case2.js'/>"></script>

<jsp:include page="/WEB-INF/includee/postScript.jsp"/>
</body>
</html>