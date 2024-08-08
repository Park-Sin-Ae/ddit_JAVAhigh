package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIOTest06 {
	public static void main(String[] args) {
		// Scanner로 출력한 단을 입력 받아 입력 받은 단의 구구단을
		// 'D:/d_other'폴더에 'gugudan.txt' 파일로 출력하는 프로그램을 작성하시오.
		
		
//		try {
//			System.out.print("구구단의 단수를 입력하세요: ");
//			InputStreamReader isr = new InputStreamReader(System.in);
//			int dan = (int)isr.read();
//			System.out.println(dan);
//			for (int i = 1; i <= 9; i++) {
//				System.out.printf("%d * %d = %d\n", dan, i, dan*i);
//			}			
//			
//			FileWriter fw = new FileWriter("d:/d_other/gugudan.txt");
//			System.out.println("아무 내용이나 입력하세요. (입력의 끝은 'Ctrl + Z' 입니다.)");
//			
//			int c;
//			
//			while((c = isr.read()) != -1) {
//				fw.write(c);
//			}
//			
//			isr.close();
//			fw.close();
//			
//			System.out.println();
//			System.out.println("작업 끝!");
//		} catch (IOException e) {
//			// TODO: handle exception
//		}
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("출력할 단 입력 >> ");
		int dan = scan.nextInt();
		
		try {
			
			// 파일 출력용 문자 기반 스트림 객체 생성
			FileWriter fw = new FileWriter("d:/d_other/gugudan.txt");
			
			for(int i = 1; i <= 9; i++) {
				String str = dan + " * " + i + " = " + (dan * i) + "\r\n";
				
			
//				System.out.println(str);
				fw.write(str);
			}
			
			System.out.println("출력 끝!!");
			fw.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
	}
}
