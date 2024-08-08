package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 예제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
     Role : 숫자가 같고 위치가 다르면 == Ball
   			숫자, 위치 같으면 == 스트라이크 
   			전부 스트라이크 할때까지 ... 
            컴퓨터의 숫자는 난수를 이용하여 구한다.
     (스트라이크는 S, 볼은 B로 출력)
         
            컴퓨터의 난수 ==> 9 5 7
실행예시)
            숫자입력 >> 3 5 6
          ==>1S 0B
            숫자입력 >> 7 8 9
          ==>0S 2B
            숫자입력 >> 9 7 5
          ==>1S 2B
            숫자입력 >> 9 5 7
          ==>3S 0B
            축하합니다. 당신은 4번째만에 맞췄습니다 !
                                      
 */

public class BaseBallTest2 {
	
	private ArrayList<Integer> numList;
	
	private ArrayList<Integer> userList = new ArrayList<Integer>();
	
	private Scanner scan = new Scanner(System.in);
	
	private int strike;   // 스트라이크의 개수
	private int ball;     // 볼의 개수

	public static void main(String[] args) {
		new BaseBallTest2().gameStart();
	}
	
	//게임이 시작되는 메서드
	public void gameStart() {
		// 난수를 만드는 메서드 호출
		createNum();
		
		// 확인용 출력
//		System.out.println("컴퓨터의 난수값 ==> " + numList);
		
		int cnt = 0; // 몇 번만에 맞췄는지를 저장하는 변수
		do {
			cnt++;
			
			// 사용자 입력 메서드 호출
			inputNum();
			
			// 볼카운트를 처리하는 메서드 호출
			ballCount();
			
		}while(strike!=3); // 3스트라이크가 될 때까지 반복 ...
		
		System.out.println();
		System.out.println("축하합니다. 당신은 " + cnt + "번째만에 맞췄습니다.");
	}
	
	// 1 ~ 9 사이의 서로 다른 난수 3개를 만들어서 List에 저장하는 메서드
	private void createNum() {
		Set<Integer> numSet = new HashSet<Integer>();
		
		// Set을 이용하여 중복되지 않는 난수 3개 만들기
		while(numSet.size()<3) {
			numSet.add((int)(Math.random()*9+1));
		}
		
		// 만들어진 난수를 List에 저장하기
		numList = new ArrayList<Integer>(numSet);
		
		// 데이터 섞기
		Collections.shuffle(numList);
	}
	
	// 사용자로부터 3개의 정수를 입력 받아 List에 저장하는 메서드
	// 입력한 정수들은 중복되지 않게 한다.
	private void inputNum() {
		int n1, n2, n3;  // 입력한 값이 저장될 변수 선언
		
		do {
			System.out.println("숫자입력 >> ");
			
			n1 = scan.nextInt();
			n2 = scan.nextInt();
			n3 = scan.nextInt();
			
			if (n1 == n2 || n1 == n3 || n2 == n3) {
				System.out.println("중복되는 값은 입력할 수 없습니다. 다시 입력하세요 ... ");
			}
						
		} while( n1 == n2 || n1 == n3 || n2 == n3 );
		
		// 입력 받은 값들을 List에 추가한다.
		userList.clear();
		userList.add(n1);
		userList.add(n2);
		userList.add(n3);
	}
	
	// 스트라이크와 볼을 판정하고 결과를 출력하는 메서드
	private void ballCount() {
		strike = 0;
		ball = 0;  // 스트라이크와 볼의 개수 초기화
		
		for(int i = 0; i < numList.size(); i++) {
			for(int j = 0; j < userList.size(); j++) {
				if(numList.get(i) == userList.get(j)) {
					if ( i == j ) {
						strike++;
					} else {
							ball++;
					}
					break;
					}
				}
			}
		// 볼 카운트 결과 출력하기
		System.out.printf("%d %d %d ==> %dS %dB\n",
				userList.get(0), userList.get(1), userList.get(2),  
				strike, ball);
		}
		
	}

