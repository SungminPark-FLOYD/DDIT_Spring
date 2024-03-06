package kr.or.ddit.servlet01;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿
 * 	: 웹상의 요청(HTTP)을 받고(분석), 웹을 통해 응답(HTTP)을 전송(동적자원생성)할 수 있는 객체의 필요요건 -> HttpServlet
 *
 *	개발 단계
 *	1. HttpServlet 구현체 정의
 *	2. 필요한 callback 메소드 재정의**
 *		$('#btn').on("click", function(){}); <- 익명함수 이벤트 핸들러 정의 : callback구조
 *		callback : 관련된 이벤트가 발생했을 때 시스템 내부적으로 자동 호출되는 구조
 *	ex) doGet(http get method 요청 발생)
 *		doPost(http post method 요청 발생)
 *		doDelete(http delete method 요청 발생)
 *	서블릿의 생명주기 이벤트
 *		: 서블릿 생성 및 초기화 - init
 *			일반적으로 컨테이너는 최초의 요청이 발생했을때 싱글톤 인스턴스를 생성함.
 *		: 서블릿 소멸 - destroy
 *	서블릿 요청 이벤트
 *		: service ==> doXXX 메서드로 분기(dispatch)
 *	3. WAS(Servlet Container)에게 해당 서블릿 등록
 *		bean container
 *		javabean container
 *		servlet container = 서블릿 객체의 생명주기를 관리하는 관리자
 *		jsp container
 *		UI container : <div></div> = 컴포넌트(java에서는 element)
 *	container : 컨테이너 내부의 객체에 대한 생명주기(생성~소멸) 관리자
 *	1) web.xml의 servlet => servlet-name, servlet-class
 *		load-on-startup, init-param, multipart-config등의 엘리먼트로 컨테이너의 특성 제어
 *	2) @WebServlet : 어노테이션의 속성으로 컨테이너 특성 제어
 *		@WebServlet: 마커 어노테이션 
 *		@WebServlet("/desc"): single-value 어노테이션(value 속성값)
 *		@WebServlet("/desc"): single-value 어노테이션(value 속성값)
 *		@WebServlet(value = "/desc", loadOnStartup = 1): 
 *				multi-value 어노테이션(속성 이름 생략 불가)
 *	4. 등록된 서블릿에 대한 매핑 설정 *** 절대 경로로 표기함
 *		case 1 : "/" - 정적자원에 대한 매핑(DefaultServlet의 매핑 정보로 사용됨)
 *		case 2 : "/*", "/member/*" - 경로매핑 구조, 구체적인 매핑 정보가 있는 객체를 제외한 나머지 모든 요청 
 *		case 3 : "*.do"- 확장자 매핑 구조
 *		case 4 : "/member/*.do" - 경로매핑과 확장자 매핑을 같이 쓸수없다
 */

//servlet 3.0
@WebServlet(value = "/desc", loadOnStartup = 1)
public class DescriptionServlet extends HttpServlet{
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("서블릿 인스턴스 생성 후 초기화 작업 완료");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("servoce 메서드 시작");
		super.service(req, resp);
		System.out.println("service 메서드 종료");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet메서드 실행");
		resp.setContentType("text/plain;charset=utf-8");
		resp.getWriter().print("동적 응답 전송");
	}
	
	@Override
	public void destroy() {
		super.destroy();
		System.out.println("서블릿 객체 소멸");
	}
	
	
}


