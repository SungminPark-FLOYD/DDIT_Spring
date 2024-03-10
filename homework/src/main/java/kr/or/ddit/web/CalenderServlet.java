package kr.or.ddit.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calendar.do")
public class CalenderServlet extends HttpServlet{
	
	@Override
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		    // 요청 파라미터 처리
		    int year = Integer.parseInt(req.getParameter("year"));
		    int month = Integer.parseInt(req.getParameter("month"));

		    // 달력 데이터 생성
		    Calendar calendar = Calendar.getInstance();
		    calendar.set(Calendar.YEAR, year);
		    calendar.set(Calendar.MONTH, month - 1);
		    int lastDay = calendar.getActualMaximum(Calendar.DATE);

		    // HTML 응답 생성
		    PrintWriter out = resp.getWriter();
		    out.println("<!DOCTYPE html>");
		    out.println("<html lang=\"ko\">");
		    out.println("<head>");
		    out.println("  <meta charset=\"UTF-8\">");
		    out.println("  <title>" + year + "년 " + month + "월 달력</title>");
		    out.println("</head>");
		    out.println("<body>");
		    out.println("  <h1>" + year + "년 " + month + "월</h1>");
		    out.println("  <table>");
		    out.println("    <thead>");
		    out.println("      <tr>");
		    out.println("        <th>일</th>");
		    out.println("        <th>월</th>");
		    out.println("        <th>화</th>");
		    out.println("        <th>수</th>");
		    out.println("        <th>목</th>");
		    out.println("        <th>금</th>");
		    out.println("        <th>토</th>");
		    out.println("      </tr>");
		    out.println("    </thead>");
		    out.println("    <tbody>");

		    // 날짜 표시
		    for (int i = 1; i <= lastDay; i++) {
		      out.println("      <tr>");
		      for (int j = 0; j < 7; j++) {
		        if (i == 1 && j < calendar.get(Calendar.DAY_OF_WEEK) - 1) {
		          // 1일 이전 공백 채우기
		          out.println("        <td></td>");
		        } else if (i > lastDay) {
		          // 마지막 날 이후 공백 채우기
		          out.println("        <td></td>");
		        } else {
		          // 날짜 표시
		          out.println("        <td>" + i + "</td>");
		          i++;
		        }
		      }
		      out.println("      </tr>");
		    }

		    out.println("    </tbody>");
		    out.println("  </table>");
		    out.println("</body>");
		    out.println("</html>");
		  }
   
	 }

