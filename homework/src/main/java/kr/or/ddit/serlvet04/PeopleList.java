package kr.or.ddit.serlvet04;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PeapleList
 */
@WebServlet("/peapleList.do")
public class PeopleList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext application;

	@Override
		public void init(ServletConfig config) throws ServletException {
			
			super.init(config);
			application = config.getServletContext();
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		//Properties 객체 생성
		Properties properties = new Properties();
		File file = new File("kr/or/ddit/MemberData.properties");
		
		//파일 읽기
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(file.getPath())) {
            if (inputStream != null) {
                // Properties로 읽어오기
                properties.load(inputStream);
            } else {
                throw new IOException("Failed to load resource from path: " + file);
            }
        }
        
        StringBuffer lis = new StringBuffer("");
		String liPtrn = "\n<li id='%s'>%s</li>";
        for (Object object: properties.keySet()) {
        	String value = (String) properties.get(object);
            String[] values = value.split("\\|");
            String name = values.length > 0 ? values[0].trim() : "";
        	lis.append(String.format(liPtrn, object, name));
        }
        
        request.setAttribute("lis", lis);
		String viewName ="/WEB-INF/views/04/peopleListForm.jsp";
		
		request.getRequestDispatcher(viewName).forward(request, response);
	}

}
