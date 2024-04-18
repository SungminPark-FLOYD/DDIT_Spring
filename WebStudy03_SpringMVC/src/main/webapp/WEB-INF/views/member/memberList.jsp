<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 

<style type="text/css">
	tr[data-mem-id]{
		cursor: pointer;
	}
	tr[data-mem-id]:hover {
		color: green;
	}
</style>

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <table>
				<tr>
					<th>회원번호</th>
					<td id="memId"></td>
				</tr>
				<tr>
					<th>회원명</th>
					<td id="memName"></td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td id="memBir"></td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td id="memZip"></td>
				</tr>
				<tr>
					<th>기본주소</th>
					<td id="memAdd1"></td>
				</tr>
				<tr>
					<th>상세주소</th>
					<td id="memAdd2"></td>
				</tr>
				<tr>
					<th></th>
					<td id="memHometel"></td>
				</tr>
				<tr>
					<th></th>
					<td id="memComtel"></td>
				</tr>
				<tr>
					<th>핸드폰번호</th>
					<td id="memHp"></td>
				</tr>
				<tr>
					<th>메일주소</th>
					<td id="memMail"></td>
				</tr>
				<tr>
					<th>직업</th>
					<td id="memJob"></td>
				</tr>
				<tr>
					<th>취미</th>
					<td id="memLike"></td>
				</tr>
				<tr>
					<th>기념일 종류</th>
					<td id="memMemorial"></td>
				</tr>
				<tr>
					<th>기념일자</th>
					<td id="memMemorialday"></td>
				</tr>
				<tr>
					<th>마일리지</th>
					<td id="memMileage"></td>
				</tr>
				<tr>
					<th>삭제여부</th>
					<td id="memDelete"></td>
				</tr>
			</table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        
      </div>
    </div>
  </div>
</div>

<a href="${pageContext.request.contextPath }/member/memberInsert.do">회원등록</a>
<table class="table table-bordered table-striped" >
	<thead class="table-dark">
		<tr>
			<th>일련번호</th>
			<th>회원명</th>
			<th>기본주소</th>
			<th>상세주소</th>
			<th>핸드폰번호</th>
			<th>메일주소</th>
			<th>마일리지</th>
		</tr>		
	</thead>
	<tbody>
		<c:if test="${not empty memberList }">
		<c:forEach items="${memberList }" var="mem" >
		<c:set value="${mem.memId eq lastCreated.memId ? 'active' : ''}" var="clzValue"/>
			<tr class="${clzValue }" data-mem-id="${mem.memId }" data-bs-toggle="modal" data-bs-target="#exampleModal">
				<td>${mem.rnum }</td>
				<td>${mem.memName }</td>
				<td>${mem.memAdd1 }</td>
				<td>${mem.memAdd2 }</td>
				<td>${mem.memHp }</td>
				<td>${mem.memMail }</td>
				<td>${mem.memMileage }</td>
			</tr>
		</c:forEach>
		</c:if>
		<c:if test="${empty memberList }">
			<tr>
				<td colspan="6">회원 정보 없음</td>
			</tr>
		</c:if>
		<!-- 세션 삭제 -->
		<c:remove var="lastCreated" scope="session"/>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				<div id="searchUI">
					<form:select path="paging.simpleCondition.searchType">
						<form:option value="" label="전체" />
						<form:option value="memName" label="이름" />
						<form:option value="memAdd1" label="지역" />
					</form:select>
					<form:input path="paging.simpleCondition.searchWord"/>
					<button id="searchBtn">검색</button>
				</div>
				${pagingHTML }
<!-- 				이름, 지역, 전체 세가지 검색 조건으로 검색 및 페이징 커리 -->
<!-- 				recordCount = 3, page = 2 -->
			</td>
		</tr>
	</tfoot>
	
</table>
<form:form modelAttribute="paging" action="${pageContext.request.contextPath }/member/memberList.do" 
	id="searchForm" method="get">
	<form:input path="simpleCondition.searchType"/>
	<form:input path="simpleCondition.searchWord"/>
	<input type="number" name="currentPage" value="1"/>
	<form:input path="currentPage"/>
</form:form>
<script>	
	function ${pageFunction}(page) {
	//		location.href="?currentPage="+page;
		searchForm.currentPage.value = page;
		$searchForm.submit();
	}
	
	//searchBtn을 클릭하면, searchUI가 가진 모든 입력값을 searchForm으로 복사하고, searchForm을 전송
	const $searchForm = $("#searchForm");
	$("#searchBtn").on("click", function(event){
		let $searchUI = $(this).parents("#searchUI");
		$searchUI.find(":input[name]").each(function(idx, ipt) {
			let name = this.name;
			let value = $(this).val();
			
			searchForm[name].value = value;
	//			searchForm.searchType.value = value;
	//			$searchForm.find(`[name='\${name}']`).val(value);
		});
		$searchForm.submit();
	}) ;
</script>

<script src='<c:url value="/resources/js/member/memberList.js"/>'></script>
