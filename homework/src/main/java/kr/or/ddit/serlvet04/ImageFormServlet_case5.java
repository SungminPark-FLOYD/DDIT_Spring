package kr.or.ddit.serlvet04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.CompletableFuture;

import javax.servlet.AsyncContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * case 1: model1 + servlet spec
 * case 2: template 구조 활용
 * case 3: jsp 스펙 활용
 * case 4: servlet + jsp ==> model2
 * case 5: 비동기 처리
 *
 */

@WebServlet(urlPatterns = "/case5/imageForm.do" ,asyncSupported = true)
public class ImageFormServlet_case5 extends HttpServlet{
	 private ServletContext application;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		AsyncContext asyncContext = req.startAsync();
		
		CompletableFuture.runAsync(() -> {			
			try {
				File folder = new File("D:\\00.medias\\images");
				String[] fileList = folder.list(new FilenameFilter() {                       
                    public boolean accept(File dir, String name) {
                        String mime = application.getMimeType(name);
                        return mime != null && mime.startsWith("image/");
                 }			
			});
				
			StringBuffer options = new StringBuffer("");
            String optPtrn = "\n<option>%s</option>";
            for (String name : fileList) {
                options.append(String.format(optPtrn, name));
            }						
			req.setAttribute("options", options);
			String viewName ="/WEB-INF/views/04/imageForm2.jsp";
		
			req.getRequestDispatcher(viewName).forward(req, resp);
			} catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            asyncContext.complete();
	        }
	 });
	}
}
