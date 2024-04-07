<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<form method="post" enctype="application/x-www-form-urlencoded">
	<table class="table table-bordered">
			<tr>
				<th>회원번호</th>
				<td><input type="text" name="memId" 
					value="${member.memId}" class="form-control" />
					<span class="text-danger">${errors.memId}</span></td>
			</tr>
			<tr>
				<th>암호</th>
				<td><input type="password" name="memPass"  class="form-control" /><span
					class="text-danger">${errors.memPass}</span></td>
			</tr>
			<tr>
				<th>회원명</th>
				<td><input type="text" name="memName" 
					value="${member.memName}" class="form-control" /><span
					class="text-danger">${errors.memName}</span></td>
			</tr>
			<tr>
				<th>앞자리 주민번호</th>
				<td><input type="text" name="memRegno1"
					value="${member.memRegno1}" class="form-control" /><span
					class="text-danger">${errors.memRegno1}</span></td>
			</tr>
			<tr>
				<th>뒷자리 주민번호</th>
				<td><input type="text" name="memRegno2"
					value="${member.memRegno2}" class="form-control" /><span
					class="text-danger">${errors.memRegno2}</span></td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><input type="date" name="memBir" value="${member.memBir}"
					class="form-control" /><span class="text-danger">${errors.memBir}</span></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" name="memZip" required
					value="${member.memZip}" class="form-control" /><span
					class="text-danger">${errors.memZip}</span></td>
			</tr>
			<tr>
				<th>기본주소</th>
				<td><input type="text" name="memAdd1" required
					value="${member.memAdd1}" class="form-control" /><span
					class="text-danger">${errors.memAdd1}</span></td>
			</tr>
			<tr>
				<th>상세주소</th>
				<td><input type="text" name="memAdd2" required
					value="${member.memAdd2}" class="form-control" /><span
					class="text-danger">${errors.memAdd2}</span></td>
			</tr>
			<tr>
				<th>집전화</th>
				<td><input type="text" name="memHometel"
					value="${member.memHometel}" class="form-control" /><span
					class="text-danger">${errors.memHometel}</span></td>
			</tr>
			<tr>
				<th>회사전화</th>
				<td><input type="text" name="memComtel"
					value="${member.memComtel}" class="form-control" /><span
					class="text-danger">${errors.memComtel}</span></td>
			</tr>
			<tr>
				<th>핸드폰번호</th>
				<td><input type="text" name="memHp" value="${member.memHp}"
					class="form-control" /><span class="text-danger">${errors.memHp}</span></td>
			</tr>
			<tr>
				<th>메일주소</th>
				<td><input type="text" name="memMail" required
					value="${member.memMail}" class="form-control" /><span
					class="text-danger">${errors.memMail}</span></td>
			</tr>
			<tr>
				<th>직업</th>
				<td><input type="text" name="memJob" value="${member.memJob}"
					class="form-control" /><span class="text-danger">${errors.memJob}</span></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="text" name="memLike" value="${member.memLike}"
					class="form-control" /><span class="text-danger">${errors.memLike}</span></td>
			</tr>
			<tr>
				<th>기념일 종류</th>
				<td><input type="text" name="memMemorial"
					value="${member.memMemorial}" class="form-control" /><span
					class="text-danger">${errors.memMemorial}</span></td>
			</tr>
			<tr>
				<th>기념일자</th>
				<td><input type="date" name="memMemorialday"
					value="${member.memMemorialday}" class="form-control" /><span
					class="text-danger">${errors.memMemorialday}</span></td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td><input type="number" name="memMileage"
					value="${member.memMileage}" class="form-control" /><span
					class="text-danger">${errors.memMileage}</span></td>
			</tr>
			<tr>
				<th>삭제여부</th>
				<td><input type="text" name="memDelete"
					value="${member.memDelete}" class="form-control" /><span
					class="text-danger">${errors.memDelete}</span></td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="submit">전송</button>
					<button type="reset">취소</button>
				</td>
			</tr>
		</table>
</form>
<script src='<c:url value="/resources/js/member/memberForm.js"/>'></script>
