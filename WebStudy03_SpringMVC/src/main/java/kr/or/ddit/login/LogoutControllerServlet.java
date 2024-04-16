package kr.or.ddit.login;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;


@Controller
@RequestMapping("/login/logout.do")
public class LogoutControllerServlet {
	
	@PostMapping
	public String doPost(HttpServletRequest req) throws UnsupportedEncodingException {
		//현재 사용자의 세션 즉시 만료
		//웰컴 페이지로 이동 - redirect
		HttpSession session = req.getSession();
		if(session.isNew()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"현재 요청이 최초의 요청일 수 없음");
		}
		//true 세션이 없으면 만들어라 / false라면 세션이없으면 null을 반환
		//req.getSession(true);
		//isNew -> true라면 이제 만들어진 요청
		session.invalidate();
		
		//URL 인코딩 하는 방법
		String message = "로그아웃 성공.";
		message = URLEncoder.encode(message, "UTF-8");
//		resp.sendRedirect(req.getContextPath() + "/?message=" + message);
		String viewName = "redirect:/index.do?message="+ message;
//		 * 6. view로 이동(flow control)
		
		//모든 컨트롤러에 다 적용시킬 수 있다
		return viewName;
	}
}
