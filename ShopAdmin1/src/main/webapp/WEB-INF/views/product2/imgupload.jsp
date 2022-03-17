<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<head>
		<script>
			function winclose() {
				opener.location.reload(); // 부모창 갱신
				window.close();
			}
		</script>
	</head>
	
	<body>
		<h3>상품 이미지 등록</h3>
		<form method="post" action="/product2/imgupload" enctype="multipart/form-data"> <!-- enctype="multipart/form-data"은 스트링 뿐만 아니라 다른 모든 데이터들도 전송시켜주는 내장 키워드라고 생각하자 -->
			상품코드 : <input type="text" name="p_code" value="${p_code}" readonly>
			<input type="file" name="p_imag"><br>
			<input type="submit" value="저장하기">
		</form>
		<a href="javascript:winclose()">닫기</a>
	</body>
</html>