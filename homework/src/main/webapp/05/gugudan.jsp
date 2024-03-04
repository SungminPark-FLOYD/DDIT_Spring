<%@page import="com.itextpdf.text.log.SysoCounter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	 int min = 1;
    	 int max = 1;

	    String minP = request.getParameter("min");
	    String maxP = request.getParameter("max");
	    
		if((minP != null && !minP.isEmpty()) ||(maxP != null && !maxP.isEmpty())) {
			try{
				min = Integer.parseInt(minP);
				max = Integer.parseInt(maxP);	
				
				if(min < 0 || min > 9) throw new IllegalArgumentException("1부터 10사이의 피연산자만 처리 가능."); 
				if(max < 0 || max > 9) throw new IllegalArgumentException("1부터 10사이의 피연산자만 처리 가능."); 
				if(min > max) throw new IllegalArgumentException("min이 max보다 큽니다");
				
			}catch(NumberFormatException e){
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
				return;
			}catch(IllegalArgumentException e) {
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
	StringBuffer gugu(int n) {
		String danPtrn = "<th>%d단</th><td>%d*%d=%d</td>"; 
		StringBuffer taTags = new StringBuffer();
		for(int i = 1; i <= 9; i++) {
			taTags.append(String.format(danPtrn, n,n,i, n*i));
		}
		
		return taTags;
	}
%>
<form>
	<input type="number" name="min" min="1" max="9">
	<input type="number" name="max" min="1" max="9">
	<button type="submit">구구단</button>
</form>

<table>
		
		<%
			for(int i = min; i <= max; i++) {
		%>
		<tr>
		<%=	
			gugu(i)
		%>
		</tr>
		<%
			
		}
		
		%>
       
</table>
</body>
</html>