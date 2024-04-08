package kr.or.ddit.case5.person.exception;

public class PersonNotFoundException extends RuntimeException{

	public PersonNotFoundException(String id) {
		super(String.format("해당[%s] 사용자가 존재하지 않음.", id));
	}
	
}
