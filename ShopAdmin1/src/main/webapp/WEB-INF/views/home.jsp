<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ page session="false" %> --%>
<%@include file="./include/home_header.jsp" %> 
<!--
<br>
	<b><h1>현재 페이지는 관리자 전용입니다.</h1></b>
		<hr>
	<c:if test="${a_id != null }">
		<h3>환영합니다. ${a_id} 님</h3>
		<p><a href="/admin/logout">로그아웃</a></p>
	</c:if>
	<c:if test="${a_id == null }">
		<h3>관리자 로그인을 해주세요.</h3>
		<p><a href="/admin/login">로그인</a></p>
	</c:if>
<br>
<br>
 -->
<%@include file="./include/home_footer.jsp" %>