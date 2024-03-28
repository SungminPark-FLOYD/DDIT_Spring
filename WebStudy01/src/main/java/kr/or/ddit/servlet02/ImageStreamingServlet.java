package kr.or.ddit.servlet02;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

//클라이언트가 전송한 파라미터 받고 이미지 바꾸기
//name이 없으면 400에러 띄우기 - 에러 상태코드 제어

@WebServlet("/image.do")
public class ImageStreamingServlet extends HttpServlet{
	private ServletContext application;  //톰캣의 유일한 싱글톤
	private String imageFolderPath;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application =  getServletContext();
		imageFolderPath = application.getInitParameter("imageFolder");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		File imageFolder = new File(imageFolderPath);
		
		String fileName = req.getParameter("name");		
		// 파일 이름이 비어있거나 null일 경우 400 Bad Request 응답을 보냄
	    if (fileName == null || fileName.isEmpty()) {
	    	//클라이언트 요청을 검증할때 400에러 코드를 자주 활용한다
	        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "이미지 파일명이 없음");
	        return;
	    }
  	    
		File imageFile = new File(imageFolder, fileName);
		if(!imageFile.exists()) {
			//404에러
	        resp.sendError(HttpServletResponse.SC_NOT_FOUND, String.format("%s 파일은 없음", fileName));
	        return;
		}
		
		//쿠키 추가
	    Cookie cookie = new Cookie("image", URLEncoder.encode(fileName, "UTF-8"));
	    cookie.setPath(req.getContextPath());
	    cookie.setMaxAge(60*60*24*2);
	    resp.addCookie(cookie);
	    
		String mime = application.getMimeType(imageFile.getName());
		System.out.println(mime);
		//mime.contains() , mime.startWith(), mime.indexOf()
		if(mime == null || mime.isEmpty() || mime.indexOf("image") == -1) {
			//400에러
	        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "정상적인 파일이 아님");
	        return;
		}
		
		resp.setContentType(mime);
		resp.setContentLengthLong(imageFile.length());
		
		try (InputStream is = new FileInputStream(imageFile);
			 OutputStream os = resp.getOutputStream();
			 BufferedInputStream bis = new BufferedInputStream(is);
			 BufferedOutputStream bos = new BufferedOutputStream(os);
			) {
	        // stream copy
//	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = bis.read()) != -1) {
	        	bos.write(length);
	        }
	    }
		
	}
}
