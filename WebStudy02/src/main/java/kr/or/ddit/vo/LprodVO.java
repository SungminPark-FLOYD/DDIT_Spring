package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * DataMapper를 사용해서 multi entity를 조회하는 단계
 * 
 * 1. 사용할 entity 결정, LPROD, BUYER
 * 2. ENTITY 하나당 하나의 vo 모델링
 * 3. ENTITY 간의 관계를 파악
 * 		1:1, 1:N, N:M
 * 4. VO간의 관계를 ENTITY간의 관계를 반영하여 모델링
 * 		1:1(has a), 1:N(has many)
 * 5. join 쿼리문작성
 * 6. resultType 대신 resultMap으로 조회 결과를 바인딩
 * 		1:1 - association으로 바인드
 * 		1:N - collection으로 바인드
 */
@Data
@EqualsAndHashCode(of = "lprodGu")
public class LprodVO {
	private Long lprodId;
	private String lprodGu;
	private String lprodNm;
	
	private List<BuyerVO> buyerList; //has many
}
