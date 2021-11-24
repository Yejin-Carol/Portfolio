<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.Calendar" %>
<%
	request.setCharacterEncoding( "utf-8" );
	String strYear = request.getParameter( "Year" );
	String strMonth = request.getParameter( "Month" );
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	const calendar = function() {
		let year = document.frm.Year.value.trim();
		let month = document.frm.Month.value.trim();
		
		document.frm.submit();
	}
</script>

</head>
<body>

<form action="calendar.jsp" name="frm" method="post">
&nbsp&nbsp연도  &nbsp&nbsp&nbsp&nbsp&nbsp월 <br>

<select name="Year">
	<option value="2020">2020</option>
	<option value="2021">2021</option>
	<option value="2022">2022</option>
</select>


<select name="Month">
	<option value="1">1월</option>
	<option value="2">2월</option>
	<option value="3">3월</option>
	<option value="4">4월</option>
	<option value="5">5월</option>
	<option value="6">6월</option>
	<option value="7">7월</option>
	<option value="8">8월</option>
	<option value="9">9월</option>
	<option value="10">10월</option>
	<option value="11">11월</option>
	<option value="12">12월</option>
</select>

<input type="button" value="달력출력" onclick="calendar()">

</form>
<%
	if ( strYear != null && strMonth != null ) {
		int year = Integer.parseInt( strYear );
		int month = Integer.parseInt( strMonth );

		int START_DAY_OF_WEEK = 0;
		int END_DAY_OF_WEEK = 0;
		int END_DAY = 0;
		
		Calendar sDay = Calendar.getInstance();
		Calendar eDay = Calendar.getInstance();
		
		sDay.set(year, month-1, 1 );
		eDay.set(year, month, 1-1 );
		
		START_DAY_OF_WEEK = sDay.get( Calendar.DAY_OF_WEEK );
		END_DAY_OF_WEEK  = eDay.get( Calendar.DAY_OF_WEEK );
		END_DAY = eDay.get( Calendar.DATE );
		
		out.println( "<table width='800' border='1'>" );
		out.println( "<tr>" );
		out.println( "<td colspan='7'>       "  + year + "년 " + month + "월" + "</td>");
		out.println( "</tr>" );
		
		out.println( "<tr>" );
		out.println( "<td>SU</td><td>MO</td><td>TU</td><td>WE</td><td>TH</td><td>FR</td><td>SA</td>");
		out.println( "</tr>" );

		for ( int i = 1; i<START_DAY_OF_WEEK; i++ ) {
			out.print("<td></td>");
		}
		
		for( int i=1, n=START_DAY_OF_WEEK ; i<=END_DAY ; i++, n++ ) {
			if( n % 7 == 1 ) out.println( "<tr>");
			out.print( "<td>" + i + "</td>" );
			if( n % 7 == 0 ) out.println("</tr>");
		}
	        
		for( int i=END_DAY_OF_WEEK ; i<=6 ; i++ ) {
			out.println( "<td></td>" );
		}
		out.println( "</table>" );
	}
%>


</body>
</html>