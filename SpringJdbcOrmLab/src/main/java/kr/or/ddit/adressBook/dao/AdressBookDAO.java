package kr.or.ddit.adressBook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.AdressBookVO;

@Mapper
public interface AdressBookDAO {
	public List<AdressBookVO> selectList();
	public AdressBookVO selectOne(Long id);
	public int insert(AdressBookVO adressBookVO);
	public int update(AdressBookVO adressBookVO);
	public int delete(Long id);
}
