package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


// 문제) 사용자로부터 LPROD_ID 값을 입력 받아 입력한 값보다
//		LPROD_ID가 큰 자료들을 출력하시오.


public class JdbcTest02 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("LPROD_ID 값 입력하세요 >> ");
		int id = scan.nextInt();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PSA92", "java");
			
			String sql ="SELECT *\r\n" + 
					"  FROM LPROD\r\n" + 
					" WHERE LPROD_ID >" + id;
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			System.out.println("======= 결과 출력 =======");
			while(rs.next()) {
					System.out.println("LPROD_ID : " + rs.getInt("lprod_id"));
					System.out.println("LPROD_GU : " + rs.getString("lprod_gu"));
					System.out.println("LPROD_NM : " + rs.getString("LPROD_NM"));
					System.out.println("---------------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 5. 자원 반납
			if(rs != null) try { rs.close(); } catch(SQLException e) {}
			if(stmt != null) try { stmt.close(); } catch(SQLException e) {}
			if(conn != null) try { conn.close(); } catch(SQLException e) {}
		}
	}
}
