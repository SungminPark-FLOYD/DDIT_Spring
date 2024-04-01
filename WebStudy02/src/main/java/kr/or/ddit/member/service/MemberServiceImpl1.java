package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl1 implements MemberService {
	private MemberDAO dao = new MemberDAOImpl();
	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		if(dao.selectMember(member.getMemId()) != null) return result = ServiceResult.PKDUPLICATED;
		
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
		ServiceResult result = null;
		if(!dao.selectMember(member.getMemId()).getMemPass().equals(member.getMemPass())) return result = ServiceResult.INVALIDPASSWORD;
		
		int cnt = dao.update(member);
		result = cnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		return result;
	}

	@Override
	public ServiceResult removeMember(MemberVO inputData) throws PkNotFoundException {
		ServiceResult result = null;
		if(!dao.selectMember(inputData.getMemId()).getMemPass().equals(inputData.getMemPass())) return result = ServiceResult.INVALIDPASSWORD;
		
		int cnt = dao.delete(inputData.getMemId());
		result = cnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		return result;
	}

}
