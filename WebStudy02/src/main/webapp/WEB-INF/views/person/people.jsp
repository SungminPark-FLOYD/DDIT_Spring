<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<%-- 웹에서 기본 컨텐츠 타입으로 사용되는 HTML 컨텐츠를 생성하기 위한 view layer --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        #detail {
        	width : 30%;
        	display : block;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            z-index: 1000; 
        }
</style>
</head>
<body>
<c:set value="${pageContext.request.contextPath }" var="cPath" scope="application"/>

<form name="personForm" action="${cPath }/people.do" method="post">
	<input type="text" name="who"/>
</form>
<table>
	<thead>
		<tr>
			<th>아이디</th>
			<th>이름</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${people }" var="once">
			<tr>
				<td>${once['id'] }</td>
				<td><a href="javascript:;" onclick="clickHandler(event)" data-member-id="${once['id']}">${once['name']}</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div id="detail"></div>


<script type="text/javascript">
	function clickHandler(e) {
		e.preventDefault();
		/* let aTag = e.target;
		document.personForm.who.value = aTag.dataset.memberId;
		//submit이벤트 발생시키기
		document.personForm.requestSubmit(); */
		
        var memberId = event.target.getAttribute("data-member-id");
		fetch("<%=request.getContextPath()%>/people.do", {
			  method: 'POST', // 또는 다른 HTTP 메서드를 선택할 수 있습니다.
			  headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			  body: new URLSearchParams({
				    'who': memberId,
			  }).toString(),
			})
			  .then(response => {
			    // 서버 응답을 처리합니다.
			    if (!response.ok) {
			      throw new Error(`HTTP error! Status: ${response.status}`);
			    }
			    return response.text(); 
			  })
			  .then(data => {
				 console.log(data);
			    document.querySelector('#detail').innerHTML = data;
			  })
			  .catch(error => {
			    // 오류 처리
			    console.error('오류 발생:', error.message);
			  });	
	}
		
	
</script>
</body>
</html>