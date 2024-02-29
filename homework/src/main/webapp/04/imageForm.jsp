<%@page import="java.io.FilenameFilter"%>
<%@page import="java.io.File"%>
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
    		<%
	            File folder = new File("D:\\00.medias\\images");
	    		String[] fileList = folder.list(new FilenameFilter() {		
	    			@Override
	    			public boolean accept(File dir, String name) {
	    				String mime = application.getMimeType(name);
	    				return mime != null && mime.startsWith("image/");
	    			}
	    		});
	    		StringBuffer options = new StringBuffer("");
	    		String optPtrn = "\n<option>%s</option>";
	    		for(String name : fileList) {
	    			options.append(String.format(optPtrn, name));
	    		}
	    		String cPath = request.getContextPath();
            %>
        <form action="<%= cPath %>/image.do" method="get">
            <select name="name">
            	<%= options %>
            </select>
            <button type="submit">이미지 랜더링</button>
        </form>
        <script type="text/javascript" src="<%= cPath %>/resources/js/app/04/imageForm.js"></script>
    </body>
</html>


