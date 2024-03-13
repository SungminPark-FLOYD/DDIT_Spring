package kr.or.ddit.calculate;


public enum Operator {
	PLUS("+") {
	    @Override
	    public int calculate(int num1, int num2) {
	      return num1 + num2;
	    }
	  },
	  MINUS("-") {
	    @Override
	    public int calculate(int num1, int num2) {
	      return num1 - num2;
	    }
	  },
	  MULTIPLY("*") {
	    @Override
	    public int calculate(int num1, int num2) {
	      return num1 * num2;
	    }
	  },
	  DIVIDE("/") {
	    @Override
	    public int calculate(int num1, int num2) {
	      if (num2 == 0) {
	        throw new ArithmeticException("0으로 나눌 수 없습니다.");
	      }
	      return num1 / num2;
	    }
	  };

	  private final String symbol;

	  Operator(String symbol) {
	    this.symbol = symbol;
	  }

	  public String getSymbol() {
	    return symbol;
	  }

	  public abstract int calculate(int num1, int num2);
}
