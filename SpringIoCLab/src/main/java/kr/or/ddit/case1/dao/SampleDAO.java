package kr.or.ddit.case1.dao;

import java.util.List;

import kr.or.ddit.vo.SampleVO;

public interface SampleDAO {
	public List<SampleVO> selecteSampleList();
	public SampleVO selecteSample(String id);
}
