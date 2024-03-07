<%@page import="kr.or.ddit.vo.PersonVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- 웹에서 기본 컨텐츠 타입으로 사용되는 HTML 컨텐츠를 생성하기 위한 view layer --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        .modal {
        	width : 30%;
            display: none;
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
<form name="personForm" action="<%=request.getContextPath() %>/people.do" method="post">
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
		<%
			List<PersonVo> people = (List)request.getAttribute("people");
			for(PersonVo once : people) {
				%>
				<tr>
					<td><%= once.getId() %></td>
					<td><a href="javascript:;" onclick="clickHandler(event)" data-member-id="<%= once.getId() %>"><%= once.getName() %></a></td>
				</tr>
				<%
			}
		%>
	</tbody>
</table>

<div id="myModal" class="modal">
        <div id="modal-content">
	        <table>
			    <tr>
			        <th>NAME</th>
			        <td id="name"></td>
			    </tr>
			    <tr>
			        <th>GEDER</th>
			        <td id ="gender"></td>
			    </tr>
			    <tr>
			        <th>AGE</th>
			        <td id="age"></td>
			    </tr>
			    <tr>
			        <th>ADDRESS</th>
			        <td id="addr"></td>
			    </tr>
			    <tr>
			    <td colsapn="2"><button id="btn">close</button></td>
			    </tr>
			</table>     
        </div>
</div>

<script type="text/javascript">
	function clickHandler(e) {
		e.preventDefault();
		/* let aTag = e.target;
		document.personForm.who.value = aTag.dataset.memberId;
		//submit이벤트 발생시키기
		document.personForm.requestSubmit(); */
		
        var memberId = event.target.getAttribute("data-member-id");

        showModal(memberId);
		
	}
	
	let modal = document.getElementById('myModal');
	let button = document.getElementById('btn');

	button.addEventListener('click', function() {	
	    modal.style.display = "none";
	    
	});
	

	function showModal(memberId) {
		var xhr = new XMLHttpRequest();
		xhr.open("POST", "<%=request.getContextPath()%>/people.do", true);

		var formData = new FormData();
		formData.append("who", memberId); 

		xhr.onreadystatechange = function() {
		    if (xhr.readyState == 4) {
		        if (xhr.status == 200) {
		            var data = JSON.parse(xhr.responseText);
		            document.getElementById("name").innerText = data.name;
		            document.getElementById("gender").innerText = data.gender;
		            document.getElementById("age").innerText = data.age;
		            document.getElementById("addr").innerText = data.address;
		            
		            var modal = document.getElementById('myModal');
		            modal.style.display = "block";
		            
		        } else {
		            // 오류 처리
		            console.error("오류 발생: " + xhr.status);
		        }
		    }
		};

		// FormData를 전송
		xhr.send(formData);
	}
</script>
</body>
</html>