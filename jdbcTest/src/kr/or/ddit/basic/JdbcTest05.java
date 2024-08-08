package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
 * LPROD 테아블에 새로운 데이터 추가하기
 * 
 * LPROD_GU와 LPROD_NM은 직접 입력 받아서 처리하고
 * LPROD_ID는 현재의 LPROD_ID 중에서 제일 큰 값보다 1 크게 한다.
 * 
 * 입력 받은 LPROD_GU가 이미 등록되어 있으면 다시 입력 받아서 처리한다.
 */
public class JdbcTest05 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			conn = DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521:xe", "PSA92", "java");
			
			conn = DBUtil.getConnection();
			
			// lprod_id는 현재의 lprod_id 중에서 제일 큰 값보다 1크게 한다.
			String sql = " SELECT MAX(LPROD_ID) MAXID FROM LPROD ";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			
			int maxId = 0;
			// ResultSet에 저장된 결과 데이터가 1개의 레코드일 경우
			// while문 대신 if문으로 처리해도 된다.
			if(rs.next()) {
				maxId = rs.getInt("maxId");		// 컬럼의 alias명을 이용하여 값 구하기
			}
			maxId++;
			
			//---------------------------------------------------------------
			// 입력 받은 LPROD_GU가 이미 등록되어 있으면 다시 입력 받아서 처리한다.
			String gu;		// 상품 분류 코드(LPROD_GU)가 저장될 변수 선언
			int count = 0; 	// 검색한 상품 분류 코드의 개수가 저장될 변수 선언
			
			String sql2 = "SELECT COUNT(*) CNT FROM LPROD WHERE LPROD_GU = ?";
			pstmt = conn.prepareStatement(sql2);
			
			do {
				System.out.print("상품 분류 코드 (LPROD_GU) 입력 >> ");
				gu = scan.next();
				
				// SQL문의 물음표(?)자리에 데이터 셋팅
				pstmt.setString(1, gu);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt("cnt");
				}
				
				if(count > 0) {
					System.out.println("입력한 상품 분류 코드" + gu + "는 이미 등록된 코드입니다.");
					System.out.println("다시 입력 하세요");
					System.out.println();
				}
			} while (count > 0);
			// ---------------------------------------------------------------
			
			System.out.println("상품 분류명(LPROD_NM) 입력 >> ");
			String nm = scan.next();
			
			String sql3 = "INSERT INTO LPROD(LPROD_ID, LPROD_GU, LPROD_NM)"
						+ "  VALUES(?, ?, ?)";
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, maxId);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("등록 성공!!");
			} else {
				System.out.println("등록 실패!");
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close(); } catch(SQLException e ) {}
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e ) {}
			if(conn != null) try { conn.close(); } catch(SQLException e ) {}
		}
	}

}
