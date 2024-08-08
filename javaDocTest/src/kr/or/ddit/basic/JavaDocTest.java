package kr.or.ddit.basic;

// JavaDoc 문서 작성 예제
/*
 * 이것도 주석의 한 예
 * 
 */

/**
 * 
 * @author PC-22
 * @version 1.0
 * 
 * <p>
 * javaDoc 주석 안에서 HTML태그를 이용하여 내용을 작성한다. <br>
 * ------------------------------------------------</br>
 * <br>
 * 파일명 : JavaDocTest.java<br>
 * 설   명 : JavaDoc문서 작성을 위한 연습용 interface<br><br>
 * 
 * 변경 이력 <br>
 * -----------------<br>
 * 변경 일자 : 2024-03-11<br>
 * 변경 인    : 홍길동 <br>
 * 변경 내용 : 최초 생성 <br>
 * -----------------<br>
 * 
 * </p>
 *
 */
public interface JavaDocTest {
	/**
	 * 메서드명 : methodTest<br>
	 * 설      명 : 반환값이 없는 메서드<br><br>
	 * 
	 * @param a 첫번째 매개변수(정수형)
	 * @param b 두번째 매개변수(정수형)
	 */
	
	public void methodTest(int a, int b);
	
	/**
	 * 메서드명 : methodAdd<br>
	 * 설      명 : 반환값이 없는 메서드<br><br>
	 * 
	 * @param x 첫번째 정수형 변수
	 * @param y 두번째 정수형 변수
	 * @return 처리된 결과를 정수형으로 반환한다.
	 */
	
	public int methodAdd(int x, int y);
	
	/**
	 * 메서드명 : methodInput<br>
	 * 설      명 : 매개변수가 없고 반환값만 있는 메서드<br><br>
	 * 
	 * @return 정수형 값이 반환된다.
	 */
	
	public int methodInput();
}
