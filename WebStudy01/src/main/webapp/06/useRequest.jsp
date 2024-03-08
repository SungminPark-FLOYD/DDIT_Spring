<%@page import="java.util.Arrays"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java"
    pageEncoding="UTF-8"%>
<%
	String accept = request.getHeader("accept");
	if(accept.contains("json")){
		response.setContentType("application/json;charset=UTF-8");
		%>
		{
			"result":"정상 서비스 완료"
		}
		<%
	}else {
		response.setContentType("text/html;charset=UTF-8");
			
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/useRequest.jsp</title>
</head>
<body>
<h4>request 기본객체 활용</h4>
<pre>
	1. line
		<%= request.getRequestURL() %>, <%= request.getMethod() %>		
		<%= request.getRequestURI() %> -> 많이 사용한다.
	2. Header
		getHeader(name), getHeaderNames(), getDate[Int]Header(name)
	3. body
		1) parameter : String getParameter(name), String[] getParameterValues(name)
					getParameterNames(), getPArameterMap()
		2) multipart
		3) payload
</pre>
<table>
	<thead>
		<tr>
			<th>파라미터명</th>
			<th>파라미터값</th>
		</tr>
	</thead>
	<tbody>
	<%
		request.setCharacterEncoding("UTF-8");
		StringBuffer trTags = new StringBuffer();
		String ptrn = "<tr><td>%s</td><td>%s</td></tr>\n";
		Map<String, String[]> map = request.getParameterMap();
		for(Map.Entry<String, String[]> entry : map.entrySet()) {			
			String name = entry.getKey();
			String[] values = entry.getValue();
			trTags.append(String.format(ptrn, name, Arrays.toString(values)));
		}	
		%>			
		<%= trTags %>

	</tbody>
</table>



<h4>요청 헤더</h4>
<table>
	<thead>
		<tr>
			<th>헤더명</th>
			<th>헤더값</th>
		</tr>
	</thead>
	<tbody>
		<%  
			
			trTags.delete(0, trTags.length());
			Enumeration<String> header = request.getHeaderNames(); 
			while(header.hasMoreElements()) {
				String name = header.nextElement();
				String value = request.getHeader(name);			
				trTags.append(String.format(ptrn, name, value));
			}
		%>
		<%= trTags %>
	</tbody>
</table>
</body>
</html>
<%
}
%>