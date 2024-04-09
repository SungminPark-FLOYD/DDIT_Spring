package kr.or.ddit.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.ToString.Exclude;

@Data
@EqualsAndHashCode(of = "prodId")
//@ToString(exclude = "prodDetail")
public class ProdVO implements Serializable{
	private String prodId;
	@NotBlank
	private String prodName;
	private String prodLgu;
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
	
	
	
}
