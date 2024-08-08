package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest02 {
	public static void main(String[] args) {
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4];		// 입출력에 사용할 배열 생성
		
		try {
			// 입출력을 처리할 스트림 객체 생성
			ByteArrayInputStream bin = new ByteArrayInputStream(inSrc);
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			
			// 읽어올 데이터가 있는지 확인
			// available() ==> 읽어올 수 있는 데이터의 byte 수를 반환
			while(bin.available() > 0) {
//				bin.read(temp);
//				bout.write(temp);
				
				// read(배열); ==> 반환값 : 실제 읽어온 데이터의 갯수
				int len = bin.read(temp);	
				
				// temp배열의 데이터들 중에서 0번째 부터 len 개수만큼 출력한다.
				bout.write(temp, 0, len);
				
				System.out.println("반복문 안에서 temp : " + Arrays.toString(temp));
			}
			
			outSrc	= bout.toByteArray();
			
			bin.close();
			bout.close();
			
			System.out.println(" inSrc : " + Arrays.toString(inSrc));
			System.out.println(" outSrc : " + Arrays.toString(outSrc));
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
	}
}
 