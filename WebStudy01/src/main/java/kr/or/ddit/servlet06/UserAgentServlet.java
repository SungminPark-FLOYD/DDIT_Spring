package kr.or.ddit.servlet06;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.BrowserInfo;

@WebServlet("/07/userAgent.do")
public class UserAgentServlet extends HttpServlet{
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		System.out.println(req.getHeader("user-agent")); 
		
		
		StringBuffer h4Tag = new StringBuffer();
		
		//대소문자 구별을 위해서 대분자로 변환
		String header = req.getHeader("user-agent").toUpperCase();
		String contents = "기타";
		Map<String, String> browserInfo = new LinkedHashMap<String, String>();
		//바뀌지 않는 데이터 -> 상수 -> 2개 이상의 상수 -> 열거형 상수 -> enum
		browserInfo.put("EDG", "엣지");
		browserInfo.put("WHALE", "웨일");
		browserInfo.put("CHROME", "크롬");
		browserInfo.put("SAFARI", "사파리");
		browserInfo.put("OTHER", "기타");
		
		//case 1:
		for(Entry<String,String> entry :browserInfo.entrySet()) {
			if(header.contains(entry.getKey())) {
				contents = entry.getValue();
				break;
			}
		}
		//case 2: enum
		BrowserInfo[] infos = BrowserInfo.values();
		for(BrowserInfo single : infos) {
			if(header.contains(single.name())) {
				contents = single.getBrowserName();
				break;
			}
		}
		BrowserInfo finded = BrowserInfo.findBrowser(header);
		contents = finded.getBrowserName();
		
		//case 3 : enum 응집력 높이기
		contents = BrowserInfo.findBrowserName(header);
		
		
		//조건 순서 지켜야됨
//		if(header.contains("EDG")) {											
//			contents = "엣지";
//		}
//		else if(header.contains("WHALE")) { 										
//			contents = "웨일";
//		}	
//		else if(header.contains("CHROME")) {										
//			contents = "크롬";
//		}
//		else if(header.contains("SAFARI")) {	 
//			contents = "사파리";		
//		}
//		else {
//			contents = "기타";
//		}
//		
		String message = "<h4>당신의 브라우저는 [%s] 입니다.</h4>";
		h4Tag.append(String.format(message,contents));
		
		resp.setContentType("text/html;charset=utf-8");
		try(PrintWriter out = resp.getWriter();){
			out.print(h4Tag);
		}
	}
}
