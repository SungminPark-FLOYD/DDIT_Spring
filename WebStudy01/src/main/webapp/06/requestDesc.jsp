<%@ page language="java"
    pageEncoding="UTF-8"%>
<%
	String accept = request.getHeader("accept");
	if(accept.contains("json")){
		response.setContentType("application/json;charset=UTF-8");
		%>
		{
			"result":"정상 서비스 완료"
		}
		<%
	}else {
		response.setContentType("text/html;charset=UTF-8");
			
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/requestDesc.jsp</title>
</head>
<body>
<h4>request(HttpServletRequest)</h4>
<form method="post">
	<%-- put 요청을 보낼때 --%>
	<input type="hidden" name="_method" value="put">
	<input type="text" name="param1" value="VALUE1">
	<input type="text" name="param2" value="한글값">
	<input type="file" name="sendFile">
	<button type="submit">전송</button>
</form>
<pre>
	: http 프로토콜로 패키징된 요청에 대한 정보와 해당 요청을 발생시킨 클라이언트에 대한 정보를 가진 객체
	
	HTTP(Hyper Text Transfer Protocol) : HTML 문서 전송시 패키징 규칙성에 대한 정의
	 -> Request Line : request URL(명사, 자원에 대한 식별자), request Method
	 	request Method(자원에 대한 행위를 표현하는 동사) : 요청의 목적(의도)과 패키징 구조에 대한 표현
	 	GET - R(클라이언트가 사용하는 기본 method) : 서버에 있는 자원 조회
	 	POST - C : 새로운 자원 등록
	 	PUT/PATCH - U : 기존의 자원의 수정 (put은 어떤 값을 수정하던지 모두 수정을 한다. - 덮어쓰기)
	 	DELETE - D : 기존 자원의 삭제
	 	OPTION : preFlight 요청에 사용됨
	 	HEAD : respomse body가 없는 line과 header로만 구성된 response받기 위한 요청
	 	TRACE : 
	 	
	 	RESTful URI 구조. -> 자원을 요청하고 받는 비동기 구조로 이루어져 있음(put과 delete는 비동기일때 가능)
	 	ex) /member/memberInsert.do ->명사와 동사가 석여있음 (x)
	 	ex) /member/memberDelete.do					    (x)
	 	
	 	ex) /member GET
	 	ex) /member POST
	 	ex) /member PUT
	 	ex) /member DELETE
	 -> Request Header : contents type, 클라이언트의 요청을 부가적으로 수식해주는 메타데이터의 영역.
	 		header name : header value
	 		Accept-*
	 		Accept : response content-type을 표현
	 		ex) text/html
	 			application/json
	 		Accept-Language : response language 표현
	 			ex) ko_KR,ko,en_US,en
	 		Accept_Encoding : response data compress 방식 표현
	 			ex) gzip
	 			
	 		Content-* : POST 요청으로 request body가 있음
	 		Content-Type : request body content mime type 설정
	 			ex) application/x-www-form-urlencoded
	 				multipart/form-date -> 여러 종류의 데이터를 가지고있을때 / input tag의 개수 만큼
	 		Content-length : body content length 표현
	 		
	 		User-Agent : 클라이언트가 사용하고 있는 시스템이나 브라우저 렌더링 엔진에 대한 표현
	 -> Request Body(Content body, Message Body) : contents 정보(없을수도있음 -> get(조회)에서는 body가 없음) 
	 										    post(보내기)에서는 body를 만들어야한다
		: POST 요청에서만 형성되는 영역으로 전송 컨텐츠의 영역
		1) request parameter : application/x-www-form-urlencoded MIME에 따라 
								전송 가능한 직렬화된 문장으로 데이터가 전송됨
							ex) name=value&name=value
							파라미터 형태의 전송데이터는 BODY가 없는 경우, 제한적으로 request line을 통해 전송되기도 함
							--> Query String의 형태로 전송
							GET : URL?name=value&name=value
		2) multipart data : multipart/form-data MIME에 따라 
				body영역이 여러개의 part로 분리되고, 각 part에 입력 데이터가 포함되어 전송됨
		3) object payload : application/json(많이 씀) MIME에 따라
					body 영역을 통해 json이나 xml페이로드를 전송하게 됨.
					


	
</pre>
</body>
</html>
<%
	}	
%>