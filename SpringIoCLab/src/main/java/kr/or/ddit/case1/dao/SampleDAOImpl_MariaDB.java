package kr.or.ddit.case1.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.SampleVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleDAOImpl_MariaDB implements SampleDAO {
	
	private Map<String, SampleVO> mariaDB;
	{
		mariaDB = new LinkedHashMap<String, SampleVO>();
		mariaDB.put("T001", SampleVO.builder()
								.id("T001")
								.name("정성윤_MariaDB")
								.role("PL")
								.build());
		mariaDB.put("T002", SampleVO.builder()
								.id("T002")
								.name("장민우_MariaDB")
								.role("TA")
								.build());
		mariaDB.put("T003", SampleVO.builder()
								.id("T003")
								.name("신의정_MariaDB")
								.role("UA")
								.build());
		mariaDB.put("T004", SampleVO.builder()
								.id("T004")
								.name("박승준_MariaDB")
								.role("AA")
								.build());
	}
	
	public void init() {
		log.info("lifecycle callback init 호출");
	}
	
	public void destroy() {
		log.info("lifecycle callback destroy 호출");
	}
	
	public SampleDAOImpl_MariaDB() {
		super();
		log.info("{} 객체 생성", this.getClass().getSimpleName());
	}

	@Override
	public List<SampleVO> selecteSampleList() {		
		return new ArrayList<SampleVO>(mariaDB.values());
	}

	@Override
	public SampleVO selecteSample(String id) {
		return mariaDB.get(id);
	}

}
