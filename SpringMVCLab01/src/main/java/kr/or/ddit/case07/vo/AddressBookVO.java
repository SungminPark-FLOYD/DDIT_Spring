package kr.or.ddit.case07.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class AddressBookVO {
	@NotNull
	private Long adrsNo;
	private String memId;
	private String adrsName;
	@Pattern(regexp = "\\d{3}-\\d{3,4}-\\d{4}")
	private String adrsTel;
	private String adrsAdd;
}
