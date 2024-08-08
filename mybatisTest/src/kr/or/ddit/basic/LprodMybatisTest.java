package kr.or.ddit.basic;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.vo.LprodVO;

// myBatis를 이용하여 DB자료를 처리하는 순서 및 방법을 학습하는 예제
public class LprodMybatisTest {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// 1. myBatis의 환경 설정 파일(mybatis-config.xml)을 읽어와서 처리하기
		// 		==> 처리가 완료되면 SqlSessionFactory객체가 생성된다.
		InputStream in = null;
		SqlSessionFactory sqlSessionFactory = null;
		try {
			// 1-1. 환경 설정 파일을 읽어 올 스트림 객체 생성 
			in = Resources.getResourceAsStream(
					"kr/or/ddit/mybatis/config/mybatis-config.xml");
			
			// 1-2. 1-1에서 생성된 스트림을 이용하여 환경 설정 파일을 읽어와서 
			// 		해당 내용을 처리한다.
			// 		환경 설정 처리가 모두 완료되면 SqlSessionFactory객체가 반환된다.
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
			
		} catch (Exception e) {
			System.out.println("MyBatis 초기화 실패!!");
			e.printStackTrace();
		} finally {
			// 스트림 닫기
			if(in!=null) try {in.close();} catch(IOException e) {}
		}
		
		//--------------------------------------------------------------------------
		
		// 2. 환경 설정이 완료되면 mapepr에 등록된 SQL문들 중 실행할 SQL문을 
		//	     호출해서 원하는 작업을 수행한다.
		
		/*
		// 2-1. insert 연습
		System.out.println("insert 작업 시작..");
		
		System.out.print("Lprod_id 입력 >> ");
		int id = scan.nextInt();
		
		System.out.print("Lprod_gu 입력 >> ");
		String gu = scan.next();
		
		System.out.print("Lprod_nm 입력 >> ");
		String nm = scan.next();
		
		// 입력 받은 자료를 VO객체에 저장하기
		LprodVO lvo = new LprodVO();
		lvo.setLprod_id(id);
		lvo.setLprod_gu(gu);
		lvo.setLprod_nm(nm);
		
		SqlSession session = null;
		try {
			// SqlSessionFactory 객체를 openSession() 메서드를 이용하여
			// SQL문을 호출해서 실행할 수 있는 SqlSession객체를 생성한다.
			// 형식) SqlSessionFactory 객체.openSession(논리값)
			//		'논리값'이  true이면 AutoCommit이 활성화 된 상태이고,
			//		'논리값'이 생략되거나 false이면 AutoCommit이 비활성화된 상태가 된다.
			session = sqlSessionFactory.openSession();
			
			// SqlSession객체를 이용하여 처리할 SQL문을 호출하여 실행한다.
			// 형식) SqlSessio객체.insert("namespace속성값.실행할태그의id속성값",파라미터 클래스)
			// 		반환값 : 작업에 성공한 레코드 수
			
			int insertCnt = session.insert("lprod.insertLprod", lvo);
			
			if(insertCnt > 0) {
				// AutoCommit이 비활성화된 상태일 경우에는 commit을 직접 실행해 주어야 한다.
				session.commit();
				System.out.println("insert 작업 성공!");
			}else {
				System.out.println("insert 작업 실패ㅠㅠ");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원 반납 ==> SqlSession 객체 닫기
			if(session!=null) session.close();
		}
		System.out.println("------------------------------------------");
		*/
		
