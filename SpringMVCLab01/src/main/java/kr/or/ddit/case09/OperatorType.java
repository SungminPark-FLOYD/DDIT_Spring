package kr.or.ddit.case09;

public enum OperatorType {
	PLUS('+', (l,r) -> l+r)
	, MINUS('-' ,(l,r) -> l-r)
	, MULTIPLY('*',(l,r) -> l*r)
	, DIVIDE('/',(l,r) -> l/r);
	
	private OperatorType(char sign, BiOperandOperator operator) {
		this.sign = sign;
		this.operator = operator;
	}
	
	private BiOperandOperator operator;
	
	private char sign;
	public char getSign() {
		return sign;
	}
	
	public long operate(long left, long right) {
		return this.operator.operate(left, right);
	}
}
