package kr.or.ddit.login.service;

import kr.or.ddit.login.AuthenticateException;
import kr.or.ddit.vo.MemberVO;

/**
 * 
 * 사용자 인증 용 business logic
 *
 */
public interface AuthenticateService {
	
	/**
	 * 사용자 인증
	 * @return 인증에 통과한 사용자에 대한 정보
	 * @throws AuthenticateException 인증 실패시 발생할 예외
	 * 			존재하지 않는 사용자일때, UserNotFoundException 발생
	 * 			비밀번호 인증 실패일때, BadCredentialException 발생
	 */
	public MemberVO authenticate(MemberVO member) throws AuthenticateException;
}
