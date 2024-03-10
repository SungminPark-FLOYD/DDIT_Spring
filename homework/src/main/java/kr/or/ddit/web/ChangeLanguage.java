package kr.or.ddit.web;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/changeLanguage")
public class ChangeLanguage extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 언어 선택 폼에서 전달된 언어 값을 받아옴
        String selectedLanguage = req.getParameter("language");

        // 선택한 언어 정보를 세션에 저장
        HttpSession session = req.getSession();
        session.setAttribute("userLocale", new Locale(selectedLanguage));

        // 페이지를 리로딩
        resp.sendRedirect(req.getHeader("Referer"));
	}
}
