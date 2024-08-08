package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/* 문제2) 5명의 별명을 입력 받아 ArrayList에 저장하고 이들 중에서
* 		별명의 길이가 제일 긴 별명을 출력하는 프로그램을 작성하시오.
* 		(단, 입력할 때 각 별명의 길이를 다르게 입력한다.)
* 		(작성  클래스명: ArrayListTest03)	
*/

public class ArrayListTest03 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		ArrayList<String> aliasList = new ArrayList<String>();
		
		System.out.println("서로 다른 길이의 별명을 5번 입력하세요 : ");
		
		for(int i = 1; i <= 5; i++) {
			System.out.println(i + "번째 별명 입력 >>");
			String alias = scan.nextLine();
			aliasList.add(alias);
		}
		
		// 제일 긴 별명이 저장될 변수를 선언하고, List의 첫번째 데이터로 초기화 한다.
		String maxAlias = aliasList.get(0);	// 긴 별명이 들어갈 공간을 만들어 0번째라고 지정한다.
		
		for(int i = 1; i < aliasList.size(); i++) {		// 위에 0번째부터 시작이기때문에 1번쨰부터 시작한다.
			if(maxAlias.length() < aliasList.get(i).length()) {
				maxAlias = aliasList.get(i);
			}
		}
		System.out.println();
		System.out.println("제일 긴 별명 : " + maxAlias);
	}
	
}
