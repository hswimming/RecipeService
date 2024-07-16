package com.shinhan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
	
	// 새로 추가
	public static Connection dbConnection() {
		Context initContext;
		Connection conn = null;
		
		try {
			initContext = new InitialContext(); // initContext를 이용해서 초기화
			Context envContext  = (Context)initContext.lookup("java:/comp/env"); // 정해져 있음
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle"); // web.xml에 등록했던 <res-ref-name>
			
			// Connection Poling에서 (서버 시작 시 미리 커넥션을 만들어두고 관리 => auth="Container") Connection 하기
			conn = ds.getConnection();
			
		} catch (NamingException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	// DB 연결
	// 한 사람이 쓰고 있는 동안 다른 사람이 못쓰면 비효율적
	// 작업 하나 수행하고 바로 끊어지도록 하는 게 바람직
	// public이어야 외부에서 사용 가능
	public static Connection dbConnection2() { // 밖에서도 Connection 사용을 위해 리턴
		// 2. Connection 생성
		// ip => 192.168.0.... / localhost 둘 다 본인 PC
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid = "hr";
		String password = "hr";
		Connection conn = null; // 아직 모르기 때문에 null

		// 1. JDBC Driver load
		// .class 작성 X
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			System.out.println("1. JDBC Driver load 성공");

			conn = DriverManager.getConnection(url, userid, password);
//			System.out.println("2. Connection 성공");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn; // 연결 return
	}

	// DB 연결 해제
	// 자원 반납
	// public이어야 외부에서 사용 가능
	public static void dbDisconnect(Connection conn, Statement st, ResultSet rs) {
		try {
			if (rs != null) rs.close();
			if (st != null) st.close();
			if (conn != null) conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
//			System.out.println("3. 실행 후 DB 연결 해제");
	}
}