package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제) LPROD_ID 값을 2개 입력 받아서 두 값 중 작은 값부터 
//		큰 값 사이의 자료들을 출력하시오

public class JdbcTest03 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("첫번째 LPROD_ID 값 입력 >> ");
		int num1 = scan.nextInt();
		
		System.out.println("두번째 LPROD_ID 값 입력 >> ");
		int num2 = scan.nextInt();
		
		// 작은 수 큰 수 입력 상관없이 결과 값 보고 싶은 경우 temp를 사용하여 위치를 맞바꿔준다.
		if(num1 > num2) {
			int temp = num1;
			num1 = num2;
			num2 = temp;
		}
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PSA92","java");
			
			String sql = "SELECT *\r\n" + 
					"  FROM LPROD\r\n" + 
					" WHERE LPROD_ID >= ?"
					+ "AND LPROD_ID <= ?";
//			String sql = "SELECT * FROM LPROD WHERE LPROD_ID BETWEEN ? AND ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num1);
			pstmt.setInt(2, num2);
			
			rs = pstmt.executeQuery();
			/*
			String sql = "SELECT *\r\n" + 
					"  FROM LPROD\r\n" + 
					" WHERE LPROD_ID >=" + num1
					+ "AND LPROD_ID <=" + num2;
			
//			String sql = "SELECT * FROM LPROD WHERE LPROD_ID BETWEEN " + num1 + " AND " + num2;
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			*/
			System.out.println("== 결과 출력 ==");
			while(rs.next()) {
				System.out.println("LPROD_ID : " + rs.getInt("lprod_id"));
				System.out.println("LPROD_GU : " + rs.getString("lprod_gu"));
				System.out.println("LPROD_NM : " + rs.getString(3));
				System.out.println("---------------------------");
		}
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 5. 자원 반납
			if(rs != null) try { rs.close(); } catch(SQLException e) {}
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
			if(stmt != null) try { stmt.close(); } catch(SQLException e) {}
			if(conn != null) try { conn.close(); } catch(SQLException e) {}
		}
	}
	
}
