<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
document.addEventListener("DOMContentLoaded", () => {
	calform.addEventListener("submit", e => {
		e.preventDefault();
		
		let url = calform.action;
		let method = "post";
		let headers = {
			"content-type" : "application/x-www-form-urlencoded",
			"accept" : "text/html"
		};
		
		let formData = new FormData(calform);
		console.log(new URLSearchParams(formData).toString());
		let body = new URLSearchParams(formData).toString();
		let options = {
				method : method,
				headers : headers,
				body : body
			};
		 fetch(url, options)
		.then(resp=>{
			if(resp.ok) {
				
				return resp.text();
			}else{
				throw new Error(`요청 처리 실패, 상태코드 : ${resp.status}`);
			}
		})
		.then(html => {
			resultArea.innerHTML = html;
		})
		.catch(err => console.log(err)); 
		
	})
})
</script>
</head>
<body>
<!-- 비동기 처리 기반의 사칙연산기. -->
<form id="calform" action="<%=request.getContextPath() %>/calculate.do">
	<input type="number" name="leftOp" />
	<select name="operator">
		<option value>연산자</option>
		<option value="PLUS">+</option>
		<option value="MINUS">-</option>
		<option value="MULTIPLY">*</option>
		<option value="DIVIDE">/</option>
	</select>
	<input type="number" name="rightOp" />
	<input type="submit" value="="/>
</form>
<div id="resultArea">
	ex) 2 * 2 = 4
</div>

</body>
</html>