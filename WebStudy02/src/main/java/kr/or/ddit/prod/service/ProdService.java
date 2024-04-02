package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.vo.ProdVO;

public interface ProdService {
	public List<ProdVO> retrieveProdList();
	
	public ProdVO retrieveProd(String prodId) throws PkNotFoundException;
}
