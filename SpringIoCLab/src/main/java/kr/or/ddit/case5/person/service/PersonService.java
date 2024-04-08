package kr.or.ddit.case5.person.service;

import java.util.List;

import kr.or.ddit.vo.PersonVo;


/**
 * Business Logic Layer : 하나의 어플리케이션이 가진 특징적인 로직이 구현되는 객체
 * 
 * ex) 급여 명세서를 구현한다면
 * 사원+근태 -> Domain layer 설계
 * 사원 데이터와 근태기록 조회(select) : Persistence layer 구현
 * 사원데이터와 근태기록을 토대로 급여 정보 계산 : Business Logic Layer 구현.
 * -> WEB과 분리되어있는 레이어 : MODEL LAYER(Domain layer,Persistence layer,Business Logic Layer)
 * 		-> 실행 환경과 분리되어있는 독립적인 레이어 
 * 
 * 실행환경에 종속되는 레이어 설계 : Controller layer, View layer
 *
 */
public interface PersonService {

	public List<PersonVo> retrievePersonList();
	public PersonVo retrievePerson(String id);
}
