package kr.or.ddit.calculate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculate.do")
public class Calculate extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String leftOp = req.getParameter("leftOp");
		String operator = req.getParameter("operator");
		String rightOp = req.getParameter("rightOp");
		
		int lfOp = Integer.parseInt(leftOp);
		int rfOp = Integer.parseInt(rightOp);
		
		String res = Operator.OP(lfOp, rfOp, operator);
		PrintWriter out = resp.getWriter();
		out.print(res);
			
		
	}
}
