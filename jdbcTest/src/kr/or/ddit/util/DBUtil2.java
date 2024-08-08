package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// JDBC 드라이버를 로딩하고 Connection 객체를 생성하여 반환하는
// 메서드로 구성된 class 만들기

// (dbinfo.properties파일의 내용으로 설정하기
//		==>Properties객체 이용하기)
public class DBUtil2 {
	private static Properties prop;		// Properties 객체 변수 선언
	static {
		prop = new Properties();	// Properties 객체 생성
		
		File f = new File("res/kr/or/ddit/config/dbinfo.properties");
		FileInputStream fin = null;
		
		try {
			// 입력용 스트림 객체 생성
			fin = new FileInputStream(f);
			
			prop.load(fin);			// 파일 내용 읽어와 Properties 객체에 저장하기
			
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(prop.getProperty("driver"));
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("입출력 오류 - 드라이버 로딩 실패!!");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
//			conn = DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521:xe", "PSA92", "java");
			conn = DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("user"),
					prop.getProperty("pass"));
		} catch (SQLException e) {
			System.out.println("DB 연결 실패!!");
			e.printStackTrace();
		}
		
		return conn;
	}
}
