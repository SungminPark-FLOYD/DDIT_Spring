<%@page import="kr.or.ddit.prod.controller.ProdInsertController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<form:form modelAttribute="${ProdInsertController.MODELNAME }"
	method="post" enctype="application/x-www-form-urlencoded">
	<table class="table table-bordered">
		<input type="hidden" name="prodId" value="${prod.prodId }">
		<tr>
			<th>상품명</th>
			<td><form:input type="text" path="prodName" required="true"
					 class="form-control" />
				<form:errors path="prodName" cssClass="text-danger" element="span" /></td>
		</tr>
		<tr>
			<th>상품분류</th>
			<td>
				<form:select path="prodLgu" items="${lprodList }" itemValue="lprodGu" itemLabel="lprodNm"/>
				<form:errors path="prodLgu" element="span" cssClass="text-danger"/></td>
		</tr>
		<tr>
			<th>거래처</th>
			<td>
				<form:select path="prodBuyer">
					<option value>제조사 선택</option>
					<c:forEach items="${buyerList }" var="buyer">
						<form:option value="${buyer.buyerId }" label="${buyer.buyerName }" cssClass="${buyer.buyerLgu }"></form:option>
					</c:forEach>
				</form:select>
				<form:errors path="prodBuyer" element="span" cssClass="text-danger"/>
			</td>
		</tr>
		<tr>
			<th>구매가</th>
			<td><form:input type="number" path="prodCost" required="true"
					 class="form-control" />
				<form:errors path="prodCost" cssClass="text-danger" element="span" /></td>
		</tr>
		<tr>
			<th>판매가</th>
			<td><form:input type="number" path="prodPrice" required="true"
					 class="form-control" />
				<form:errors path="prodPrice" cssClass="text-danger" element="span" /></td>
		</tr>
		<tr>
			<th>세일가</th>
			<td><form:input type="number" path="prodSale" required="true"
					 class="form-control" />
				<form:errors path="prodSale" cssClass="text-danger" element="span" /></td>
		</tr>
		<tr>
			<th>요약정보</th>
			<td><form:input type="text" path="prodOutline" required="true"
					 class="form-control" />
				<form:errors path="prodOutline" cssClass="text-danger"
					element="span" /></td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td><form:input type="text" path="prodDetail"
					 class="form-control" />
				<form:errors path="prodDetail" cssClass="text-danger" element="span" /></td>
		</tr>
		<tr>
			<th>이미지</th>
			<td><form:input type="text" path="prodImg" required="true"
					 class="form-control" />
				<form:errors path="prodImg" cssClass="text-danger" element="span" /></td>
		</tr>
		<tr>
			<th>총재고</th>
			<td><form:input type="number" path="prodTotalstock"
					required="true"  class="form-control" />
				<form:errors path="prodTotalstock" cssClass="text-danger"
					element="span" /></td>
		</tr>
		<tr>
			<th>입고일</th>
			<td><form:input type="date" path="prodInsdate"
					 class="form-control" />
				<form:errors path="prodInsdate" cssClass="text-danger"
					element="span" /></td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td><form:input type="number" path="prodProperstock"
					required="true" 
					class="form-control" />
				<form:errors path="prodProperstock" cssClass="text-danger"
					element="span" /></td>
		</tr>
		<tr>
			<th>크기</th>
			<td><form:input type="text" path="prodSize"
					 class="form-control" />
				<form:errors path="prodSize" cssClass="text-danger" element="span" /></td>
		</tr>
		<tr>
			<th>색상</th>
			<td><form:input type="text" path="prodColor"
					class="form-control" />
				<form:errors path="prodColor" cssClass="text-danger" element="span" /></td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td><form:input type="text" path="prodDelivery"
					 class="form-control" />
				<form:errors path="prodDelivery" cssClass="text-danger"
					element="span" /></td>
		</tr>
		<tr>
			<th>단위</th>
			<td><form:input type="text" path="prodUnit"
					 class="form-control" />
				<form:errors path="prodUnit" cssClass="text-danger" element="span" /></td>
		</tr>
		<tr>
			<th>입고량</th>
			<td><form:input type="number" path="prodQtyin"
					 class="form-control" />
				<form:errors path="prodQtyin" cssClass="text-danger" element="span" /></td>
		</tr>
		<tr>
			<th>출고량</th>
			<td><form:input type="number" path="prodQtysale"
					 class="form-control" />
				<form:errors path="prodQtysale" cssClass="text-danger"
					element="span" /></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><form:input type="number" path="prodMileage"
					 class="form-control" />
				<form:errors path="prodMileage" cssClass="text-danger"
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
