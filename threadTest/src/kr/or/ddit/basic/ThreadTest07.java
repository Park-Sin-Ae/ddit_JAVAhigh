package kr.or.ddit.basic;

import javax.swing.JOptionPane;


/*
 * 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
 * 
 * 컴퓨터의 가위 바위 보는 난수를 이용해서 구하고,
 * 사용자의 입력은 showInputDialog()메서드를 이용해서 입력 받는다. 
 * 
 * 입력 시간은 5초로 제한하고 카운트 다운을 진행한다.
 * 5초 안에 입력이 없으면 게임에 진것으로 처리하고 프로그램을 끝낸다.
 * 
 * 5초 안에 입력이 완료되면 컴퓨터와의 승패를 구해서 출력한다.
 * 
 * 결과 예시) 
 * 
 * 1) 5초안에 입력을 못했을 때
 * 
 * -- 결 과 --
 * 시간 초과로 당신이 졌습니다.
 * 
 * 2) 5초안에 입력했을 때 
 * -- 결 과 -- 
 * 컴퓨터 : 가위
 * 사용자 : 바위
 * 결 과 : 당신이 이겼습니다.
 * 
 */

public class ThreadTest07 {
	public static boolean inputCheck;
	
	public static void main(String[] args) {
		GameTimer gt = new GameTimer();
		
		// 난수를 이용해서 컴퓨터의 가위 바위 보 정하기
		String[] data = {"가위", "바위", "보"};
		int index = (int)(Math.random() * 3);	// 0~2사이의 난수 만들기
		String com = data[index];
		
		// 사용자로부터 가위 바위 보 입력 받기
		gt.start();
		
		String man = null;
		do {
			man = JOptionPane.showInputDialog("가위 바위 보를 입력하세요.");
//		} while(!("가위".equals(man) || "바위".equals(man) || "보".equals(man)));
		} while(!"가위".equals(man) && !"바위".equals(man) && !"보".equals(man));	// 앞에 비교값 입력할 때 null 일 경우 에러를 뱉는다 
		inputCheck = true;
		
		// 결과를 판정하기
		String result = "";		// 결과가 저장 될 변수
//		if(com.equals(man)) {
//			result = "비겼습니다.";
//		}else if("가위".equals(com) && "보".equals(man) || 
//				 "바위".equals(com) && "가위".equals(man) ||
//				 "보".equals(com) && "바위".equals(man)) {
//			result = "당신이 졌습니다.";
//		}else {
//			result = "당신이 이겼습니다.";
//		}
		
		switch(com + man) {
		case "가위가위" :
		case "바위바위" :
		case "보보" :
			result = "비겼습니다.";
				break;
		case "가위보" :
		case "바위위" :
		case "보바위" :
			result = "당신이 졌습니다.";
			break;
			
		default :
			result = " 당신이 이겼습니다.";
		}
		
		// 결과 출력
		System.out.println(" -- 결 과 --");
		System.out.println("컴퓨터 : " + com);
		System.out.println("사용자 : " + man);
		System.out.println("결  과 : " + result);
	}
}

class showInputDialog extends Thread {
	@Override
		public void run() {
		}
}


// 카운트 다운 쓰레드
class GameTimer extends Thread {
	@Override
	public void run() {
		System.out.println("카운트 다운 시작");
		for(int i = 5; i >= 1; i--) {
			if(ThreadTest07.inputCheck == true) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println("-- 결 과 --");
		System.out.println("시간이 초과되어 당신이 졌습니다.");
	}
}