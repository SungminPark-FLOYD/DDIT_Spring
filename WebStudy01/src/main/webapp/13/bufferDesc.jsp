<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="8kb" autoFlush="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>13/bufferDesc.jsp</title>
</head>
<body>
<h4>웹 어플리케이션에서 버퍼의 활용</h4>
<%
	
	request.getRequestDispatcher("").forward(request, response);
	request.getRequestDispatcher("").include(request, response);
%>
<pre>
	버퍼제어에 사용하는 객체 : out(JspWriter)
	버퍼 크기 : <%= out.getBufferSize() %>
	버퍼의 잔여 크기 : <%= out.getRemaining() %>
	자동방출 지원 여부 : <%= out.isAutoFlush() %>
	<%
		for(int i = 1; i <= 100; i++) {
			out.println(String.format("%d 번째 반복으로 메시지 기록.", i));
			if(out.getRemaining() < 100) {
				out.flush();
				System.out.printf("%d 번째 버퍼 방출", i);
			}
			if(i == 50){
				throw new RuntimeException("강제 발생 예외");
			}
		}
	
	%>
</pre>
</body>
</html>