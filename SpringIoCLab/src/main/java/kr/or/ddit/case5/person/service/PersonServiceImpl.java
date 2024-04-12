package kr.or.ddit.case5.person.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.case4.bts.dao.BtsDAO;
import kr.or.ddit.case5.person.dao.PersonDAO;
import kr.or.ddit.case5.person.dao.PersonDAOImpl;
import kr.or.ddit.case5.person.exception.PersonNotFoundException;
import kr.or.ddit.vo.PersonVo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService{
	private final PersonDAO dao;
	
	@Override
	public List<PersonVo> retrievePersonList() {
		List<PersonVo> poeple = dao.selectPersonList();
		for(PersonVo once : poeple) {
			System.out.printf("%s 조회함.\n", once.getName());
		}
		return poeple;
	}

	@Override
	public PersonVo retrievePerson(String id) {
		PersonVo person = dao.selectPerson(id);
		if(person == null) 
			throw new PersonNotFoundException(id);
		return person;
	}

}
