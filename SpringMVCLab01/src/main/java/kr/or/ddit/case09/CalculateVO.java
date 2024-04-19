package kr.or.ddit.case09;

import lombok.Data;

@Data
public class CalculateVO {
	private long leftOp;
	private long rightOp;
	private OperatorType operator;
	
	public long getResult() {
		return operator.operate(leftOp, rightOp);
	}
	
	public String getExpr() {
		return String.format("%d %c %d = %d", leftOp, operator.getSign(), rightOp, getResult());
	}
}
