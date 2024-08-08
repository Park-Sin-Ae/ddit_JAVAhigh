package kr.or.ddit.basic;

import java.util.ResourceBundle;

/*
 * ResourceBundle 객체 ==> 파일의 확장자가 '.properties'인 파일의 내용을 읽어와 
 * 				key 값과 value 값을 분리해서 정보를 갖는 객체
 * 
 */
public class ResourceBundleTest {

	public static void main(String[] args) {
		// ResourceBundle 객체를 이용하여 파일 내용 읽어오기
		
		// ResourceBundle 객체 생성
		//		==> 객체를 생성할 때 인수값으로 읽어올 파일을 지정하는데
		//			이 때는 '패키지명.파일명' 까지만 지정하고 확장자는 지정하지 않는다.
		//			(이우 : 확장자는 '항상'.properties'이기 때문에..)
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.config.dbinfo");
		
		// 얽어온 내용 출력하기
		System.out.println("driver : " + bundle.getString("driver"));
		System.out.println("url : " + bundle.getString("url"));
		System.out.println("user : " + bundle.getString("user"));
		System.out.println("pass : " + bundle.getString("pass"));
	}

}
