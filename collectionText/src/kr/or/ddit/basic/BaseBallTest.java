package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Scanner;

/*
 * 예제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
 * 		컴퓨터의 숫자는 난수를 이용하여 구한다.
 * 		(스트라이크는 S, 볼은 B로 출력한다.)
 * 		같은 위치에 동일한 숫자면 스트라이크
 * 		다른 위치에 동일한 숫자면 볼
 * 
 *   	컴퓨터의 난수 ==> 9 5 7 (Set 을이용하여 중복을 피해 만들지만 List나 배열로 저장한다.)
 *   
 * 실행예시)
 * 	 (내가 입력한 데이터도 리스트나 배열에 저장한다)
 * 	  숫자입력 >> 3 5 6
 * 	 3 5 6 ==> 1S 0B
 * 	 숫자입력 >> 7 8 9
 * 	 7 8 9 ==> 0S 2B
 *   숫자입력 >> 9 7 5
 *   9 7 5 ==> 1S 2B
 *   숫자입력 >> 9 5 7
 *   9 5 7 ==> 3S 0b
 *   
 *   축하합니다. 당신은 4번째만에 맞췄습니다.
 *   
 */

public class BaseBallTest {

	public static void main(String[] args) {
		Scanner sc =  new Scanner(System.in);
		
		HashSet<Integer> comNum = new HashSet<>();
		HashSet<Integer> userNum = new HashSet<>();
		
		// 컴퓨터가 생성한 3개의 숫자 설정하기
		while (comNum.size() < 3) {
			int num = (int)(Math.random()*9) + 1; // 1부터 9까지의 난수 생성
			comNum.add(num);
		}
		System.out.println("컴퓨터가 숫자를 정했습니다. 숫자를 맞춰보세요!");
		
		int cnt = 0;
		
		// 게임 루프 생성하기
		while(true) {
			System.out.println("숫자를 입력하세요 (1부터 9까지 중복되지 않는 숫자 3개) >> ");
			userNum.clear(); // 이전에 입력한 숫자 삭제
			for(int i = 0; i < 3; i++) {
				int num = sc.nextInt();
				userNum.add(num);
			}
			
			// 스트라이크와 볼 계산하기
			int strikes = 0;
			int balls = 0;
			int index = 0;
			
			for(int num : userNum) {
				
			}
		}

	}

}
