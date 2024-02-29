package kr.or.ddit.servlet03;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/imageForm.do")
public class ImageForm extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File folder = new File("D:/00.medias/images");
		// 폴더에 있는 파일들 가져오기
        File[] files = folder.listFiles();
        
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<form action='" + request.getRequestURI() + "' method='post'>");
        out.println("<select name='image'>");

        for(int i = 0; i < files.length; i++) {
        	out.println(String.format("<option value='%s'>%s</option>", files[i].getName(),files[i].getName()));
        }
        
        out.println("</select>");
        out.println("<button type='submit'>이미지 랜더링</button>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        
        
        
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		File imageFolder = new File("D:/00.medias/images");
		
		String fileName = request.getParameter("image");		
		// 파일 이름이 비어있거나 null일 경우 400 Bad Request 응답을 보냄
	    if (fileName == null || fileName.isEmpty()) {
	    	//클라이언트 요청을 검증할때 400에러 코드를 자주 활용한다
	    	response.sendError(HttpServletResponse.SC_BAD_REQUEST, "이미지 파일명이 없음");
	        return;
	    }
		
		File imageFile = new File(imageFolder, fileName);
		if(!imageFile.exists()) {
			//404에러
			response.sendError(HttpServletResponse.SC_NOT_FOUND, String.format("%s 파일은 없음", fileName));
	        return;
		}
		String mime = getServletContext().getMimeType(imageFile.getName());
		System.out.println(mime);
		//mime.contains() , mime.startWith(), mime.indexOf()
		if(mime == null || mime.isEmpty() || mime.indexOf("image") == -1) {
			//400에러
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "정상적인 파일이 아님");
	        return;
		}
		
		response.setContentType(mime);
		response.setContentLengthLong(imageFile.length());
		
		try (InputStream is = new FileInputStream(imageFile);
			 OutputStream os = response.getOutputStream();
			 BufferedInputStream bis = new BufferedInputStream(is);
			 BufferedOutputStream bos = new BufferedOutputStream(os);
			) {
	        int length;
	        while ((length = bis.read()) != -1) {
	        	bos.write(length);
	        }
	    }	
		
	}

}
