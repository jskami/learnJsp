package com.it.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTest {
	
	//@Test
	public void testConn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?serverTimezone=Asia/Seoul",
					"root",
					"");
			log.info(conn); // 스프링에서는 출력문 잘 안쓴다. log.info를 자주 쓴다.
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
// 커넥션 객체가 잘 생성 되었는지 테스트 해본거야. 출력문에 해시코드가 나오면 잘 된거야! 이제 DB연동은 된거야-
