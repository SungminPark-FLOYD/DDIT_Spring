package kr.or.ddit.servlet03;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


/**
 * 서블릿 스펙 제공 객체
 * 
 * 1. HttpServletRequest : http 프로토콜로 발생한 요청과 해당 요청을 발생시킨 클라이언트에 대한 정보를 가진 객체
 *							stateless 객체(1:1로 응답이 전송된 후 제거되는 객체).
 * 2. HttpServletResponse : 응답으로 전송할 메인 컨텐츠와 해당 컨텐츠에 대한 메타데이터를 가진 객체
 * 3. HttpSession : 한 클라이언트에 대해 독립적으로 운영되는 객체(서버) -> 자기 자신만 접근할 수있는데이터
 * 4. ServletContext : 하나의 서버(WAS, Servlet container)와 현재 컨텍스트(어플리케이션)에 대한 정보를 가진 객체 -> 싱글톤, 모든 클라이언트가 접근할수 있어야하는 데이터
 * 5. ServletConfig : 컨테이너에 등록된 서블릿과 1:1 관계로 생성되는 객체. 해당 서블릿의 메타 데이터를 가진 객체
 * 						ex) servletname, initParameter
 * 6. Cookie : 클라이언트의 저장 구조를 통해 상태가 유지되는 객체(클라이언트) -> 세션의 트래킹 모드로 사용가능
 *
 */

@WebServlet(value = "/nouse", loadOnStartup = 1)
public class SerlvetSpecOtherDescServlet extends HttpServlet{
	
	private ServletContext appliction;

	//ServletContext, ServletConfig를 만드는 과정
	@Override
	public void init() throws ServletException {
		super.init();
		appliction = getServletContext();
		System.out.printf("contextPath : %s\n" ,appliction.getContextPath());
		System.out.printf("session tracking mode : %s\n" ,appliction.getDefaultSessionTrackingModes());
		System.out.printf("serlvet spec version : %d.%d\n" ,appliction.getEffectiveMajorVersion(), appliction.getEffectiveMinorVersion());
		System.out.printf("server info : %s\n" ,appliction.getServerInfo());
	}
}
