package kr.or.ddit.case09;

@FunctionalInterface
public interface BiOperandOperator {
	public long operate(long left, long right);
}
