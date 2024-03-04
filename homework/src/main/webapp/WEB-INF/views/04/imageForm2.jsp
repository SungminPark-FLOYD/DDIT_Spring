<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<style type="text/css">
			form{
				background-color: blue;
			}
		</style>
	</head>
    <body>    	
        <form action="${pageContext.request.contextPath}/image.do" method="get">
            <select name="name">
            	${options}
            </select>
            <button type="submit">이미지 랜더링</button>
        </form>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/app/04/imageForm.js"></script>
    </body>
</html>


