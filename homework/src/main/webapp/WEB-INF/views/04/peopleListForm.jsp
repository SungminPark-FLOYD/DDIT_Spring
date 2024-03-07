<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<ul>
        ${lis}   
    </ul>
    
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/app/04/peopleListForm.js"></script>
</body>
</html>