<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
leftOp, rightOp, Operator

<form action="/mission/case08_9" method="post" enctype="application/x-www-form-urlencoded">
	<input type="number" name="leftOp"/>
	<select name="operator">
		<option value="PLUS">+</option>
		<option value="MINUS">-</option>
		<option value="MULTIPLY">*</option>
		<option value="DIVIDE">/</option>
	</select>
	<input type="number" name="rightOp"/>
	<button type="submit">=</button>
</form>

<script type="text/javascript">
	document.forms[0].addEventListener("submit", function (e) {
		e.preventDefault();
		let leftOp = this.leftOp.value;
		let rightOp = this.rightOp.value;
		let operator = this.operator.value;
		
		let data = obj [{
				"leftOp" : leftOp,
				"rightOp" : rightOp,
				"operator" : operator
		}]
		
		fetch("${pageContext.request.contextPath}/mission/case08_9",{
			method : "POST",
			headers : {
				"content-type" : "application/json",
				"accept" : "application/json"
			},
			body : JSON.stringify(data)
		}).then(resp => resp.json()
		).then(result => {
			console.log(result);
		})
	})
</script>
</body>
</html>