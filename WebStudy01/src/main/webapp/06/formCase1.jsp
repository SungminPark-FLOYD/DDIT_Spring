<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="<%= request.getContextPath() %>/06/case1Req.do" method="get">
	<pre>
		<a href="<%= request.getContextPath() %>/06/case1Req.do?p5=34">요청1</a>	
		<input type="text" name="p1" placeholder="p1"/>
		<input type="number" name="p2" placeholder="p2"/>
		<input type="text" name="p2" placeholder="p2"/>
		<input type="date" name="p3" placeholder="p3"/>
		<input type="datetime-local" name="p4" placeholder="p4"/>
		<input type="text" id="p6" placeholder="p6"/>
		<input type="radio" id="p7_y" name="p7" value="TRUE" checked/> true
		<input type="radio" id="p7_f" name="p7" value="FALSE"/> false
		<!-- 라벨을 통해 이벤트를 파생시킬 수 있다 -->
		<label><input type="checkbox" name="p8" id="p8_1" value="CH1"/> ch1</label>
		<input type="checkbox" name="p8" id="p8_2" value="CH2"/><label for="p8_2">ch2</label>		
		<input type="checkbox" name="p8" id="p8_3" value="CH3"/> ch3
		<select name="p9">
			<!-- 아무것도 선택하지 않았을때 기본적으로 선택되는 옵션을 프롬프트 텍스트이라고 한다. -> 
			value가 없어도 text값이 넘어간다 -> 비어있는 value를 할당해서 값이 넘어가지 않게 한다-->
			<option value>선택</option>
			<option value="value1">text1</option>
			<option value="value2">text2</option>
		</select>
		<select name="p10" multiple>
			<!-- 
				multiple속성이 있으면 선택하지 않았을때 파라미터가 넘어가지 않는다 
				multiple속성이 있으면 프롬프트 텍스트를 주지 않는다
			-->
			<option value="value1">text1</option>
			<option value="value2">text2</option>
		</select>
		<button type="submit">전송</button>
		<button type="reset">취소</button>
	</pre>
</form>

<!-- 비동기 요청 -->
<script src="<%=request.getContextPath() %>/resources/js/app/06/formCase1.js">
</script>
</body>
</html>