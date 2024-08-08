package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

//로또를 구매하는 프로그램 작성하기
//
//사용자는 로또를 구매할 때 구매할 금액을 입력하고
//입력한 금액에 맞게 로또번호를 출력한다.
//(단, 로또 한장의 금액은 1000원이며 최대 100장까지만 구입할 수 있고,
//     거스름돈도 계산하여 출력한다.)


public class LottoStore {
	private Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		new LottoStore().lottoStoreStart();
	}
	
	// 시작 메서드
	public void lottoStoreStart() {
		while(true) {
			int choice = displayMenu();
			switch(choice) {
			case 1 :	// 로또 구입
				buyLotto();
				break;
			case 2 :	// 종료
				System.out.println("");
				System.out.println("감사합니다.");
				return;	// 메서드 자체를 빠져나간다
			default :
				System.out.println();
				System.out.println("메뉴 선택을 잘못 했습니다.(메뉴 번호는 '1' 또는 '2'를 입력하세요)");
			}
		}
		
	}
	
	// 로또 구매를 처리하는 메서드
	private void buyLotto() {
		System.out.println();
		System.out.println("Lotto 구입 시작");
		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.println("금액 입력 : ");
		
		int money = scan.nextInt();
		System.out.println();
		
		if(money < 1000) {
			System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
			
			return;
		}
		if(money >= 101000) {
			System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
			
			return;
		}
		
		HashSet<Integer> lottoSet = new HashSet<Integer>();
		
		int count =	money / 1000; 				// 구해야 할 로또의 갯수 구하기
		
		System.out.println();
		System.out.println("행운의 로또번호는 아래와 같습니다.");
		
		for(int i = 1; i <= count; i++) {		// 구매 할 로또 개수만큼 반복
			// 로또번호 생성하기
			while(lottoSet.size() < 6) {
				lottoSet.add((int)(Math.random() * 45 + 1));
			}
			
			ArrayList<Integer> lottoList = new ArrayList<Integer>(lottoSet);
			Collections.sort(lottoList);
			
			System.out.println("로또번호 " + i + ">> " + lottoList);
			lottoSet.clear();					// 새로운 로또번호를 생성하기 위해 Set의 자료를 모두 삭제한다.
			
		}
		System.out.println();
		
		System.out.println("받은 금액은 " + money + "원이고 거스름돈은  " +(money % 1000)+" 원입니다.");
				
				
	}
	
	// 메뉴를 출력하고 입력한 메뉴 번호를 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println("==========================");
		System.out.println("Lotto 프로그램");
		System.out.println("--------------------------");
		System.out.println("1. Lotto 구입");
		System.out.println("2. 프로그램 종료");
		System.out.println("==========================		 ");
		System.out.println("메뉴선택 : ");
		System.out.println();
		
		return scan.nextInt();
		
	}
}
