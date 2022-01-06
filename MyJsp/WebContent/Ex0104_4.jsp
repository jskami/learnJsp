<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- Ex0104_3에서 넘어온 정보를 전송받아서 처리하자! -->
	<%
		// 이름을 리턴받아-
		request.setCharacterEncoding("utf-8"); // 인코딩 방식 설정, 가장 위에서 작성해야 아래의 모든 코딩에도 영향을 미치니까.. rg?
		String sname = request.getParameter("sname"); //리퀘스트라는 객체가 따로 존재한다. 클라이언트로부터 넘어온 정보를 받는 객체로서, 이녀석을 사용하자
			// ↑여기 이름은 뭘 해도 상관없지만 알아보기 쉽게 sname이라고 동일하게 하자 / 위 문법은 '전송문자열 저장'이다.
		//out.print(sname + "<br>");
		
		// 나이를 리턴받아-
		String sage = request.getParameter("sage"); 
		//out.print(sage + "<br>");
		
		// 비밀번호를 리턴받아-
		String passwd = request.getParameter("passwd");
		//out.print(password + "<br>");
		
		// 지역을 리턴받아-
		String sarea = request.getParameter("sarea");
		//out.print(sarea + "<br>");
		
		// 연도를 리턴받아-
		String syear = request.getParameter("syear");
		//out.print(syear + "<br>");
		
		// 취미를 리턴받아-
		String shobby = request.getParameter("shobby");
		//out.print(shobby + "<br>");
		
		
		// sage를 정수형으로 변환시켜보자!  / + '예외처리'를 해보자!
		int iage = 0;  // 전역변수
		
		// 이름 입력 오류를 띄워보자
		boolean flag = true;  // 나이가 정상으로 넘어온다고 가정
		if (sname.trim().length() < 2) { // "   홍길  동     " 이런게 있다면 앞, 뒤에 공백을 제가한 순수 문자만 출력 !:ㄱ,ㄴ이런게 아니라 가,나,박,이,최 등등 조합된 한 글자를 말한다. -> 이게 trim메서드이다. 
			flag = false;		
		}
			//"====================여기까지 이름 입력 오류======================");
		int ipassword = 0;
		try {
			ipassword = Integer.parseInt(passwd);
		} catch (Exception e) {
			flag = false;
		}
			//"====================여기까지 비번 입력 오류======================");
		try {
			iage = Integer.parseInt(sage); // 정수형태의 문자열을 정수로 형변환 하는 방법! 진짜 중요 integer캐스팅 숙지할 것
		} catch (Exception e) { // 예외가 발생했을 경우 처리할 내용
			//iage = 25;  // 예외가 발생 안하면 37라인이 정상 작동, 예외 발생하면 39라인이 작동 rg? 디폴트 값으로 25를 준것-
			flag = false;  // 예외가 발생된 상황
		}
			//"====================여기까지 나이 입력 오류======================");
			
			//"====================여기부터 오류 메시지 띄우기======================");
		if (flag == false) {  // 예외가 발생했을 경우
%> <!-- 이것은 스크립트 문법을 분리 시키기 위함이다.  -->
			<script>
				alert("입력 오류 발생");
				history.back();  // 이전 페이지로 이동
			</script>
<%	
		} else {  // 정상일 경우	
			//out.print(iage + "<br>");
			out.print(sname + "<br>");
			out.print(sage + "<br>");
			out.print(passwd + "<br>");
			out.print(sarea + "<br>");
			out.print(syear + "<br>");
			out.print(shobby + "<br>");
		}
		// ↑ 이 처럼 사용자가 나이 입력란에 문자를 입력할 수 있는 예외가 발생할 수 있으니까 개발자는 다양한 변수를 생각하고 예외를 처리할 수 있도록 꼼꼼하게, 신중하게 코딩해야 할 것이다.
	%>
</body>
</html>