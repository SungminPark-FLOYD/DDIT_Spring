<%@page import="java.time.format.TextStyle"%>
<%@page import="java.time.Month"%>
<%@page import="java.time.format.FormatStyle"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.YearMonth"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.ZoneId"%>
<%@page import="java.time.ZonedDateTime"%>
<%@ page import="java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String yearP = request.getParameter("year");
	String monthP = request.getParameter("month");
	String localeP= request.getParameter("locale");
	String zoneP= request.getParameter("zone");
	
	
	//펙토리 메서드는 대부분의 인스턴스를 상수로 관리한다.
	//parsing : 문자열을 특정 타입의 데이터로 변환하는 작업.
	//formatting : 특정 타입의 데이터를 일정 형식의 문자열로 변환하는 작업
	//request.getLocale() : header의 acceptLanguage에의해서 결졍됨
	Locale locale = request.getLocale();
	if(localeP!= null) {
		locale = Locale.forLanguageTag(localeP);
	}
	//서버기준 지역 아이디 구하기
	ZoneId zone = ZoneId.systemDefault();
	//zoneid기준 현재 시각 구하기
	ZonedDateTime now = ZonedDateTime.now(zone);
	//현재 시각 기준 현재 날짜 구하기
	LocalDate today = LocalDate.from(now);
	//현재 시각 기준 현재 연/월 구하기
	YearMonth thisMonth = YearMonth.from(now); 	
	
	if((yearP != null && !yearP.isEmpty()) || (monthP != null && !monthP.isEmpty())) {
		try{
			thisMonth = YearMonth.of(Integer.parseInt(yearP), Integer.parseInt(monthP));
		}catch(NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
			return;
		}
	}
	
	//시간 연산
	YearMonth prevMonth = thisMonth.minusMonths(1);
	YearMonth nextMonth = thisMonth.plusMonths(1);
	
	 FormatStyle FULL = FormatStyle.FULL;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	h4.control{
		color: blue;
		text-decoration: underline;
		cursor: pointer;
	}
</style>
<script type="text/javascript">
	document.addEventListener("DOMContentLoaded", ()=> {
		calForm.year.value = <%=thisMonth.getYear() %>;
		calForm.month.value = <%=thisMonth.getMonthValue() %>;
		calForm.locale.value = '<%=locale.toLanguageTag() %>';
<%-- 		calForm.zone.value = <%=thisMonth.getMonthValue() %>; --%>

		calForm.addEventListener("change", (e)=> {
			e.target.form.requestSubmit();
		});
	});
	
	document.addEventListener("click", e =>{
		if(!e.target.classList.contains("control")) return false;
		let control = e.target;
	 	let year = control.dataset.year;
	 	let month = control.dataset.month;
	 	
	 	calForm.year.value = year;
	 	calForm.month.value = month;
	 	calForm.requestSubmit();
	});
</script>
</head>
<body>
<h4>now : <%= now.format(DateTimeFormatter.ofLocalizedDateTime(FULL).withLocale(locale)) %></h4>
<h4>today : <%= today.format(DateTimeFormatter.ofLocalizedDate(FULL).withLocale(locale)) %></h4>

<div>
<h4 class="control" data-year="<%=prevMonth.getYear() %>" data-month="<%=prevMonth.getMonthValue() %>"><%=prevMonth %></h4>
<h4>thisMonth : <%= thisMonth.format(DateTimeFormatter.ofPattern("yyyy, MMMM", locale)) %></h4>
<h4 class="control" data-year="<%=nextMonth.getYear() %>" data-month="<%=nextMonth.getMonthValue() %>"><%=nextMonth %></h4>
</div>
<form id="calForm">
	<input type="number" name="year"/>
	<select name="month" >
		<% 
			for(Month single :Month.values()){
		%>
			<option value="<%= single.getValue() %>">
				<%= single.getDisplayName(TextStyle.FULL, locale)%>
			</option>
		<%
			}
		%> 
	</select>
	<input type="text" name="locale" />
	<input type="text" name="zone" />
</form>

</body>
</html>