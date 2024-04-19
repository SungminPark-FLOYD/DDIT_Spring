package kr.or.ddit.case07.vo;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Data
public class BankInfoVO {
	@NotBlank
	private String bankNo;
	@NotBlank
	private String bankName;
	@NotBlank
	private String bankUserName;
	
	//date타입을 파싱하기위한 장치
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate bankDate;
}
