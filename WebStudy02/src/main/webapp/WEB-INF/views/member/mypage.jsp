<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:if test="${not empty message }">
	<script>
		alert("${message}")
	</script>
	<c:remove var="message" scope="session"/>
</c:if>
<jsp:include page="/WEB-INF/includee/preScript.jsp"/>
</head>
<body>
<h4>${member.memName }의 마이페이지</h4>
<table class="table table-bordered">
			<tr>
				<th>회원번호</th>
				<td>${member.memId}</td>
			</tr>
			<tr>
				<th>암호</th>
				<td>${member.memPass}</td>
			</tr>
			<tr>
				<th>회원명</th>
				<td>${member.memName}</td>
			</tr>
			<tr>
				<th>앞자리 주민번호</th>
				<td>${member.memRegno1}</td>
			</tr>
			<tr>
				<th>뒷자리 주민번호</th>
				<td>${member.memRegno2}</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td>${member.memBir}</td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td>${member.memZip}</td>
			</tr>
			<tr>
				<th>기본주소</th>
				<td>${member.memAdd1}</td>
			</tr>
			<tr>
				<th>상세주소</th>
				<td>${member.memAdd2}</td>
			</tr>
			<tr>
				<th>집전화</th>
				<td>${member.memHometel}</td>
			</tr>
			<tr>
				<th>회사전화</th>
				<td>${member.memComtel}</td>
			</tr>
			<tr>
				<th>핸드폰번호</th>
				<td>${member.memHp}</td>
			</tr>
			<tr>
				<th>메일주소</th>
				<td>${member.memMail}</td>
			</tr>
			<tr>
				<th>직업</th>
				<td>${member.memJob}</td>
			</tr>
			<tr>
				<th>취미</th>
				<td>${member.memLike}</td>
			</tr>
			<tr>
				<th>기념일 종류</th>
				<td>${member.memMemorial}</td>
			</tr>
			<tr>
				<th>기념일자</th>
				<td>${member.memMemorialday}</td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td>${member.memMileage}</td>
			</tr>
			<tr>
				<th>삭제여부</th>
				<td>${member.memDelete}</td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="button" class="btn btn-primary" id="updateBtn" >수정하기</button>
					<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal" >탈퇴</button>
				</td>
			</tr>
			

</table>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
     	<form id="deleteForm" method="post" action="${pageContext.request.contextPath }/member/memberDelete.do">
      <div class="modal-body">
				<input type="password" name="password" required/>
      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-danger">탈퇴</button>
     </div>
	</form>
    </div>
  </div>
</div>
<script>
	const $updateBtn = $('#updateBtn').on('click', function() {
		let who = $(this).data("who");
		location.href = `${pageContext.request.contextPath}/member/memberUpdate.do`;
	})
</script>
<jsp:include page="/WEB-INF/includee/postScript.jsp"/>
</body>
</html>