<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.Writer"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.Reader"%>
<%@page import="kr.or.ddit.servlet07.MbtiControllerServlet"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>12/resourceType.jsp</title>
</head>
<body>
<h4>자원의 분류</h4>
<pre>
	해당 자원에 접근하는 방법에 따른 분류
	1. web resource : URL로 접근할 수 있는 웹을 통해 서비스되는 자원
		ex) http://localhost/WebStudy01/resources/images/cat3.png
		<%
			String url = "/resources/images/cat3.png";
			String realPath = application.getRealPath(url);
			out.println(new File(realPath));
		%>
	2. file system resource : 파일 시스템상의 절대 경로로 접근
		ex) D:\00.medias\images\cute5.jpg
		<%= new File("D:\\00.medias\\images\\cute5.jpg") %>
	3. classpath resource : classpath 이후의 경로(qualified name)로 접근.
		ex) /kr/or/ddit/MemberData.properties
		<%
			String logicalPath = "/kr/or/ddit/MemberData.properties";
			String physicalPath = MbtiControllerServlet.class.getResource(logicalPath).getFile();
			
			out.println(new File(physicalPath));
		%>
	<%
		String srcFURL = "/resources/images";
		File srcFolder = new File(application.getRealPath(srcFURL));
		String srcName = "cat5.png";	
		File srcFile = new File(srcFolder, srcName);
		
		String desFURL = "/12";
		File desFolder = new File(application.getRealPath(desFURL));
		File desFile = new File(desFolder, srcFile.getName());
		

		if (!desFile.exists()) {
			try{
				desFile.createNewFile();
				out.println("파일생성");
		        } 
		        catch(Exception e){
			    e.getStackTrace();
			}        
	   	}
		
		try(
			InputStream is =  new FileInputStream(srcFile);	
			BufferedInputStream bis = new BufferedInputStream(is);
			OutputStream os = new FileOutputStream(desFile);
			BufferedOutputStream bos = new BufferedOutputStream(os);
		){
			int re = -1;
	        while ((re = bis.read()) != -1) {
	        	bos.write(re);
	        }
	        bos.flush();
		}
		
		

		
	
		
	
	%>
</pre>
<img src="<%=request.getContextPath() %>/12/cat3.png"/>
</body>
</html>