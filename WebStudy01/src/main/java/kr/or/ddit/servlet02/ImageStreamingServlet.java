package kr.or.ddit.servlet02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

//클라이언트가 전송한 파라미터 받고 이미지 바꾸기
//name이 없으면 400에러 띄우기 - 에러 상태코드 제어

@WebServlet("/image.do")
public class ImageStreamingServlet extends HttpServlet{
	private ServletContext application;  //톰캣의 유일한 싱글톤

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application =  getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		File imageFolder = new File("D:/00.medias/images");
		
		String fileName = req.getParameter("name");		
		// 파일 이름이 비어있거나 null일 경우 400 Bad Request 응답을 보냄
	    if (fileName == null || fileName.isEmpty()) {
	        resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
	        return;
	    }

		
		File imageFile = new File(imageFolder, fileName);
		String mime = application.getMimeType(imageFile.getName());
		
		resp.setContentType(mime);
		resp.setContentLengthLong(imageFile.length());
		
		try (InputStream is = new FileInputStream(imageFile); OutputStream os = resp.getOutputStream()) {
	        // stream copy
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) != -1) {
	            os.write(buffer, 0, length);
	        }
	    }
		
	}
}
