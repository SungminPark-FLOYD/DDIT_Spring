package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString.Exclude;

@Data
@EqualsAndHashCode(of = "buyerId")
public class BuyerVO implements Serializable{
	private String buyerId;
	private String buyerName;
	private String buyerLgu;
	private String buyerBank;
	@Exclude
	@JsonIgnore
	private transient String buyerBankno;
	private String buyerBankname;
	private String buyerZip;
	private String buyerAdd1;
	private String buyerAdd2;
	private String buyerComtel;
	private String buyerFax;
	private String buyerMail;
	private String buyerCharger;
	private String buyerTelext;
	
	private LprodVO lprod;
	private List<ProdVO> prodList;	//-> Has Many, Buyer(1) : PROD(N) -> BuyerVO has many ProdVO
	
	private int prodCount;
}
