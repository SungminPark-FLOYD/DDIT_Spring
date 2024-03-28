package kr.or.ddit.person.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import kr.or.ddit.vo.PersonVo;

public class PersonDAOImpl implements PersonDAO {
	private Properties props;
	
	public PersonDAOImpl() {
		super();
		props = new Properties();
		
		try(
			InputStream is = this.getClass().getResourceAsStream("/kr/or/ddit/MemberData.properties");
		){
			props.load(is);
		} catch (IOException e) {
			//checked EX를 unchecked EX로 전환한다.
			throw new UncheckedIOException(e);
		}
	}
	
	private PersonVo rawToObject(String id, String rawData) {
		String[] tokens = rawData.split("\\|");
		PersonVo person = new PersonVo(id, tokens[0], tokens[1], tokens[2], tokens[3]);
		
		return person;
	}
	@Override
	public List<PersonVo> selectPersonList(){
		List<PersonVo> people = new ArrayList<PersonVo>();
		
		Enumeration<Object> keys = props.keys();
		while (keys.hasMoreElements()) {
			Object key = (Object) keys.nextElement();
			Object value = props.get(key);
			PersonVo person = rawToObject(key.toString(), value.toString());
			people.add(person);			
		}
		
		return people;
	}
	@Override
	public PersonVo selectPerson(String id) {
		String property = props.getProperty(id);
		
//		if(property != null) {
//			PersonVo person = rawToObject(id, property);			
//			return person;
//		}else {
//			return null;
//		}
		
		//java 8 의 문법
		//람다식에서 리턴값을 바로 호출하는 구조이면 {}를 생략하고 return을 생략할 수 있다.
		return Optional.ofNullable(property).map((p)->rawToObject(id, p)).orElse(null);
		
	}
}
