<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/httpAuth.jsp</title>
</head>
<body>
<h4>Http프로토콜 인증 시스템</h4>
<pre>
보안처리의 기본 구조
인증(Authentication) + 인가(Authorization) 기반의 접근 제어 구조를 통한 보안 처리.
인증 : 사용자가 본인이 맞는지 신원을 확인하는 작업.
	1. was를 사용한 인메모리 구조
	2. 데이터 베이스를 사용한 구조
인가 : 보호자원에 대한 접근 권한을 획득했는지 여부를 확인하는 작업.
JAAS(Java Authentication Authorization Service) 프레임 워크에 의해 처리
	: 인증된 사용자 정보를 Principal의 구현체로 표현함
1. 헤더 기반의 인증
	ex)
	보호자원(/09/mbti)에 대해 미인증된 사용자가 접근한 경우,
	응답으로 401상태코드와 www-authenticate:authType[BASIC:BAERER] 헤더를 전송함.
	브라우저가 응답을 받고, 인증 정보를 입력받을 수 있는 UI를 선택함.
	인증된 후, 인증상태 정보 유지에 (authorization:authType[BASIC:BAERER] base64인코딩된 사용자 정보) 헤더를 시용함.
	1) BASIC : 브라우저가 가진 기본 인증 UI 활용 방식.
	2) BAERER : 토큰 기반의 인증 시스템, OAuth 프로토콜에서 사용되는 인증 방식.
		access token 표현
			authorization:Baerer encrypt(base64(token))
2. 세션 기반의 인증
	1) FORM : form UI를 활용하는 방식.
		action : j_security_check
		id parameter : j_username
		password parameter : j_password
		
</pre>
</body>
</html>