<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	 int num = 5;

	    String numParam = request.getParameter("num");
		if(numParam != null && !numParam.isEmpty()) {
			try{
				num = Integer.parseInt(numParam);
			}catch(NumberFormatException e) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
				return;
			}
		}
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%! 
	long sigma(int n) {
		if(n < 1 || n > 10) throw new IllegalArgumentException("1부터 10사이의 피연산자만 처리 가능.");
		if(n == 1) return 1;
		else
			return n + sigma(n-1);
	}

	long factorial(int n) {
		if(n < 1 || n > 10) throw new IllegalArgumentException("1부터 10사이의 피연산자만 처리 가능.");
		if(n == 1) return 1;
		else
			return n * factorial(n-1);
	}
%>
<form>
	<input type="number" name="num" min="1" max="10" value="<%= num %>"/>
	<button type="submit">전송</button>
</form>
<%
	try{
%>
<h4>누적 합 : <%= sigma(num) %></h4>
<h4>누적 곱 : <%= factorial(num) %></h4>
<%
	}catch(IllegalArgumentException e) {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
	}
%>
</body>
</html>