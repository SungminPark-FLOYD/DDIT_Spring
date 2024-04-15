<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h4>입력 양식 구성</h4>
<!-- 포스트 메소드의 enctype 설정 -->
<!-- 기존의 html 태그도 사용할 수 있다 -->
<!-- modelAttribute="bank"로 초기화 -->
<form:form modelAttribute="bank">
	<form:input path="bankNo" placeholder="계좌번호" />
	<form:errors path="bankNo"/>
	<form:input path="bankName"  placeholder="은행명"/>
	<form:errors path="bankName"/>
	<form:input path="bankUserName" placeholder="계좌주"/>
	<form:errors path="bankUserName"/>
	<form:input path="bankDate" placeholder="개설일"/>
	<form:errors path="bankDate"/>
	<button type="submit">전송</button>
</form:form>

<%-- <form method="post" enctype="application/x-www-form-urlencoded"> --%>
<%-- 	<input type="text" name="bankNo" placeholder="계좌번호" value="${bank.bankNo }"/> --%>
<%-- 	<input type="text" name="bankName" placeholder="은행명" value="${bank.bankName }"/> --%>
<%-- 	<input type="text" name="bankUserName" placeholder="계좌주" value="${bank.bankUserName }"/> --%>
<%-- 	<input type="date" name="bankDate" placeholder="개설일" value="${bank.bankDate }"/> --%>
<!-- 	<button type="submit">전송</button> -->
<%-- </form> --%>