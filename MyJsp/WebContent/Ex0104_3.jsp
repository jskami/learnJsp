<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 전송 가능한 form을 만들어보자! -->
	<form method="post" action="./Ex0104_4.jsp">
		성명 : <input type="text" name="sname" placeholder="성명을 입력하세요.">
		<br>
		나이 : <input type="text" name="sage" placeholder="나이를 입력하세요.">
		<br>
		비밀번호 : <input type="password" name="Passwd" placeholder="비밀번호를 입력하세요.">
		<br>
		지역 : <select name="sarea">
				<option value="충북">충북</option> <!-- 실제로 전송되는 내용 -->
				<option value="경기">경기</option> <!-- 실제로 전송되는 내용 -->
				<option value="서울">서울</option> <!-- 실제로 전송되는 내용, 이중에 하나만 선택해야한다. -->
			  </select><br>
			  
			  <!-- 미션 : 2000년부터 2030년까지 syear 변수로 전송할 수 있는 select 태그 구현해봐! -->
		연도	: <select name="syear">
			  <% // 내가 한 방식 : for문은 자바 문법이고 out.- 출력문은 jsp문법이라 <% %'>(주석을 위해'표기) 식으로 구분해야 할 필요가 있어 보인다.
			  		for (int i = 2000; i <= 2030; i++) {
			  			out.print("<option value='"+ i +"'>"+ i +"</option>");
			  		}
			   %>	
			   
			   <!-- 선생님의 방식 
			   		<% for (int i = 2000; i <= 2030; i++) { %>
			   			<option value = "<%=i%>"><%=i%></option>
			    	  <% } %>
			   -->
			  </select><br>
			  <!-- 미션 끝 -->
			  
		취미 : <input type="radio" name="shobby" value="등산">등산&nbsp;&nbsp;&nbsp;
			  <input type="radio" name="shobby" value="낚시" checked>낚시&nbsp;&nbsp;&nbsp;
			  <input type="radio" name="shobby" value="축구">축구&nbsp;&nbsp;&nbsp;<br>
			  <!-- 값이 다르지만 네임이 같다는 점을 주의해서 보자! 최초에 아무것도 체크 안된상태에서 값을 넘겨버릴 수도 있으니까 하나는 체크된 상태로 보여주기 위해 checked를 사용했다. -->
			 
			  <input type="submit" value="전송하기">
	</form>
</body>
</html>