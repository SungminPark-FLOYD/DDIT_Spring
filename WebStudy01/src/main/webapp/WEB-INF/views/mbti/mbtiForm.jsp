<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MBTI</title>
    <%
			String mbti = (String)request.getAttribute("mbti");
			if(StringUtils.isNoneBlank(mbti)){
		%>
				<script>
					document.addEventListener("DOMContentLoaded", () => {
						document.forms[0].type.value = "<%=mbti%>";
						document.forms[0].requestSubmit();
					})
				</script>
		<%
			}
		%>
</head>
<body>
    <form id="mbti-form" method="post" enctype="application/x-www-form-urlencoded"><!-- 메소드 생략하면 Get방식, Get방식은 enctype필요없음 -->
        <select name="type" onchange="this.form.requestSubmit()"> <!-- onchange뒤는 익명함수임. submit만 쓰면 이벤트 핸들러 동작X -->
			<% 
				Map<String,String> mbtiMap = (Map) application.getAttribute("mbtiMap");
				for(Entry<String,String> entry : mbtiMap.entrySet()){ //set의 엘리먼트 타입은 Entry 키/벨류타입은 Sting,String
			%>
            <option value="<%=entry.getKey()%>"><%=entry.getValue() %></option>
			<%
				}
			%>
        </select>
    </form>
    <div id="mbti-area">
    
    </div>
<script src="<%=request.getContextPath() %>/resources/js/mbti/mbtiForm-answer.js"></script>
</body>
</html>