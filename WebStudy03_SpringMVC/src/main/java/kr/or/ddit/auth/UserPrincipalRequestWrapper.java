package kr.or.ddit.auth;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;

public class UserPrincipalRequestWrapper extends HttpServletRequestWrapper{

	public UserPrincipalRequestWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Principal getUserPrincipal() {
		HttpSession session = getSession();
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		if(authMember != null) {
			return new MemberVOWrapper(authMember);
		}else {
			return super.getUserPrincipal();
		}
	}

}
