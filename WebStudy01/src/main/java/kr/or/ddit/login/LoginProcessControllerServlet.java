package kr.or.ddit.login;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.exception.ResponseStatusException;

@WebServlet("/login/loginProcess.do")
public class LoginProcessControllerServlet extends HttpServlet{
	private boolean authenticate(String id, String password) {
		return id.equals(password);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. body 영역의 디코딩에 사용할 chatset 결정
		req.setCharacterEncoding("UTF-8");
		//2. 파라미터 받기
//		String id = req.getParameter("memId");
//		String password = req.getParameter("memPass");
		
		//3. 파라미터 검증
//		if((id == null || id.isEmpty()) || (password == null || password.isEmpty())) {
//			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 형식의 아이디 또는 비밀번호");
//			return;
//		}
		
		//	- 검증 통과 -> 4. 인증 여부 판단
		try {
			//자바 1.8문법
			String memId = Optional.of(req.getParameter("memId"))
					.filter(id -> !id.isEmpty())
					.orElseThrow(() -> new ResponseStatusException(400, "아이디 누락"));
			
			String memPass = Optional.of(req.getParameter("memPass"))
					.filter(pass -> !pass.isEmpty())
					.orElseThrow(() -> new ResponseStatusException(400, "비밀번호 누락"));
			if(authenticate(memId, memPass)) {
//				성공 -> 웰컴페이지로 이동 - redirect
				resp.sendRedirect(req.getContextPath() + "/");
			}else {
//				실패 -> 로그인 페이지로 이동 - forward
				req.setAttribute("message", "로그인실패");
				req.getRequestDispatcher("/login/loginForm.jsp").forward(req, resp);
				//resp.sendRedirect(req.getContextPath() + "/login/loginForm.jsp");
			}
		}catch(ResponseStatusException e) {
//			- 불통과 -> 400 상태코드 전송
			resp.sendError(e.getStatus(), e.getMessage());
		}

		
		
		
		
	}
}
