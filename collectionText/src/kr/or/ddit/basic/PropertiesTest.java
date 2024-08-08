package kr.or.ddit.basic;

import java.util.Properties;

public class PropertiesTest {
	/*
	 * - Properties 객체 ==> Map보다 축소된 기능의 객체라고 할 수 있다.
	 * 
	 * - Map은 Key값과 Value 값에 모든 형태의 객체를 사용할 수 있지만
	 * 	Properties 객체는 Key 값과 Value 값에 String만 사용할 수 있다.
	 * - Map은 put(),get()메서드를 이용해서 데이터를 입출력하지만 
	 * 	Properties객체는 setProperty(), getProperty()메서드를 통해서 데이터를 입출력 한다.
	 * 
	 * - Properties객체는 데이터를 파일로 입출력할 수 있다.
	 * 
	 * 
	 */
	
	public static void main(String[] args) {
		Properties prop = new Properties();
		
		prop.setProperty("name", "홍길동");
		prop.setProperty("age", "20");
		prop.setProperty("age2", "" + 20);				// 숫자를 문자열로 변환
		prop.setProperty("age3", String.valueOf(20));	// 숫자를 문자열로 변환
		prop.setProperty("tel", "010-1234-5678");
		prop.setProperty("addr", "대전");
		
		// --------------------------------------------------------------
		
		String name = prop.getProperty("name");
		int age = Integer.parseInt(prop.getProperty("age"));
		String tel = prop.getProperty("tel");
		String addr = prop.getProperty("addr");
		
		System.out.println("이      름 : " + name);
		System.out.println("나      이 : " + age);
		System.out.println("전화번호 : " + tel);
		System.out.println("주      소 : " + addr);
		
		System.out.println("#########################");
	}
}