		/*
		// 2-2. update 연습
		System.out.println("update 작업 시작..");
		
		System.out.println("수정할 Lprod_gu 입력 >> ");
		String gu2 = scan.next();
		
		System.out.println("수정할 Lprod_id 입력 >> ");
		int id2 = scan.nextInt();
		
		System.out.println("수정할 Lprod_nm 입력 >> ");
		String nm2 = scan.next();
		
		// 입력 받은 자료 VO에 저장
		LprodVO lvo2 = new LprodVO();
		lvo2.setLprod_gu(gu2);
		lvo2.setLprod_id(id2);
		lvo2.setLprod_nm(nm2);
		
		SqlSession session = null;
		try {
			// SqlSession객체 생성
			session = sqlSessionFactory.openSession();
			
			// SQL문 호출하여 실행하기 ==> UPDATE처리
			// 형식) SqlSession객체.update("namespace속성값.id속성값",파라미터클래스)
			// 		반환값 : 처리한 레코드 수 
			int updateCnt = session.update("lprod.updateLprod", lvo2);
			
			if(updateCnt > 0) {
				session.commit();
				System.out.println("update 작업 성공!");
			} else {
				System.out.println("update 작업 실패ㅠㅠ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		*/
		
		
		/*
		// 2-3. delete 연습
		System.out.println("delete 작업 시작..");
		
		System.out.println("삭제할 Lprod_gu 입력 >> ");
		String gu3 = scan.next();
		
		SqlSession session = null;
		try {
			// SqlSession 객체 생성
			session = sqlSessionFactory.openSession();
			
			// SQL문 호출하여 실행 = DELETE 처리
			// 형식)SqlSession 객체.delete("namespace속성값.id속성값", 파라미터클래스)
			// 		반환값 : 작업에 성공한 레코드 수
			
			int deleteCnt = session.delete("lprod.deleteLprod", gu3);
			
			if(deleteCnt >0) {
				session.commit();
				System.out.println("Delete 작업 성공!!");
			}else {
				System.out.println("delete 작업 실패 ㅠㅠ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		*/
		
		/*
		// 2-4. select 연습
		
		// 1) SELECT문을 처리한 결과가 여러개의 레코드 일 경우
		System.out.println("select 작업 시작 - 결과가 여러개 일 경우..");
		
		SqlSession session = null;
		
		try {
			// SqlSession객체 생성
			session = sqlSessionFactory.openSession();
			
			// SQL문을 호출하여 실행 ==> SELECT문 처리
			// 처리한 결과가 여러개의 레코드일 경우에는 selectList()메서드를 
			// 사용하는데 이 메서드는 검색된 레코드 각각을 resultType속성에
			// 설정된 객체에 저장한 후 이 객체를 List에 추가해 주는 작업을 
			// 자동으로 수행한다.
			// 형식) SqlSession객체.selectList("namespace속성값.id속성값", 파라미터클래스)
			List<LprodVO> lprodList = session.selectList("lprod.getAllLprod");
			
			System.out.println("SELECT문 처리 결과..");
			for(LprodVO lvo4 : lprodList) {
				System.out.println("ID : " + lvo4.getLprod_id());
				System.out.println("GU : " + lvo4.getLprod_gu());
				System.out.println("NM : " + lvo4.getLprod_nm());
				System.out.println("----------------------------------");
			}
			System.out.println("출력 끝...!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		*/
		
		// 2) SELECT문을 처리한 결과가 한 개의 레코드 일 경우
		System.out.println("select 작업 시작 - 결과가 한 개일 경우..");
		
		System.out.print("검색할 Lprod_gu 입력 >> ");
		String gu4 = scan.next();
		
		SqlSession session = null;
		try {
			// SqlSession 갹채 생성
			session = sqlSessionFactory.openSession();
			
			// SQL문을 호출하여 실행 ==> SELECT문 처리
			// 처리한 결과가 한 개의 레코드일 경우에는 selectOne()메서드를 사용한다.
			// selectOne()메서드는 SELECT문을 처리한 결과가 없으면 null을 반환한다.
			// 형식) SqlSession객체.selectOne("namespace속성값.id속성값", 파라미터클래스)
			// 		반환값 : resultType속성에 지정한 것이 반환값의 자료형이 된다.
			LprodVO lvo5 = session.selectOne("lprod.getLprod", gu4);
			
			if(lvo5==null) {
				System.out.println("검색한 데이터가 하나도 없습니다.");
				return;
			}
			
			System.out.println("==== 검색 결과 ====");
			System.out.println("ID : " + lvo5.getLprod_id());
			System.out.println("GU : " + lvo5.getLprod_gu());
			System.out.println("---------------------------------------");
			System.out.println("출력 끝...!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
	}

}
