package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesIOTest {

	public static void main(String[] args) {
		Properties prop = new Properties();
		
		File f = new File("res/kr/or/ddit/config/memo.properties");
		FileOutputStream fout = null;
		
		try {
			// Properties객체에 데이터 셋팅하기
			prop.setProperty("name", "홍길동");
			prop.setProperty("age", String.valueOf(30));
			prop.setProperty("tel", "010-1234-5678");
			prop.setProperty("addr", "대전시 중구 오류동");
			
			// 출력용 스트림 객체 생성
			fout = new FileOutputStream(f);
			
			// Properties객체의 내용을 출력	==> store()메서드 이용
			prop.store(fout, "이것은 주석 내용입니다.");
			System.out.println("출력 작업 완료..");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(fout != null) try { fout.close(); } catch(IOException e) {}
		}
		
		System.out.println("-----------------------------------------------------");
		// ------------------------------------------------------------------
		// memo.properties 파일의 내용을 읽어와 출력하ㅣ
		
		Properties prop2 = new Properties();
		
		FileInputStream fin = null;
		
		try {
			// 입력용 스트림 객체 생성
			fin = new FileInputStream(f);
			
			// 입력용 스트림을 이용하여 파일 내용을 읽어와 Properties 객체에 저장하기
			//	==> load()메서드 이용
			//		==> 파일 내용을 읽어와 key값과 value 값을 분류하여 Properties 객체에 추가한다.
			prop2.load(fin);
			
			// 읽어온 정보 화먄에 출력하기
			System.out.println();
			System.out.println("name : " + prop2.getProperty("name"));
			System.out.println("age : " + prop2.getProperty("age"));
			System.out.println("tel : " + prop2.getProperty("tel"));
			System.out.println("addr : " + prop2.getProperty("addr"));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(fin != null) try { fin.close(); } catch(IOException e) {}
		}
	}

}
