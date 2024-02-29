package kr.or.ddit.serlvet04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;

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
//@WebServlet("/case2/imageForm.do")
@WebServlet("/04/imageForm.tmpl")
public class ImageFormServlet_case2 extends HttpServlet{
	 private ServletContext application;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
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
		
		StringBuffer html = new StringBuffer("");
		
//		1. 탬플릿 파일 읽기
//		/WebStudy01/src/main/webapp/04/imageForm.tmpl
		String realPath = application.getRealPath("/04/imageForm.tmpl");
		File tmplFile = new File(realPath);
		try(
			FileReader fr = new FileReader(tmplFile);
			BufferedReader br = new BufferedReader(fr);
			PrintWriter out = resp.getWriter();
		){
			String line = null;
			while((line = br.readLine()) != null) {
				html.append(String.format("%s\n", line));
			}
		
//			2. 데이터([%cPAth%], [%options%]) 치환
			String content = html.toString().replace("[%cPath%]", req.getContextPath())
				.replace("[%options%]", options);
//			3. 치환된 후의 완전한 컨텐츠를 전송
			out.println(content);
		}
		
	}
}
