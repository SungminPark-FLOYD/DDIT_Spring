package kr.or.ddit.person.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import kr.or.ddit.person.service.PersonService;
import kr.or.ddit.person.service.PersonServiceImpl;
import kr.or.ddit.vo.PersonVo;

@WebServlet("/people.do")
@MultipartConfig
public class PersonDetailServlet extends HttpServlet{
	private PersonService service = new PersonServiceImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * Part part = req.getPart("who");
		 * 
		 * if (part != null) { BufferedReader reader = new BufferedReader(new
		 * InputStreamReader(part.getInputStream())); StringBuilder requestBody = new
		 * StringBuilder(); String line;
		 * 
		 * while ((line = reader.readLine()) != null) { requestBody.append(line); }
		 * 
		 * String memberId = requestBody.toString(); PersonVo person =
		 * service.retrievePerson(memberId); Gson gson = new Gson(); String personJson =
		 * gson.toJson(person);
		 * 
		 * resp.setContentType("application/json"); resp.setCharacterEncoding("UTF-8");
		 * 
		 * resp.getWriter().write(personJson);
		 * 
		 * } else { resp.sendError(400, "필수 파라미터 누락"); }
		 */
		
		String id = req.getParameter("who");
		if(id == null || id.isEmpty()) {
			resp.sendError(400, "필수 파라미터 누락");
			return;
		}
		
		PersonVo person = service.retrievePerson(id);
		req.setAttribute("person", person);
		req.getRequestDispatcher("/WEB-INF/views/person/detail.jsp").forward(req,resp);
		
		
	}
}
