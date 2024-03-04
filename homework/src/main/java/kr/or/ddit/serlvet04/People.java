package kr.or.ddit.serlvet04;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.json.JSONParser;

import com.google.gson.Gson;

/**
 * Servlet implementation class People
 */
@WebServlet("/people.do")
public class People extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("who");
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
        
        StringBuffer data = new StringBuffer(""); 
        String[] values = null;
        for (Object object: properties.keySet()) {
        	if(id.equals(object) ) {
        		String value = (String) properties.get(object);
                values = value.split("\\|");
        	}        	
        }
        Gson gson = new Gson();
        
        String json = gson.toJson(values);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
	}

}
