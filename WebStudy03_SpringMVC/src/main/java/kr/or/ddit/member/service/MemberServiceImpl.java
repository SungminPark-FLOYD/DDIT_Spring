package kr.or.ddit.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.login.AuthenticateException;
import kr.or.ddit.login.BadCredentialException;
import kr.or.ddit.login.UserNotFoundException;
import kr.or.ddit.login.service.AuthenticateService;
import kr.or.ddit.login.service.AuthenticateServiceImpl;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberDAO dao;
	@Autowired
	private AuthenticateService authService;
	
	private void encryptMember(MemberVO member) { //call by reference 방식
		String plain = member.getMemPass();
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encoded = encoder.encode(plain);
		member.setMemPass(encoded);
	}
	
	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		if(dao.selectMember(member.getMemId()) != null) return result = ServiceResult.PKDUPLICATED;
		
		encryptMember(member);
		int cnt = dao.insertMember(member);
		result = cnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		
		return result;
	}

	@Override
	public List<MemberVO> retrieveMemberList() {
		List<MemberVO> memberList = dao.selectMemberList();
		return memberList;
	}

	@Override
	public MemberVO retrieveMember(String memId) throws PkNotFoundException {
		MemberVO member = dao.selectMember(memId);
		if(member == null) throw new PkNotFoundException(500);
		return member;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) throws PkNotFoundException {
		//나머지는 runtimeexception이기 때문에 따로 처리를 하지 않아도 된다
		try {
			authService.authenticate(member);
			return dao.update(member) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}catch (BadCredentialException e) {
			return ServiceResult.INVALIDPASSWORD;
		}
		
	}

	@Override
	public ServiceResult removeMember(MemberVO inputData) throws PkNotFoundException {
		//나머지는 runtimeexception이기 때문에 따로 처리를 하지 않아도 된다
		try {
			authService.authenticate(inputData);
			return dao.delete(inputData.getMemId()) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}catch (BadCredentialException e) {
			return ServiceResult.INVALIDPASSWORD;
		}
	}

}
