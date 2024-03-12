<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>Http response packaging</h4>
<h4> http 의 기본 특성 : Connectfull(연결 지향형)x / ConnectLess(o), StateLess(o)</h4>
<pre>
	1. Response Line : 요청 처리 결과를 표현하는 세자리 숫자 (statuse code)
		1) 1xx : ING.., websocket에서 한정적으로 사용되는 상태코드.
		2) 2xx : 200(OK)
		3) 3xx : 아직 처리가 완료되지 않았으므로, 클라이언트의 추가 액션이 필요함.
				: response body가 없고, 연관된 헤더가 함께 전송됨.
				
				302/307 : MOVED, 이동한 이후의 주소를 응답과 함께 전송함(Location 헤더).
				304 : Not Modified
					브라우저의 캐싱 정책과 연관된 상태코드로 정적자원이 캐싱되어있는 경우, 캐싱 자원의 유효 여부를 알려줄때 사용됨.
					캐싱 : 클라이언트와 서버의 검색 속도를 빠르게하기위한 정책 -> 정적자원이 변경되지 않았다면 클라이언트는 캐시를 찾아서 정적자원을 서비스 한다.
		실패를 표현하는 상태코드
		4) 4xx : 클라이언트측 원인으로 처리 실패
			404 : <%= HttpServletResponse.SC_NOT_FOUND %>, 사용자의 URL 식별이 잘못됐을때 발생, 보호자의 url접근방법이 잘못됬을때
			400 : <%= HttpServletResponse.SC_BAD_REQUEST %>, 사용자의 요청을 검증하는 과정에서 주로 발생함
				ex) 필수 파라미터 누락,  잘못된 데이터 타입 전송, 잘못된 데이터 길이... 등을 검증할 때 직접 발생시킴.
			405 : <%= HttpServletResponse.SC_METHOD_NOT_ALLOWED %>, 
					서블릿의 기본 요청 콜백에 정의 된 생타코드로 재정의하기 전까지 기본전송되는 상태코드.
			401/403  : <%=HttpServletResponse.SC_UNAUTHORIZED %>, 허가되지 않은 요청 / <%=HttpServletResponse.SC_FORBIDDEN %>, 금지된 요청 -> 보안 접근제어에서 사용된다.
				401 : 인증되지 않은 사용자를 표현할 때 발생시킴
				403 : 허가(인가)되지 않은 사용자가 금지된 자원에 접근할 때 발생시킴
			406 : <%= HttpServletResponse.SC_NOT_ACCEPTABLE %>,
					accept 요청 헤더로 표현된 response content-type을 처리할 수 없을때 발생시킴.
			415 : <%= HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE %>,
						content-type 헤더와 함께 전송된 request body를 해석할 수 없을때 발생시킴
		5) 5xx : 서버측의 원인으로 요청 처리 실패 -> 서버의 원인을 제공하지 않기 위해서 상태코드는 대부분 500으로 처리한다.
	2. Response Header : response meta data 영역(name/value 로 구성되는 문자열)
	3. Response Body(Content Body, Message Body) : 응답 본문(content) 가 기록되는 영역
</pre>
</body>
</html>