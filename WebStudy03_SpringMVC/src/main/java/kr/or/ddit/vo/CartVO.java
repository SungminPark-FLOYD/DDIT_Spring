package kr.or.ddit.vo;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"cartNo", "cartProd"})
public class CartVO implements Serializable{
	private String cartMember;
	private String cartNo;
	private String cartProd;
	private Long cartQty;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate cartDate;
	
	private MemberVO member;	//Has a
	private ProdVO prod;		//Has a
	
}
