package kr.or.ddit.person.service;

import java.util.List;

import kr.or.ddit.person.dao.PersonDAO;
import kr.or.ddit.person.dao.PersonDAOImpl;
import kr.or.ddit.person.exception.PersonNotFoundException;
import kr.or.ddit.vo.PersonVo;

public class PersonServiceImpl implements PersonService{
	private PersonDAO dao = new PersonDAOImpl();
	
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
