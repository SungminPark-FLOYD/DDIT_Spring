<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- 포스트 메소드의 enctype 설정 -->
<!-- 기존의 html 태그도 사용할 수 있다 -->
<!-- modelAttribute="bank"로 초기화 -->
<form:form modelAttribute="address">
	<form:input path="adrsNo" placeholder="주소번호" />
	<form:errors path="adrsNo"/>
	<form:input path="memId"  placeholder="회원아이디"/>
	<form:errors path="memId"/>
	<form:input path="adrsName" placeholder="주소명"/>
	<form:errors path="adrsName"/>
	<form:input path="adrsTel" placeholder="전화번호"/>
	<form:errors path="adrsTel"/>
	<form:input path="adrsAdd" placeholder="주소"/>
	<form:errors path="adrsAdd"/>
	<button type="submit">전송</button>
</form:form>

