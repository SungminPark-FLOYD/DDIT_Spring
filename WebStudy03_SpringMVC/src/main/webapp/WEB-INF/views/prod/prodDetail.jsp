<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<table class="table table-bordered table-striped" >
		<tr>
			<th>상품코드</th>
			<td id="prodId">${prod.prodId}</td>
		</tr>
		<tr>
			<th>제조사정보</th>
			<td>
				<table class="table table-bordered table-striped">
					<thead class="table-dark">
						<tr>
							<th>제조사명</th>
							<th>소재지</th>
							<th>담당자명</th>
							<th>연락처</th>
							<th>팩스번호</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${prod.buyer.buyerName}</td>
							<td>${prod.buyer.buyerAdd1} ${prod.buyer.buyerAdd2}</td>
							<td>${prod.buyer.buyerCharger}</td>
							<td>${prod.buyer.buyerComtel}</td>
							<td>${prod.buyer.buyerFax}</td>
						</tr>
					</tbody>
				</table>
			</td>
	</tr>
	<tr>
			<th>구매자정보</th>
			<td>
				<table class="table table-bordered table-striped">
					<thead class="table-dark">
						<tr>
							<th>구매자</th>
							<th>이메일</th>
						</tr>
					</thead>
					<tbody>
						
						<c:forEach items="${prod.cartList }" var="cart">
						<tr>
							<td>
								${cart.member.memName }
							</td>
							<td>
								${cart.member.memMail }
							</td>
						</tr>
						</c:forEach>
						
					</tbody>
				</table>
			</td>
	</tr>
			<tr>
			<th>상품명</th>
			<td>${prod.prodName}</td>
		</tr>
		<tr>
			<th>상품분류</th>
			<td>${prod.prodLgu}</td>
		</tr>
		<tr>
			<th>거래처</th>
			<td>${prod.prodBuyer}</td>
		</tr>
		<tr>
			<th>구매가</th>
			<td>${prod.prodCost}</td>
		</tr>
		<tr>
			<th>판매가</th>
			<td>${prod.prodPrice}</td>
		</tr>
		<tr>
			<th>세일가</th>
			<td>${prod.prodSale}</td>
		</tr>
		<tr>
			<th>요약정보</th>
			<td>${prod.prodOutline}</td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td>${ prod.prodDetail}</td>
		</tr>
		<tr>
			<th>이미지</th>
			<td>
				<img src="<c:url value='/resources/prodImages/${prod.prodImg}'/>"/>
			</td>
		</tr>
		<tr>
			<th>총재고</th>
			<td>
				
			${prod.prodTotalstock}</td>
		</tr>
		
		<tr>
			<th>입고일</th>
			<td>${prod.prodInsdate}</td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td>${prod.prodProperstock}</td>
		</tr>
		<tr>
			<th>크기</th>
			<td>${prod.prodSize}</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>${prod.prodColor}</td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td>${prod.prodDelivery}</td>
		</tr>
		<tr>
			<th>단위</th>
			<td>${prod.prodUnit}</td>
		</tr>
		<tr>
			<th>입고량</th>
			<td>${prod.prodQtyin}</td>
		</tr>
		<tr>
			<th>출고량</th>
			<td>${prod.prodQtysale}</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${prod.prodMileage}</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="button" class="btn btn-secondary" id="updateBtn">수정</button>
			</td>
		</tr>
</table>
<script src="${pageContext.request.contextPath }/resources/js/app/prod/prodDetail.js"></script>