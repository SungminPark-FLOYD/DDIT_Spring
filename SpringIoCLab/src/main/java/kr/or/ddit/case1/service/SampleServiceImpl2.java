package kr.or.ddit.case1.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kr.or.ddit.case1.dao.SampleDAO;
import kr.or.ddit.vo.SampleVO;

public class SampleServiceImpl2 implements SampleService{
	private SampleDAO dao;
	
	public void setDao(SampleDAO dao) {
		this.dao = dao;
	}
	
	public List<SampleVO> readSampleList() {
		return dao.selecteSampleList();
	}

	@Override
	public SampleVO readSample(String id) {
		
		return dao.selecteSample(id);
	}
}
