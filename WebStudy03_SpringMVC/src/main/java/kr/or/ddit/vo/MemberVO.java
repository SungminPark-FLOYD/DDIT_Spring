package kr.or.ddit.vo;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * 회원관리를 위한 Domain Layer
 * 
 * 	1번 그룹(가입시 검증 그룹) : 아이디, 주민번호1, 주민번호2, + 기본 그룹
 *  2번 그룹(수정시 검증 그룹) : + 기본그룹
 *  기본 그룹(가입과 수정시 모두 검증 그룹 : 비밀번호,  이름, 우편번호, 주소1, 주소2, 이메일
 *  3번 그룹(탈퇴시 검증 그룹) : 비밀번호
 */
@Data
@ToString(exclude = {"memPass","memRegno1","memRegno2"})
@EqualsAndHashCode(of = {"memId"})
public class MemberVO implements Serializable{
	private int rnum;
	@NotBlank(groups = InsertGroup.class)
	private String memId;
	@NotBlank(groups = {DeleteGroup.class, Default.class})
	@Size(min = 4, max = 12, groups = {DeleteGroup.class, Default.class})
	@JsonIgnore
	private transient String memPass;
	@NotBlank
	@Size(max = 20)
	private String memName;
	@NotBlank(message = "주민번호1번 누락", groups = InsertGroup.class)
	@JsonIgnore
	private transient String memRegno1;
	@NotBlank(groups = InsertGroup.class)
	@JsonIgnore
	private transient String memRegno2;
	private String memBir;
	@NotBlank
	private String memZip;
	@NotBlank
	private String memAdd1;
	@NotBlank
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	@NotBlank
	@Email
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	private String memMemorialday;
	private Long memMileage;
	private boolean memDelete;
	
	//구매기록 중복허용 불가
	@JsonIgnore
	private transient Set<CartVO> cart; //has many
	
	//사용자 역할 정보
	private String memRole;
	
	//BLOB로 저장되는 정보 받기
	private byte[] memImg;
	public void setMemImage(MultipartFile memImage) throws IOException {
		if(memImage.isEmpty()) return;
		this.memImage = memImage;
		this.memImg = memImage.getBytes();
	}
	private MultipartFile memImage;
	
	public String getMemImgBase64() {
		if(memImg == null) return null;
		return Base64.getEncoder().encodeToString(memImg);
	}
	
	
	
	
	
}
