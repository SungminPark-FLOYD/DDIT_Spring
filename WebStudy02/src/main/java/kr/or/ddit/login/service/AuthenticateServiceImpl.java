package kr.or.ddit.login.service;

import kr.or.ddit.login.AuthenticateException;
import kr.or.ddit.login.BadCredentialException;
import kr.or.ddit.login.UserNotFoundException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements AuthenticateService{
	private MemberDAO dao = new MemberDAOImpl();

	@Override
	public MemberVO authenticate(MemberVO member) throws AuthenticateException {
		MemberVO saved = dao.selectMember(member.getMemId());
		if(saved == null) throw new UserNotFoundException(String.format("%s 사용자 없음", member.getMemId()));
		
		if(saved.isMemDelete())
			throw new AuthenticateException("이미 탈퇴한 회원");

		if(member.getMemPass().equals(saved.getMemPass())) {
			return saved;
		}else {
			throw new BadCredentialException("비밀번호 인증실패");
		}		
	}

}
