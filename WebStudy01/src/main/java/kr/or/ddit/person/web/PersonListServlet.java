package kr.or.ddit.person.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.person.dao.PersonDAOImpl;
import kr.or.ddit.person.service.PersonService;
import kr.or.ddit.person.service.PersonServiceImpl;
import kr.or.ddit.vo.PersonVo;



/**
 * 웹이라는 실행환경에 종속되는 controller layer
 *
 */
@WebServlet("/peopleList.do")
public class PersonListServlet extends HttpServlet{
//	private PersonDAOImpl dao = new PersonDAOImpl();
	private PersonService service = new PersonServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<PersonVo> people =  service.retrievePersonList();
		
		req.setAttribute("people", people);
		req.getRequestDispatcher("/WEB-INF/views/person/people.jsp").forward(req, resp);
	}
}
