package kr.or.ddit.member.service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.login.AuthenticateException;
import kr.or.ddit.login.BadCredentialException;
import kr.or.ddit.login.UserNotFoundException;
import kr.or.ddit.login.service.AuthenticateService;
import kr.or.ddit.login.service.AuthenticateServiceImpl;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberDAO dao;
	@Autowired
	private AuthenticateService authService;
	
	//웹자원이기때문에 동적으로 위치를 잡아준다
	@Value("/resources/memberImages/")
	private Resource memberImages;
	
	private void encryptMember(MemberVO member) { //call by reference 방식
		String plain = member.getMemPass();
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encoded = encoder.encode(plain);
		member.setMemPass(encoded);
	}
	private void processImage(MemberVO member) {
		MultipartFile uploadFile = member.getMemImage();
		if(uploadFile == null) return;
		try {
			Resource saveRes = memberImages.createRelative(uploadFile.getOriginalFilename());
			FileUtils.copyInputStreamToFile(uploadFile.getInputStream(), saveRes.getFile());
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
	
	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		if(dao.selectMember(member.getMemId()) != null) return result = ServiceResult.PKDUPLICATED;
		
		encryptMember(member);
		if(dao.insertMember(member) > 0) {
			processImage(member);
			return ServiceResult.OK;
		}else {
			return ServiceResult.FAIL;
		}		
	}

	@Override
	public List<MemberVO> retrieveMemberList(PaginationInfo paging) {
		int totalRecord = dao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		return dao.selectMemberList(paging);
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
