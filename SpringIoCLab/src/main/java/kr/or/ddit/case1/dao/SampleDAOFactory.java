package kr.or.ddit.case1.dao;

public class SampleDAOFactory {
	public static SampleDAO getSampleDAO() {
		return new SampleDAOImpl_Oracle();
	}
}
