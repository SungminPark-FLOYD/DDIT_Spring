<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%!
    	 static int num = 0;
    	 int sum = 0;
    	 int mul = 1;
    %>
    <% 
    	
	    String numParam = request.getParameter("num");
		if(numParam != null && !numParam.isEmpty()) {
			try{
				num = Integer.parseInt(numParam);
				if(num < 0 || num > 10) num = 5;
			}catch(NumberFormatException e) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
		}
		else num = 5;
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%! 
	int sigma(int n) {
		sum += n;
		return sum;
	}

	int factorial(int n) {
		mul *= n;
		return mul;
	}
%>
<form>
	<input type="number" name="num" min="1" max="10"/>
	<button type="submit">전송</button>
</form>
<h4>누적 합 : <%= sigma(num) %></h4>
<h4>누적 곱 : <%= factorial(num) %></h4>
</body>
</html>