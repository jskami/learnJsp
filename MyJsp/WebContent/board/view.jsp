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
<%	
	// view.jsp는 
	request.setCharacterEncoding("utf-8"); // 한글 깨질지도 모르니까 그냥 무조건 써..
	// 넘기는걸 받을거야!
	String b_num = request.getParameter("b_num");  // Get방식에 의한 전송
%>
	<h3>상세보기</h1> 
	<a href="./list.jsp">글목록</a><br>
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
		query = "select * from tblboard where b_num = ?"; // ?를 사용해서 b_num에 대응하도록 설정
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, Integer.parseInt(b_num)); // 여기는 정수형을 줘야해 / 파싱 써서 형변환 ㄱㄱ! 
		//pstmt = conn.prepareStatement(query); // 불러온 쿼리를 쿼리결과 객체로 생성
		
		rs = pstmt.executeQuery();  // 쿼리 실행해서 결과를 rs에 반환받음
		rs.next();  // 첫 번째 레코드로 이동(레코드는 단 1개만 검색됨)
		
		//int ib_num = rs.getInt("b_num");  // 정수형으로 쓴다는걸 명시하기 위해 i를 붙인것 뿐이야
		String b_subject = rs.getString("b_subject");
		String b_name = rs.getString("b_name");
		String b_contents = rs.getString("b_contents");
		String b_date = rs.getString("b_date");
		// 매개변수 둘 다 스트링으로 줄거야, 치환해서 업뎃 + 새로 배운 함수 : .replace
		b_contents = b_contents.replace("\n", "<br>"); // 실제 글을 작성할 때 엔터를 치면 줄 바꿈이 적용된다!
		
		
		
%>		
			<table border="1">
			<tr>
				<td align="center" width="150">번호</td>
				<td align="center" width="300"><%=b_num %><br></td>
			</tr>
			<tr>
				<td align="center" width="150">제목</td>
				<td align="center" width="300"><%=b_subject %></td>
			</tr>
			<tr>	
				<td align="center" width="150">작성자</td>
				<td align="center" width="300"><%=b_name %><br></td>
			</tr>	
			<tr>	
				<td align="center" width="150">내용</td>
				<td align="center" width="300"><%=b_contents %><br></td>
			</tr>	
			<tr>	
				<td align="center" width="150">등록일</td> 
				<td align="center" width="300"><%=b_date %><br></td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<a href="./update.jsp?b_num=<%=b_num %>">[수정]</a>&nbsp;&nbsp;
					<a href="./delete.jsp?b_num=<%=b_num %>">[삭제]</a> 
				</td>
			</tr>
<%			

%>	
			</table>

		<%-- 번호 : <%=b_num%><br>
		제목 : <%=b_subject%><br>
		작성자 : <%=b_name%><br>
		내용 : <%=b_contents%><br>
		작성일 : <%=b_date%><br> --%>
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