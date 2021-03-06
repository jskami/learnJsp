<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%> <!-- 상단에서 import구문을 적어줘야 로드 가능 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>게시판 리스트</h1> 
	
	<a href="./write.jsp">글쓰기</a><br>
<%
	Connection conn = null; // 전역변수 추가(접속객체 선언) / connection객체는 내장되어 있는 기능이다. 생성과 동시에 null값 부여.
	PreparedStatement pstmt = null; // 쿼리 객체 선언
	ResultSet rs = null;  // 쿼리 결과(레코드 집합) 객체 선언
	String query = "";  // SQL 문법 선언, 초기화 상태
	try {
		Class.forName("com.mysql.jdbc.Driver"); // JDBC 드라이버를 로드하는 문장으로서 Driver 대문자 주의!
		//out.print("드라이버 로드 성공");
		String url = "jdbc:mysql://localhost:3306/mysql";  // 접속url, 맨 뒤에 mysql은 db이름이다.
		String user = "root";  // 원칙상 아이디와, 비밀번호는 반드시 설정 해줘야 하지만, 학습용이니까 기본 설정으로 가자!
		String passwd = "";
		conn = DriverManager.getConnection(url, user, passwd);
		//out.print(conn);
		query = "select * from tblboard";
		pstmt = conn.prepareStatement(query); // 불러온 쿼리를 쿼리결과 객체로 생성
		rs = pstmt.executeQuery();  // 쿼리 실행해서 결과를 rs에 반환받음
		//rs 객체는 여러개의 레코드가 포함되어 있을 수 있음(반복구문 필요)
%>			
			<table border="1">
			<tr>
				<td align="center" width="100">글 번호</td>
				<td width="250">제목</td>
				<td align="center" width="130">작성자</td>
				<td align="center" width="150">내용</td>
				<td align="center" width="150">등록일</td>
			</tr>
<%			
		while (rs.next()) { //next가 참일 경우 레코드가 반환된다는 의미
			int b_num = rs.getInt("b_num"); // 테이블에 설계한 이름과 같아야 한다.
			String b_subject = rs.getString("b_subject");
			String b_name = rs.getString("b_name");
			String b_contents = rs.getString("b_contents");
			Date b_date = rs.getDate("b_date");  // sql 타입과 동일하게 설정해야 한다. 자바에도 Date 타입이 존재한다.
			//out.print(b_num + "===" + b_subject + "===" + b_name + "===" + b_contents + "===" + b_date + "<br>");
%>		
			<tr> 
				<td align="center"><%=b_num %></td>
				<td><a href="./view.jsp?b_num=<%=b_num %>"><%=b_subject %></a></td> <!-- 링크로 감싸도록 할거야, -->
				<!-- 원하는 글(유일한 글)만 볼 수 있도록 설정할거야 -->
				<td align="center"><%=b_name %></td>
				<td align="center"><%=b_contents %></td>
				<td align="center"><%=b_date %></td>
			</tr>
<%
		}
%>	
			</table>
<%
	} catch (Exception e) { // 객체를 생성한 뒤에는 객체를 소멸해줘야 메모리 공간이 여유로워진다.
		out.print(e);
	} finally {
		try {
			if (rs != null)  // 제일 나중에 열린 객체부터 순서대로 닫아준다. rs가 마지막에 열렸으니 여기서는 제일 먼저 닫아준다.
				rs.close();  // close, 종료!
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
			out.print(ex);   // 파이널부터 여기까지가 객체 종료 구문이다.
		}
	}
%>
</body>
</html>

















