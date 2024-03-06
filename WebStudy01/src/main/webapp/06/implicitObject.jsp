<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/implicitObject.jsp</title>
</head>
<body>
<h4>기본객체(내장 객체)</h4>
<pre>
	기본 객체의 종류
	1. request(HttpServletRequest)
	2. response(HttpServletResponse)
	3. session(HttpSession)
	4. application(ServletContext)
	
	5. out(JspWriter)
	6. page : this
	7. config(ServletConfig) 
	8. exception(Throwable) : 에러를 처리하기 위한 에러페이지에서 활성화됨
	
	9. pageContext(PageContext)(가장 중요)
	
</pre>
</body>
</html>