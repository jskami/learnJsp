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
		<!-- update_end와 비슷하니까 복붙 후 수정 -->
<%
	request.setCharacterEncoding("utf-8"); // 한글처리를 해줘야 해! 이 녀석은 데이터를 넘기고 받을때 처리 / 상단에 1~2라인은 이 파일 자체를 처리
	String b_num = request.getParameter("b_num"); // 넘버만 넘어오면 돼
%>
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
		query = "delete from tblboard where b_num = ?"; // 삭제 / 조건은 b_num을 바탕으로 넘겨받은 정보들을 삭제하는거야.
		pstmt = conn.prepareStatement(query); // 불러온 쿼리를 쿼리결과 객체로 생성
		pstmt.setInt(1, Integer.parseInt(b_num)); // b_num을 인티저로 형 변환 
		// ! 만약 ID값을 받아와야 한다면 String으로 받아올 수 없으니까 당연히 형변환을 해주고 받아와야겠지? ok /
		pstmt.executeUpdate();  // insert, update, delete의 경우 사용 /
%>		
		<!-- 리스트 자체 이동과 jscript를 이용한 이동 방법중 선택 --> 
		<script>
			alert("삭제되었습니다."); 
			location.href = "./list.jsp"; // 삭제되면 뷰는 없을테니까 리스트로 옮기자	
			// 수정된 내용을 확인 할 수 있도록 뷰로 가게 하는데, 뷰는 항상 번호가 필요하니까 get방식으로 '?'를 돌려줘야 한다.
		</script>
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