package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.ProdVO;

/**
 * 
 * 상품관리(CRUD)
 *
 */
public interface ProdDAO {
	/**
	 * 상품 등록
	 * @param prod
	 * @return 성공하면 1이상 실패하면 0반환
	 */
	public int insertProd(ProdVO prod);
	
	/**
	 * 전체 상품 리스트 조회
	 * 상품코드 ,거래처 코드, 분류코드, 상품명, 구매가, 판매가, 마일리지, 입고일
	 * @return 상품이없으면 길이가 0인 리스트 반환
	 */
	public List<ProdVO> selectProdList();
	

	/**
	 * 선택 상품 조회
	 * @param prodId
	 * @return 실패하면 null반환
	 */
	public ProdVO selectProd(@Param("prodId") String prodId);
	
	/**
	 * 상품 수정
	 * @param prod
	 * @return성공하면 1이상 실패하면 0반환
	 */
	public int updateProd(ProdVO prod);

	
	
}
