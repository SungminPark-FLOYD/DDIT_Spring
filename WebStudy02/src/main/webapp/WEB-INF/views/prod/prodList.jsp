<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<table class="table table-bordered table-striped" >
	<thead class="table-dark">
		<tr>
			<th>상품명</th>
			<th>상품분류</th>
			<th>거래처</th>
			<th>구매가</th>
			<th>판매가</th>
			<th>입고일</th>
			<th>마일리지</th>
			<th>거래처명</th>
			<th>분류명</th>
		</tr>		
	</thead>
	<tbody>
		<c:if test="${not empty prodList }">
		<c:forEach items="${prodList }" var="prod" >
			<tr data-mem-id="${prod.prodId }">
				<c:url value="/prod/prodDetail.do" var="detailUrl"> 
						<c:param name="who" value="${prod.prodId}"/>
					</c:url>
				<td><a href=" ${detailUrl }">${prod.buyer.buyerName }</a></td>
				<td>${prod.lprod.lprodNm }</td>
				<td>${prod.prodName }</td>
				<td>${prod.prodLgu }</td>
				<td>${prod.prodBuyer }</td>
				<td>${prod.prodCost }</td>
				<td>${prod.prodPrice }</td>
				<td>${prod.prodInsdate }</td>
				<td>${prod.prodMileage }</td>
			</tr>
		</c:forEach>
		</c:if>
		<c:if test="${empty prodList }">
			<tr>
				<td colspan="10">상품 정보 없음</td>
			</tr>
		</c:if>
	</tbody>
</table>
	
