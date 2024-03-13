package kr.or.ddit.calculate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
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
		
		
		
		String leftOp = null;
		String rightOp = null;
		String operatorStr = null;
		try {
			leftOp = Optional.of(req.getParameter("leftOp"))
					.filter(lp -> !lp.isEmpty())			
					.orElseThrow(() -> new IllegalArgumentException("숫자를 입력해 주세요"));
			rightOp = Optional.of(req.getParameter("rightOp"))
					.filter(rp -> !rp.isEmpty())			
					.orElseThrow(() -> new IllegalArgumentException("숫자를 입력해 주세요"));
			operatorStr = Optional.of(req.getParameter("operator"))
					.filter(op -> !op.isEmpty())			
					.orElseThrow(() -> new IllegalArgumentException("연산자를 입력해 주세요"));
			
		}catch (IllegalArgumentException e) {
			resp.sendError(400, e.getMessage());
			return;
		}
		
		int lfOp = Integer.parseInt(leftOp);
		int rfOp = Integer.parseInt(rightOp);
		Operator operator = Operator.valueOf(operatorStr);
		int result = 0;
		
	    try {    	
	    	result = operator.calculate(lfOp, rfOp);
	    } catch (RuntimeException e) {
	    	resp.sendError(400,e.getMessage());	     
	    	return;
	    }
			
		PrintWriter out = resp.getWriter();
		out.print(String.format("%s %s %s = %d", leftOp, operator.getSymbol(), rightOp, result));
			
		
	}
}
