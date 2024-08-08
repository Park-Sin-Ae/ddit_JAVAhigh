package kr.or.ddit.basic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.LprodVO;

// 'jdbcTest' 프로젝트에 있는 JdbcTest05 클래스의 처리를 myBatis로 처리하시오.

/*
 * LPROD 테아블에 새로운 데이터 추가하기
 * 
 * LPROD_GU와 LPROD_NM은 직접 입력 받아서 처리하고
 * LPROD_ID는 현재의 LPROD_ID 중에서 제일 큰 값보다 1 크게 한다.
 * 
 * 입력 받은 LPROD_GU가 이미 등록되어 있으면 다시 입력 받아서 처리한다.
 */

public class JdbcToMybatisTest {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		/*
		// myBatis의 환경 설정 파일을 읽어와서 처리하기
		InputStream in = null;
		SqlSessionFactory sqlSessionFactory = null;
		try {
			in = Resources.getResourceAsStream(
					"kr/or/ddit/mybatis/config/mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			System.out.println("myBatis 초기화 실패~!!");
			e.printStackTrace();
		} finally {
			if(in!=null) try { in.close();} catch (IOException e) {}
		}
		//--------------------------------------------------------------------
		*/
		
		SqlSession session = null;
		
		try {
//			session = sqlSessionFactory.openSession();
			session = MybatisUtil.getSqlSession();
			
			// LPROD_ID는 현재의 LPROD_ID 중에서 제일 큰 값보다 1 크게 한다.
			int maxid = session.selectOne("jdbc.getMaxId");
			maxid++;
			
			// 입력 받은 LPROD_GU가 이미 등록되어 있으면 다시 입력 받아서 처리한다.
			String gu;
			int count = 0;
			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력 >> ");
				gu = scan.next();
				
				count = session.selectOne("jdbc.getLprodCount", gu);
				
				if(count > 0) {
					System.out.println("입력 한 상품 분류 코드 " + gu + "는 이미 등록된 코드입니다.");
					System.out.println("다시 입력하세요.");
				}
					
			} while (count > 0);
			
			System.out.print("상품 분류명(LPROD_NM) 입력 >> ");
			String nm = scan.next();
			
			// insert 할 자료들을 VO객체에 저장한다.
			LprodVO lvo = new LprodVO();
			lvo.setLprod_id(maxid);
			lvo.setLprod_gu(gu);
			lvo.setLprod_nm(nm);
			
			int cnt = session.insert("jdbc.insertLprod", lvo);
			
			if( cnt > 0) {
				session.commit();
				System.out.println("등록 완료!");
			} else {
				System.out.println("등록 실패!!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원 반납
			if(session!=null) session.close();
		}
	}
}
