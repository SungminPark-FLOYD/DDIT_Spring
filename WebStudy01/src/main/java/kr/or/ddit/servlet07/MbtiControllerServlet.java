package kr.or.ddit.servlet07;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.exception.ResponseStatusException;


//일반적으로 서블릿은 요청이들어오면 실행(?)함.
//init을 미리 실행해 놓고싶다면?
@WebServlet(loadOnStartup = 1, value="/09/mbti")
public class MbtiControllerServlet extends HttpServlet {
	
	private ServletContext application;

	@Override
	public void init() throws ServletException { //라이프 사이클 콜백
		super.init();
		Map<String, String> mbtiMap = new LinkedHashMap<>(); //순서를 넣으려면 Linked계열 사용 
		mbtiMap.put("istj","1. ISTJ 소금형");
        mbtiMap.put("isfj","2. ISFJ 권력형");
        mbtiMap.put("infj","3. INFJ 예언자형");
        mbtiMap.put("intj","4. INTJ 과학자형");
        mbtiMap.put("istp","5. ISTP 백과사전형");
        mbtiMap.put("isfp","6. ISFP 성인군자형");
        mbtiMap.put("infp","7. INFP 잔다르크형");
        mbtiMap.put("intp","8. INTP 아이디어형");
        mbtiMap.put("estp","9. ESTP 활동가형");
        mbtiMap.put("esfp","10. ESFP 사교형");
        mbtiMap.put("enfp","11. ENFP 스파크형");
        mbtiMap.put("entp","12. ENTP 발명가형");
        mbtiMap.put("estj","13. ESTJ 사업가형");
        mbtiMap.put("esfj","14. ESFJ 친선도모형");
        mbtiMap.put("enfj","15. ENFJ 언변능숙형");
        mbtiMap.put("entj","16. ENTJ 지도자형");
        
        application = getServletContext();
        application.setAttribute("mbtiMap", mbtiMap); //request는 Stateless고 ServletContext는 Statefull!
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = "/WEB-INF/views/mbti/mbtiForm.jsp";
		req.getRequestDispatcher(path).forward(req,resp); 
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> mbtiMap = (Map) application.getAttribute("mbtiMap");
		//form태그안의 name속성. <select name="type"," 사용하기. 검증통과 후 html로 이동하는 코드가 나와야함(model2구조).
		try {
		//1. 팔요 파라미터 확보 / 검증
			String mbtitype = Optional.ofNullable(req.getParameter("type")) //of사용하면 없으면 바로 nullpointexception뜨게함. ofNullable은 안뜸.
					.filter(tp->!tp.isEmpty())
					.orElseThrow(()->new ResponseStatusException(400, "필수파라미터 누락")); //orElseThrow : 던질수 있는 예외만 전공해준다!
			
			if(!mbtiMap.containsKey(mbtitype)) { //404에러를 400으로 바꾸기 위해서 넣는거!! (브라우저 단에서 옵션을 클라이언트가 자의적으로 바꿨을때 404대신 400이 뜨게 하기위해)
				throw new ResponseStatusException(400,String.format("%s mbti 유형은 없음", mbtitype)); //return이 필요없고 예외가 발생하면 flow는 멈추고 catch로 jumping
			}
			
//			String path = String.format("/WEB-INF/views/mbti/%s.html",mbtitype);
			String content = String.format("/WEB-INF/views/mbti/%s.html",mbtitype);  //(모듈화 작업시 필요코드)
			req.setAttribute("content", content); //(모듈화 작업시 필요코드)
			String path = "/WEB-INF/views/mbti/base.jsp"; //모듈화 작업시 필요한 path
			req.getRequestDispatcher(path).forward(req,resp);
		}catch(ResponseStatusException e) {
			resp.sendError(e.getStatus(), e.getMessage()); //이런용도로 사용하는게 custom exception임 
		}
	}
}

