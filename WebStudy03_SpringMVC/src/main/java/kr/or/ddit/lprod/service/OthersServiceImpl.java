package kr.or.ddit.lprod.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.lprod.dao.LprodDAO;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OthersServiceImpl implements OthersService {

	private final LprodDAO dao;
	
	@Override
	public List<LprodVO> retrieveLprodList() {
		return dao.selectLprodList();
	}

	@Override
	public List<BuyerVO> retrieveBuyerList() {
		List<LprodVO> lprodList = retrieveLprodList();
		List<BuyerVO> buyerList = new ArrayList<BuyerVO>();
		for(LprodVO lprod : lprodList) {
			buyerList.addAll(lprod.getBuyerList());
		}	
		return buyerList;
	}

}
