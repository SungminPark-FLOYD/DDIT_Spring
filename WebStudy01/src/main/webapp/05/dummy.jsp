<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	int max = 10;
	String maxParam = request.getParameter("max");
	if(maxParam != null && !maxParam.isEmpty()) {
		try{
			max = Integer.parseInt(maxParam);
			if(max < 0) max = 10;
			
		}catch(NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../resources/js/jquery-3.7.1.min.js"></script>
<style>
	.odd{
		background-color: yellow;
	}
	.even{
		background-color: red;
	}
</style>
</head>
<body>
<%! static String printNumber(int n) {
		String d = "";
		for(int i = 1; i <= 10; i++) {
			boolean od = i%2 == 1;
			String v = od ? "odd" : "even";
			d += "<li class='"+ v + "'>" + i + "</li>";
		}
		return d;
	}
	%>
<%! StringBuffer print2(int max){
	String liptrn = "<li class='%s'>%d</li>";
	StringBuffer liTags = new StringBuffer();
	for(int i = 1; i <= max; i++) {
		boolean od = i%2 == 1;
		String v = od ? "odd" : "even";
		liTags.append(String.format(liptrn, v, i));
	}
	return liTags;	
}
%>

	<ul>
		<%= print2(max) %>
	</ul>
	<form>
		<input type="number" name="max" value="<%= max%>"/>
		<button type="submit">전송</button>
	</form>
	<a href="?max=5">더미</a>
	<img src=""/>
	
</body>
</html>