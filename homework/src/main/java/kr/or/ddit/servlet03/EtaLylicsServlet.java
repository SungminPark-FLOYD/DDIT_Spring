package kr.or.ddit.servlet03;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.Buffer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;


/**
 * 사용자로부터 /eta 요청을 받고,
 * 컨텐츠 폴더에있는 eta_utf8.txt 파일에 있는 가사를 컨텐츠로 서비스
 *
 */

@WebServlet("/eta")
public class EtaLylicsServlet extends HttpServlet{
	private ServletContext application;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = config.getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		File folder = new File("D:/00.medias/");
		String fildName = "ETA_UTF8.txt";
		File file = new File(folder, fildName);
		String mime = application.getMimeType(fildName);
		resp.setContentType(String.format("%s;%s", mime, "charset=utf-8"));
		resp.setContentLengthLong(file.length());
		String li = "";
		try (InputStream is = new FileInputStream(file);
			 OutputStream os = resp.getOutputStream();
			 BufferedInputStream bis = new BufferedInputStream(is);
			 BufferedOutputStream bos = new BufferedOutputStream(os);
			) {
	        int length;
	        while ((length = bis.read()) != -1) {	        	
	        	bos.write(length);
	        }
//	        bos.flush();
	    }
		
	}
	
	//선생님 코드
	void ex(HttpServletRequest req, HttpServletResponse resp) throws FileNotFoundException, IOException {
		File readFile = new File("D:/00.medias/ETA_UTF8.txt");
		
		String mime = getServletContext().getMimeType(readFile.getName());
		resp.setContentType(String.format("%s;%s", mime, "charset=utf-8"));
		try(
			FileInputStream fis = new FileInputStream(readFile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			OutputStream os = resp.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(os);
		){
			int readByte = -1;
			while((readByte = bis.read()) != -1) {
				bos.write(readByte);
			}
			//안전한 처리
			bos.flush();
		}
	}
	
}
