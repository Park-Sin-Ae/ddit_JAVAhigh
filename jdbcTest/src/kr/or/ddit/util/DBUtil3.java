package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

// JDBC 드라이버를 로딩하고 Connection 객체를 생성하여 반환하는
// 메서드로 구성된 class 만들기

// (dbinfo.properties파일의 내용을 이용하여 설정하기
// 		==>ResourceBundle객체 이용하기)

public class DBUtil3 {
	private static ResourceBundle bundle; 			// ResourceBundle 객체 변수 선언
	static {
		bundle = ResourceBundle.getBundle("kr.or.ddit.config.dbinfo");
		try {
			// ResourceBundle 객체 생성
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(bundle.getString("driver"));
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
//			conn = DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521:xe", "PSA92", "java");
			
			conn = DriverManager.getConnection(
					bundle.getString("url"),
					bundle.getString("user"),
					bundle.getString("pass"));
		} catch (SQLException e) {
			System.out.println("DB 연결 실패!!");
			e.printStackTrace();
		}
		
		return conn;
	}
}

