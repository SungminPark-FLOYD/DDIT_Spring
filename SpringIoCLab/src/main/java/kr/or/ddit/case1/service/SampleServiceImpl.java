package kr.or.ddit.case1.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.case1.dao.SampleDAO;
import kr.or.ddit.case1.dao.SampleDAOFactory;
import kr.or.ddit.case1.dao.SampleDAOImpl_MariaDB;
import kr.or.ddit.case1.dao.SampleDAOImpl_Oracle;
import kr.or.ddit.vo.SampleVO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@Service
public class SampleServiceImpl implements SampleService {
//	1. new 키워드를 이용해 의존 객체를 직접 생성하는 방식 : 결합력 최상
//	private SampleDAO dao = new SampleDAOImpl_MariaDB();
//	2. Factory Object[Method] Pattern : factory객체와 내부에서 생성되는 객체 사이의 결합력 잔존
//	private SampleDAO dao = SampleDAOFactory.getSampleDAO();
//	3. Strategy pattern : 전략을 주입받아 사용하는 구조. 반드시 주입자(모든 결합력 집중)와 주입 방법이 필수. ==> DI container
//	4. DI Container 사용 : 의존객체와 객체들간의 의존관계 형성을 도와주는 대상
	
	@Resource(name = "sampleDAOImpl_MariaDB")
	private SampleDAO dao;
	
	
	public SampleServiceImpl(SampleDAO dao) {
		super();
		this.dao = dao;
		log.info("{} 객체 생성 및 {}를 생성자로 주입 받음", this.getClass().getSimpleName() , dao.getClass().getSimpleName());
	}
	
	public void setDao(SampleDAO dao) {
		this.dao = dao;
		log.info("{}를 기본 생성자로 생성, {}를 setter로 주입받음",this.getClass().getSimpleName() , dao.getClass().getSimpleName());
	}

	public void init() {
		log.info("------------------주입된 dao : {}----------------------> {} init호출 언제 호출되는지 확인할 것"
				, dao.getClass().getSimpleName()
				, this.getClass().getSimpleName());
	}
	@Override
	public List<SampleVO> readSampleList() {
		return dao.selecteSampleList();
	}

	@Override
	public SampleVO readSample(String id) {
		SampleVO sample = dao.selecteSample(id);
		
		if(sample == null) throw new RuntimeException("해당 팀원이 없음");
		return sample;
	}
}
