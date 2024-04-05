package kr.or.ddit.vo;

import java.security.Principal;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberVOWrapper implements Principal{
	private MemberVO realUser;

	public MemberVOWrapper(MemberVO realUser) {
		super();
		this.realUser = realUser;
	}

	@Override
	public String getName() {
		return realUser.getMemId();
	}
	
}
