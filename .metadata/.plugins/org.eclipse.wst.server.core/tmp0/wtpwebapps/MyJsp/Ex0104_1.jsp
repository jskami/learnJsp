<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%      // 이 영역은 자바문법의 영역이다. JSP태그이다.
	out.print("안녕하세요!" + "<br>"); // 덧셈 연산자를 활용한 줄바꿈
	out.print("웹프로그래밍입니다.<br>"); // 줄바꿈 바로 적용
	out.print("&lt;br&gt;"); // 줄바꿈 자체를 표현하는 방법
	out.print("<h1>JSP프로그래밍</h1>"); // 글자 크기를 조절할 수 있는 패딩문자열!
	
	int i; // 정수형 변수 선언
	int total = 0; // 초기화
	for (i = 1; i <= 10; i++) {
		total += i;
	}
	out.print("1~10까지의 합은" + total + "<br>");
%>
</body>
</html>