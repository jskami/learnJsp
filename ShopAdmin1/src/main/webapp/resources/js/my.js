function writeform() { // 단순한 메서드 이름일 뿐이다.
		var thisform = document.writefrm // 변수 선언!, 다큐먼트는 키워드가 맞고 웹브라우저를 의미한다. / 현재 폼을 저장한 것이다. 이제 어떻게 전송 할 것인가가 필요하다.
		thisform.method = "post"; // 속성
		thisform.action = "/board/insert"; // 속성
		var id_subject = document.getElementById('id_subject').value; // 변수 선언!, 텍스트상자의 값이다! / 한 문서 내에서는 절대로 id를 중복시켜서는 안되고, 대소문자를 구분할 것!
		if (id_subject == '') { //특정 문자열 또는 공백 등 단순 비교를 해보자!
			alert('제목을 입력해 합니다.')						
		} else {
			thisform.submit();
		}
		//alert(id_subject); // 입력 확인을 위한 디버깅용
		//thisform.submit(); //폼 전송! 이녀석은 메서드이고 위에 둘을 속성이다.
	};
	
	//분기?하기 위해서는 ...?
	function writeform2() { // 단순한 메서드 이름일 뿐이다.
		var thisform = document.writefrm // 변수 선언!, 다큐먼트는 키워드가 맞고 웹브라우저를 의미한다. / 현재 폼을 저장한 것이다. 이제 어떻게 전송 할 것인가가 필요하다.
		thisform.method = "post"; // 속성
		thisform.action = "/board/insert"; // 속성
		var id_subject = document.getElementById('id_subject').value; // 변수 선언!, 텍스트상자의 값이다! / 한 문서 내에서는 절대로 id를 중복시켜서는 안되고, 대소문자를 구분할 것!
		if (id_subject == '') { //특정 문자열 또는 공백 등 단순 비교를 해보자!
			alert('제목을 입력해 합니다.2')						
		} else {
			thisform.submit();
		}
		//alert(id_subject); // 입력 확인을 위한 디버깅용
		//thisform.submit(); //폼 전송! 이녀석은 메서드이고 위에 둘을 속성이다.
	};
	
	// 자스는 주로 메시지를 호출할 때 사용한다.
	// id를 이용하는 것이 주로 편리하다. id는 계층구조의 영향을 받지 않기 때문에 어디든 갈 수 있다. 그래서 id 사용을 선호한다. 단, 한 페이지에 중복되지 않도록 한다.
	
	/* 22.03.18=========================
	입력할 때 자바스크립트 활용? ... 을 마지막으로 알려주신다고 하니 잘 배워두자.

	라이트폼이라는 함수에 대해 스크립트 정의이다.

	세미콜론을 생략해도 좋지만 여러개를 호출할 수 있을 경우 발생할 에러에 대해 미리 방지하고자 가급적 세미콜론을 써두자!

	스크립트부터 onclick까지 문서의 내용을 객체화(문서객체) 한것이기 때문에(?) form에 name을 정해주자.

	유일한 값을 보장한다면 여기(header.jsp)파일에 넣어도 되고 그게 아니라면 방금 전에 스크립트 작성한 insert.jsp에 넣어도 된다.

	오! 스크립트에 정의해놓으니까 폼태그에 액션과 메서드가 비어있어도 전송이 된다!

	아직 안만들었어 착각 ㄴㄴ! */
	