package kr.or.ddit.calculate;


public enum Operator {
	PLUS,
	MINUS,
	MULTIPLY,
	DIVIDE;
	

	public static String OP(int a, int b, String op) {
		System.out.println(op);
		String res = "%s %s %s = %s";
				
		if(op.equals("PLUS")) {		
			res = String.format(res, a, "+", b, a+b);
		}
		else if(op.equals("MINUS")) {
			res = String.format(res, a, "-", b, a-b);
		}
		else if(op.equals("MULTIPLY")) {
			res = String.format(res, a, "*", b, a*b);
		}else {
			res = String.format(res, a, "/", b, a/b);
		}
		return res;
		
	}
}
