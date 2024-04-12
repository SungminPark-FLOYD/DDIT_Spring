package kr.or.ddit.adressBook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.adressBook.dao.AdressBookDAO;
import kr.or.ddit.vo.AdressBookVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdressBookServiceImpl implements AdressBookService {

	private final AdressBookDAO dao;
	
	@Override
	public List<AdressBookVO> selectAddressList() {
		return dao.selectList();
	}

	@Override
	public AdressBookVO selectAddress(Long id) {
		return dao.selectOne(id);
	}

	@Override
	public int insert(AdressBookVO adressBookVO) {
		return dao.insert(adressBookVO);
	}

	@Override
	public int update(AdressBookVO adressBookVO) {
		return dao.update(adressBookVO);
	}

	@Override
	public int delete(Long id) {
		return dao.delete(id);
	}
	
}
