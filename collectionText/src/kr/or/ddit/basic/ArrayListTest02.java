package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;


/*
 * 문제1) 5명의 사람 이름을 입력 받아 ArrayList에 저장한 후에
 * 		이 ArrayList에 저장된 데이터들 중에서 '김'씨 성의 이름을
 * 		모두 출력하는 프로그램을 작성하시오.
 * 		(단, 입력은 Scanner 객체를 이용한다.)
 * 
 * 문제2) 5명의 별명을 입력 받아 ArrayList에 저장하고 이들 중에서
 * 		별명의 길이가 제일 긴 별명을 출력하는 프로그램을 작성하시오.
 * 		(단, 입력할 때 각 별명의 길이를 다르게 입력한다.)
 * 		(작성  클래스명: ArrayListTest03)	
 * 
 * 문제3) 5명의 별명을 입력 받아 ArrayList에 저장하고 이들 중에서
 * 		별명의 길이가 제일 긴 별명들을 출력하는 프로그램을 작성하시오.
 * 		(단, 입력하는 별명의 길이가 같을 수 있다.)
 * 		(작성  클래스명: ArrayListTest04)
 */

public class ArrayListTest02 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		ArrayList<String> nameList = new ArrayList<String>();
		System.out.println("5명의 이름을 입력하세요 : ");
		
		for(int i = 1; i <= 5; i++) {
			System.out.println(i + "번째 사람 이름 입력 >> ");
			String name = sc.next();
			nameList.add(name);
			
		} 
		System.out.println();
		System.out.println("김씨 성을 가진 사람들 : ");
		
		for(int i = 0; i < nameList.size(); i++) {
//			if(nameList.get(i).substring(0, 1).equals("김")) {  //0번째 있는 한글자
//				System.out.println(nameList.get(i));
//			}
//			if(nameList.get(i).charAt(0) == '김') { 		// 0 번째 있는 글자가 '김'과 같은 것  
//				System.out.println(nameList.get(i));
//			}
//			if(nameList.get(i).indexOf("김") == 0) { 
//				System.out.println(nameList.get(i));
//			}
			if(nameList.get(i).startsWith("김")) { 
				System.out.println(nameList.get(i));
			}
		}
		
	}
}
