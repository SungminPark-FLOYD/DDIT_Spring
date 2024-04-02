package kr.or.ddit.vo;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.ToString.Exclude;

@Data
@EqualsAndHashCode(of = "prodId")
//@ToString(exclude = "prodDetail")
public class ProdVO implements Serializable{
	private String prodId;
	private String prodName;
	private String prodLgu;
	private String prodBuyer;
	private Long prodCost;
	private Long prodPrice;
	private Long prodSale;
	private String prodOutline;
	@Exclude
	private String prodDetail;
	private String prodImg;
	private Long prodTotalstock;
	private LocalDate prodInsdate;
	private Long prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Long prodQtyin;
	private Long prodQtysale;
	private Long prodMileage;
	
	//has a 관계 표현 , 단수관계
	private BuyerVO buyer; //(1:1), PROD(1) : BUYER(1) --> ProdVO has a BuyerVO
	private LprodVO lprod; //(1:1), PROD(1) : LPROD(1) --> ProdVO has a lprodVO
	
	
}
