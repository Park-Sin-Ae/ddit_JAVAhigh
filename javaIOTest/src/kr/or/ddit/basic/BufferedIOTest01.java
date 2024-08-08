package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {
	public static void main(String[] args) {
		// 입출력의 성능 향상을 위해서 Buffered 스트림을 사용한다.
		try {
			FileOutputStream fout = new FileOutputStream("d:/d_other/bufferTest.txt");
			BufferedOutputStream bout = new BufferedOutputStream(fout, 5);
			
			for(char ch = '1'; ch <= '9'; ch++) {
				bout.write(ch);
			}
			
			// 출력 작업을 마치면 버퍼에 남아 있는 데이터를 모두 출력 시켜야 한다.
			bout.flush();		
			System.out.println("!작업 끝!");
			
			// 보조 스트림을 닫으면 보조 스트림이 사용한 기반 스트림도 같이 닫힌다.
			bout.close();		// close에는 flush기능까지 있다.
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
