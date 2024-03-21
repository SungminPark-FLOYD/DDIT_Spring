package kr.or.ddit.bts.service;

import java.util.List;

import kr.or.ddit.bts.dao.BtsDAO;
import kr.or.ddit.bts.dao.InMemoryDAOImpl;
import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.vo.BtsVO;

public class BtsServiceImpl implements BtsService {
	private BtsDAO dao = new InMemoryDAOImpl();

	@Override
	public BtsVO readBts(String code) throws PkNotFoundException {
		BtsVO bts = dao.selectBts(code);
		if(bts == null) {
			throw new PkNotFoundException(404);
		}
		dao.incrementHit(code);
		return bts;
	}

	@Override
	public List<BtsVO> readBtsList() {
		return dao.selectBtsList();
	}

	

}
