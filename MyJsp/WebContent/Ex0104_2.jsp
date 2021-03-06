<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<%
	// 중첩된 반복구문 / *을 5행 5열로 출력해보자!
	for (int i = 0; i < 5; i++) {  // 변수를 내부에서 선언할거야
		for (int j = 0; j < 5; j++) {
			out.print("*");
		}
	out.print("<br>");
	}
	
	// 구구단을 만들어 보자! 주석 처리가 왜 안되지?			
	for (int i = 1; i <= 9; i++) {
		for (int j = 1; j <= 9; j++) {
			out.print(i + "*" + j + "=" + (i*j) + " ");
		}
		out.print("<br>");
	}

	
	// 테이블 문법을 사용해보자	
	out.print("<h5>테이블 문법을 사용해보자<h5>");
	out.print("<table border='1'>");
	out.print("<tr>구구단 : out.print출력문 사용</tr>");
	for (int i = 1; i <= 9; i++) {
		out.print("<tr>");
		for (int j = 1; j <= 9; j++) {
			//out.print("<td>"); / td를 출력문 하나에 포함관계로 넣어도 가능하고 나는 위, 아래로 감싸서 표현했다.
			out.print("<td>" + i + "*" + j + "=" + (i*j) + " " + "</td>"); // ①이 출력문과
			//out.print("</td>");
		}
		out.print("</tr>");
	}
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	
	%>
	<!-- out.print가 아닌 제어구문으로 테이블 구구단 출력해보기 -->
	구구단 : "<'%' '%'>" 제어구문 출력문 사용
	<table border="1">
	<% for (int i = 1; i <= 9; i++) { %>
		<tr>
		<% for (int j = 1; j <= 9; j++) { %>
			<td>
			<%=i%>*<%=j%>=<%=i*j%> <!-- i, j, ixj 총 세가지를 출력한다. ①이 출력문이 서로 같다. 이 출력문이 더 간결하다. --> 
			</td>
		<% } %>
		</tr>
	<% } %>
	</table>
	<%
	out.print("<font size='10'>");
	out.print("웹프로그래밍");
	out.print("</font><br>");
	%>
	<font size="10"><%= "웹프로그래밍" %></font><br>	
	
	- jsp는 디자인용도로 주로 사용된다. <br>
	- 내장모니터 확인
	
</body>
</html>