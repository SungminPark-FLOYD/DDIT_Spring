package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/06/case1Req.do")
public class Case1Servlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//name : p2, value : 23
		StringBuffer trTags = new StringBuffer();
		String ptrn = "name : %s, value : %s<br>";
		
		//1.8에서 나온 문법
		//람다식은 suger syntax를 지향한다 -> 최대한 짧게, 최대한 가독성이 높게 
		//한줄짜리 내용은 {} 바디부분을 생략할 수 있다.
		req.getParameterMap().forEach((k,v) -> 
			trTags.append(String.format(ptrn, k, Arrays.toString(v)))
		);
		
		
//		Map<String, String[]> map = req.getParameterMap();
//		for(Map.Entry<String, String[]> entry : map.entrySet()) {			
//			String name = entry.getKey();
//			String[] values = entry.getValue();
//			trTags.append(String.format(ptrn, name, Arrays.toString(values)));
//		}
	
		//요청 검증하기 대소문자 상관없음
		String accept = req.getHeader("accept");
		String contentType = null;
		String content = null;
		if(accept.contains("json")) {
			contentType = "application/json;charset=utf-8";
			content = "{\"result\" : \"처리 성공\"}";
		}else {
			contentType = "text/html;charset=utf-8";
			content = "<html><body><h4>처리성공</h4></body></html>";
		}
		
		resp.setContentType(contentType);
		try(PrintWriter out = resp.getWriter();){
			out.print(content);
		}
		
		
//		String res;
//		if(accept.equals("application/json")) {
//			resp.setContentType("application/json;charset=utf-8");
//			//{"result" : "처리 성공"}
//			res = "{\"result\" : \"처리 성공\"}";
//		}else {
//			resp.setContentType("text/html;charset=utf-8");
//			//<html><body><h4>처리성공</h4></body></html>
//			res = "<html><body><h4>처리성공</h4></body></html>";
//		}	
//		PrintWriter out = resp.getWriter();
//		out.append(res);
//		out.flush();
		
		
	}
}


