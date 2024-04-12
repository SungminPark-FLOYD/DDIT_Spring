package kr.or.ddit.adressBook.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.vo.AdressBookVO;

public interface AdressBookService {
	public List<AdressBookVO> selectAddressList();
	public AdressBookVO selectAddress(Long id);
	public int insert(AdressBookVO adressBookVO);
	public int update(AdressBookVO adressBookVO);
	public int delete(Long id);
}
