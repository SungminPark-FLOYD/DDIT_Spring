package kr.or.ddit.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "adrsNo")
public class AdressBookVO {
	private Long adrsNo;
	private String memId;
	private String adrsName;
	private String adrsTel;
	private String adrsAdd;
	
	
	private MemberVO member;
}
