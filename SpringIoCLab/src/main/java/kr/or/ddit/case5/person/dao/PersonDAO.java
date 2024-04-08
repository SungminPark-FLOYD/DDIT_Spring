package kr.or.ddit.case5.person.dao;

import java.util.List;

import kr.or.ddit.vo.PersonVo;


/**
 * Persistence Layer : persistence 영역에 저장된 raw data를 Domain객체로 매핑하는 역할을 담당하는 객체
 *  
 *
 */
public interface PersonDAO {

	public List<PersonVo> selectPersonList();
	public PersonVo selectPerson(String id);

}