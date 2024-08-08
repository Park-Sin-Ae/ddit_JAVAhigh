package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

public class FileTest02 {
	public static void main(String[] args) {
		File file1 = new File("d:/d_other/test.txt");
		
		System.out.println(file1.getName() + "의 크기 : " + file1.length() + "byte(s)");
		System.out.println();
		System.out.println("path : " + file1.getPath());
		System.out.println("absolutePath : " + file1.getAbsolutePath());
		
		// 현재 위치 나타내기
		File file2 = new File(".");
		System.out.println("path : " + file2.getPath());
		System.out.println("absolutePath : " + file2.getAbsolutePath());
		System.out.println();
		
		if(file1.isFile()) {
			System.out.println(file1.getName() + "은(는) 파일입니다.");
		}else if(file1.isDirectory()) {
			System.out.println(file1.getName() + "은(는) 디렉토리입니다.");
		}else {
			System.out.println(file1.getName() + "은(는) 뭘까요?");
		}
		System.out.println();
		
		if(file2.isFile()) {
			System.out.println(file2.getName() + "은(는) 파일입니다.");
		}else if(file2.isDirectory()) {
			System.out.println(file2.getName() + "은(는) 디렉토리입니다.");
		}else {
			System.out.println(file2.getName() + "은(는) 뭘까요?");
		}
		System.out.println();
		
		File file3 = new File("d:/d_other/sample.exe");
		
		if(file3.isFile()) {
			System.out.println(file3.getName() + "은(는) 파일입니다.");
		}else if(file3.isDirectory()) {
			System.out.println(file3.getName() + "은(는) 디렉토리입니다.");
		}else {
			System.out.println(file3.getName() + "은(는) 뭘까요?");
		}
		System.out.println();
		
		if(file3.exists()) {
			System.out.println(file3.getAbsolutePath() + "은 존재 합니다.");
		}else {
			System.out.println(file3.getAbsolutePath() + "은 존재하지 않습니다.");
			try {
				if(file3.createNewFile()) {
					System.out.println("파일 생성 완료");
				} else {
					 System.out.println("파일 생성 실패!");
				}
			} catch (IOException e) {
				// TODO: handle exception
			}
		}
			
	}
}
