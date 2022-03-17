package com.it.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTest {
	
	@Test
	public void testConn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?serverTimezone=Asia/Seoul",
					"root",
					"");
			log.info(conn);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}

// 커넥션 객체가 잘 생성되었는지 테스트, 출력문에 해시코드 나오면 성공. DB연동 완료