package kr.or.ddit.servlet08;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.ha.backend.Sender;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.exception.ResponseStatusException;

@WebServlet("/bts")
public class BtsControllerServlet extends HttpServlet{
	private ServletContext application;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		Map<String, String[]> btsMap = new LinkedHashMap<String, String[]>();
		btsMap.put("B001", new String[] {"뷔", "bts/bui"});
		btsMap.put("B002", new String[] {"제이홉", "bts/jhop"});
		btsMap.put("B003", new String[] {"지민", "bts/jimin"});
		btsMap.put("B004", new String[] {"진", "bts/jin"});
		btsMap.put("B005", new String[] {"정국", "bts/jungkuk"});
		btsMap.put("B006", new String[] {"RM", "bts/rm"});
		btsMap.put("B007", new String[] {"슈가", "bts/suga"});
		application.setAttribute("btsMap", btsMap);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewName = "/WEB-INF/views/bts/btsForm.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String[]> btsMap = (Map) application.getAttribute("btsMap");
		// 디코딩 설정
		req.setCharacterEncoding("UTF-8");
		// 파라미터 받기
//		String member = req.getParameter("member");
//		int status = 200;
//		if(StringUtils.isBlank(member)) {
//			status = 400;
//		}else if(!btsMap.containsKey(member)) {
//			status = 404;
//		}
//		
//		if(status == 200) {
//			String[] btsData = btsMap.get(member);
//			String path = btsData[1];
//			String prefix = "/WEB-INF/views/";
//			String suffix = ".jsp";
//		}else {
//			resp.sendError(status);
//		}
		
		
		
		
		
		//검증 -> 실패하면 400
		try {
			//1. 팔요 파라미터 확보 / 검증
				String btsKey = Optional.ofNullable(req.getParameter("member")) 
						.filter(tp->!tp.isEmpty())
						.orElseThrow(()->new ResponseStatusException(400, "필수파라미터 누락")); 
				
				if(!btsMap.containsKey(btsKey)) { 
					throw new ResponseStatusException(404,"해당 bts멤버는 없음");
				}
				
				//쿠키 추가
//			    Cookie cookie = new Cookie("bts", URLEncoder.encode(btsKey, "UTF-8"));
//			    cookie.setPath(req.getContextPath());
//			    cookie.setMaxAge(60*60*24*2);
//			    resp.addCookie(cookie);
			    
				String memUrl = null;
				for(Entry<String, String[]> entry : btsMap.entrySet()) {
					if(entry.getKey().equals(btsKey)) {
						memUrl = entry.getValue()[1];
					}
				}
				
				String content = String.format("/WEB-INF/views/%s.jsp",memUrl);  //(모듈화 작업시 필요코드)
				req.setAttribute("content", content); //(모듈화 작업시 필요코드)
				String path = "/WEB-INF/views/bts/base.jsp"; //모듈화 작업시 필요한 path
				req.getRequestDispatcher(path).forward(req,resp);
			}catch(ResponseStatusException e) {
				resp.sendError(e.getStatus(), e.getMessage()); //이런용도로 사용하는게 custom exception임 
			}
	}
}
