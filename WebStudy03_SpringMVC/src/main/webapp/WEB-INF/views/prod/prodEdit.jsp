<%@page import="kr.or.ddit.prod.controller.ProdUpdateController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form modelAttribute="<%=ProdUpdateController.MODELNAME%>"
	method="post" enctype="application/x-www-form-urlencoded">
	<table class="table table-bordered">
		<tr>
			<th>상품코드</th>
			<td><form:input type="text" path="prodId" required="true"
					value="${prod.prodId}" class="form-control" />
				<from:errors path="prodId" cssClass="text-danger" element="span" /></td>
		</tr>
		<tr>
			<th>상품명</th>
			<td><form:input type="text" path="prodName" required="true"
					value="${prod.prodName}" class="form-control" />
				<from:errors path="prodName" cssClass="text-danger" element="span" /></td>
		</tr>
		<tr>
			<th>상품분류</th>
			<td><form:input type="hidden" path="prodLgu" required="true"
					value="${prod.prodLgu}" class="form-control" />
				<from:errors path="prodLgu" cssClass="text-danger" element="span" /></td>
		</tr>
		<tr>
			<th>거래처</th>
			<td><form:input type="hidden" path="prodBuyer" required="true"
					value="${prod.prodBuyer}" class="form-control" />
				<from:errors path="prodBuyer" cssClass="text-danger" element="span" /></td>
		</tr>
		<tr>
			<th>구매가</th>
			<td><form:input type="number" path="prodCost" required="true"
					value="${prod.prodCost}" class="form-control" />
				<from:errors path="prodCost" cssClass="text-danger" element="span" /></td>
		</tr>
		<tr>
			<th>판매가</th>
			<td><form:input type="number" path="prodPrice" required="true"
					value="${prod.prodPrice}" class="form-control" />
				<from:errors path="prodPrice" cssClass="text-danger" element="span" /></td>
		</tr>
		<tr>
			<th>세일가</th>
			<td><form:input type="number" path="prodSale" required="true"
					value="${prod.prodSale}" class="form-control" />
				<from:errors path="prodSale" cssClass="text-danger" element="span" /></td>
		</tr>
		<tr>
			<th>요약정보</th>
			<td><form:input type="text" path="prodOutline" required="true"
					value="${prod.prodOutline}" class="form-control" />
				<from:errors path="prodOutline" cssClass="text-danger"
					element="span" /></td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td><form:input type="text" path="prodDetail"
					value="${prod.prodDetail}" class="form-control" />
				<from:errors path="prodDetail" cssClass="text-danger" element="span" /></td>
		</tr>
		<tr>
			<th>이미지</th>
			<td><form:input type="text" path="prodImg" required="true"
					value="${prod.prodImg}" class="form-control" />
				<from:errors path="prodImg" cssClass="text-danger" element="span" /></td>
		</tr>
		<tr>
			<th>총재고</th>
			<td><form:input type="number" path="prodTotalstock"
					required="true" value="${prod.prodTotalstock}" class="form-control" />
				<from:errors path="prodTotalstock" cssClass="text-danger"
					element="span" /></td>
		</tr>
		<tr>
			<th>입고일</th>
			<td><form:input type="date" path="prodInsdate"
					value="${prod.prodInsdate}" class="form-control" />
				<from:errors path="prodInsdate" cssClass="text-danger"
					element="span" /></td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td><form:input type="number" path="prodProperstock"
					required="true" value="${prod.prodProperstock}"
					class="form-control" />
				<from:errors path="prodProperstock" cssClass="text-danger"
					element="span" /></td>
		</tr>
		<tr>
			<th>크기</th>
			<td><form:input type="text" path="prodSize"
					value="${prod.prodSize}" class="form-control" />
				<from:errors path="prodSize" cssClass="text-danger" element="span" /></td>
		</tr>
		<tr>
			<th>색상</th>
			<td><form:input type="text" path="prodColor"
					value="${prod.prodColor}" class="form-control" />
				<from:errors path="prodColor" cssClass="text-danger" element="span" /></td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td><form:input type="text" path="prodDelivery"
					value="${prod.prodDelivery}" class="form-control" />
				<from:errors path="prodDelivery" cssClass="text-danger"
					element="span" /></td>
		</tr>
		<tr>
			<th>단위</th>
			<td><form:input type="text" path="prodUnit"
					value="${prod.prodUnit}" class="form-control" />
				<from:errors path="prodUnit" cssClass="text-danger" element="span" /></td>
		</tr>
		<tr>
			<th>입고량</th>
			<td><form:input type="number" path="prodQtyin"
					value="${prod.prodQtyin}" class="form-control" />
				<from:errors path="prodQtyin" cssClass="text-danger" element="span" /></td>
		</tr>
		<tr>
			<th>출고량</th>
			<td><form:input type="number" path="prodQtysale"
					value="${prod.prodQtysale}" class="form-control" />
				<from:errors path="prodQtysale" cssClass="text-danger"
					element="span" /></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><form:input type="number" path="prodMileage"
					value="${prod.prodMileage}" class="form-control" />
				<from:errors path="prodMileage" cssClass="text-danger"
					element="span" /></td>
		</tr>



		<tr>
			<td colspan="2">
				<button type="submit">전송</button>
				<button type="reset">취소</button>
			</td>
		</tr>
	</table>
	</form:form>
	<script
		src="${pageContext.request.contextPath }/resources/js/app/prod/prodForm.js"></script>