package kr.or.ddit.case07.vo;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class BankInfoVO {
	@NotBlank
	private String bankNo;
	@NotBlank
	private String bankName;
	@NotBlank
	private String bankUserName;
//	private LocalDate bankDate;
	@NotBlank
	private String bankDate;
}
