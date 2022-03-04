<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Mytest</title>
</head>
<body>


<h1>
	Hello world!
</h1>
<br>
<h3>1. 변수 처리(임의의 변수를 생성해보자)</h3>
<%-- <c:set var="mynum" value="100" /> --%>
<p>"<'c'>"c는 코어를 말한다. c:set으로 변수를 만들고 값을 정한다.
<h3>${mynum}</h3>
<p>-를 다음과 같이 동일하게 나타낼 수도 있다.
<h3><c:out value="${mynum}"/></h3>
<p>출력과 관계된 JSTL문법은 c:out이다.
<br>
<h3>2. 제어구조 (mynum을 이용해 홀,짝 연산을 해보자)</h3>
<p> c:choose문법 내에서 c:when으로 조건을 설정하고 값 출력, 그게 아니라면 c:otherwise로 반대 조건 값이 나온다.
<c:choose>
	<c:when test="${mynum % 2 == 0}">
		<h3>${mynum}은 짝수</h3>
	</c:when>
	<c:otherwise>
		<h3>${mynum}은 홀수</h3>
	</c:otherwise>
</c:choose>
<h3>3. 구구단</h3>
<p> c:foreach를 통해 반복구문 실행! 구구단은 2차원 제어구조 성격을 갖고 있으니까 foreach 안에 foreach를 하나 더 만들어야 한다.
<p> 그리고 각 foreach에 var속성을 이용해 변수명을 짓고 begin과 end로 시작값과 끝값을 입력한다.
<p> 내부 foreach에서 연산하도록 설정한다.&nbsp;	&nbsp;	
<br>
<c:forEach var="i" begin="1" end="9">
	<c:forEach var="j" begin="1" end="9">
		${i}*${j}=${i*j}&nbsp;	
	</c:forEach>	
	<br>
</c:forEach>






















</body>
</html>
