package kr.or.ddit.login;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@WebServlet("/login/logout.do")
public class LogoutControllerServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//현재 사용자의 세션 즉시 만료
		//웰컴 페이지로 이동 - redirect
		
		//true 세션이 없으면 만들어라 / false라면 세션이없으면 null을 반환
		//req.getSession(true);
		//isNew -> true라면 이제 만들어진 요청
		HttpSession session = req.getSession();
		if(session.isNew()) {
			resp.sendError(400, "현재 요청이 최초의 요청일 수 없음.");
			return;
		}
		session.invalidate();
		
		//URL 인코딩 하는 방법
		String message = "로그아웃 성공.";
		message = URLEncoder.encode(message, "UTF-8");
		resp.sendRedirect(req.getContextPath() + "/?message=" + message);
	}
}
