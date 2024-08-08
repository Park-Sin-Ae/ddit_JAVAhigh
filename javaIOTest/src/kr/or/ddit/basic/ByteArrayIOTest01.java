package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest01 {
	public static void main(String[] args) {
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outSrc = null;
		
		// 입출력을 처리할 스트림 객체 생성
		ByteArrayInputStream bin = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		
		int data;		// 읽어온 자료가 저장될 변수

		// read()메서드 ==> 더 이상 읽어올 자료가 없으면 -1을 반환한다.
		while((data = bin.read()) != -1) {
			bout.write(data);	// 읽어온 자료를 그대로 출력하기
			
		}
		// 출력된 스트림값을 배열로 변환해서 저장하기
		outSrc = bout.toByteArray();
		
		// 작업이 완료되면 사용했던 스트림을 닫아 준다.(자원 반납)
		try {
			bin.close();
			bout.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		System.out.println(" inSrc : " + Arrays.toString(inSrc));
		System.out.println(" outSrc : " + Arrays.toString(outSrc));
	}
}
 