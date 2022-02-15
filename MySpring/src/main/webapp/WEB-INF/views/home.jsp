<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<p><a href="/member/login">로그인</a></p>
<p><a href="/member/logout">로그아웃</a></p>
<p><a href="/member/list">사용자 리스트</a></p>
<p><a href="/board/list">게시판 리스트</a></p>
<p><a href="/product/list">상품 리스트</a></p>
<p><a href="/shop/list">장바구니 리스트</a></p>

<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<p> 사용자 ID : ${m_id}</p>
<p> 사용자 NAME : ${m_name}</p>

</body>
</html>
