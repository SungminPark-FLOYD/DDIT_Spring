package kr.or.ddit.case04.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.case04.vo.DummyVO;

@Repository
public class DummyDAO {
	public DummyVO selectRawData() {
		return new DummyVO("조회한 raw data");
	}
}
