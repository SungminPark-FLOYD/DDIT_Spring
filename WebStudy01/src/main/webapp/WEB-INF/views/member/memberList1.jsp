<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/includee/preScript.jsp"/>
<style type="text/css">
	tr[data-mem-id]{
		cursor: pointer;
	}
	tr[data-mem-id]:hover {
		color: green;
	}
</style>
</head>
<body data-url="${pageContext.request.contextPath }">


<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>

<table class="table table-bordered table-striped" >
	<thead class="table-dark">
		<tr>
			<th>회원명</th>
			<th>기본주소</th>
			<th>상세주소</th>
			<th>핸드폰번호</th>
			<th>메일주소</th>
			<th>마일리지</th>
		</tr>		
	</thead>
	
		<c:if test="${not empty memberList }">
		<c:forEach items="${memberList }" var="mem" >
			<tr data-mem-id="${mem.memId }" data-bs-toggle="modal" data-bs-target="#exampleModal">
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
	<tbody>
	</tbody>
</table>



<jsp:include page="/WEB-INF/includee/postScript.jsp"/>
<script src='<c:url value="/resources/js/member/memberList1.js"/>'></script>
</body>
</html>