package kr.or.ddit.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.ibatis.session.LocalCacheScope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.ToString.Exclude;

@Data
@EqualsAndHashCode(of = "prodId")
//@ToString(exclude = "prodDetail")
public class ProdVO implements Serializable{
	@NotBlank(groups = UpdateGroup.class)
	private String prodId;
	@NotBlank
	private String prodName;
	@NotBlank(groups = InsertGroup.class)
	private String prodLgu;
	@NotBlank(groups = InsertGroup.class)
	private String prodBuyer;
	@Min(0)
	private long prodCost;
	@Min(0)
	private long prodPrice;
	@Min(0)
	private long prodSale;
	@NotBlank
	private String prodOutline;
	@Exclude
	private String prodDetail;
	@NotBlank
	private String prodImg;
	@Min(0)
	private long prodTotalstock;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate prodInsdate;
	@NotNull
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
	
	private List<CartVO> cartList;
	
	
}
