<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>글입력</h3>
	<a href="./list.jsp">리스트</a><br>
	<form method="post" action="./write_end.jsp"> <!-- 상대경로로 보내주자,(아직 안 만든 상태 rg?) 이제부터 자료를 저장하고 제출하는 코드를 만들거야! -->
		제목 : <input type="text" name="b_subject"><br>
		작성자 : <input type="text" name="b_name"><br>
		내용 : <textarea cols="50" rows="10" name="b_contents"></textarea><br>
		<!-- 작성일은 자동으로 생성되도록 설정이 되어 있대 + 위 세가지 항목을 모두 전송할 수 있는 버튼을 만들자 -->
		<input type="submit" value="글쓰기">
		
	</form>
</body>
</html>