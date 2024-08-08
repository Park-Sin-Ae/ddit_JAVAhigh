package kr.or.ddit.basic;

// 쓰레드의 상태를 출력하는 예제

public class ThreadTest09 {
	public static void main(String[] args) {
		StatePrintThread th = new StatePrintThread(new TargetThread());
		
		th.start();
		
	}
}


// 쓰레드 상태의 감시 대상이 되는 쓰레드
class TargetThread extends Thread {
	@Override
	public void run() {
		for(long i = 1L; i <= 20_000_000_000L; i++) {
			long k = i + 1;
		}
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		for(long i = 1L; i <= 20_000_000_000L; i++) {
			long k = i + 1;
		}
	}
}

// TargetThread의 상태값을 구해서 출력하는 쓰레드
class StatePrintThread extends Thread {
	private TargetThread target;

	// 생성자
	public StatePrintThread(TargetThread target) {
		this.target = target;
	}
	
	@Override
	public void run() {
		while(true) {
			// TargetThread의 상태값 구하기
			Thread.State state = target.getState();	// 열거형 받아오기
			System.out.println("TargetThread의 상태값 : " + state);
			
			if(state ==  Thread.State.NEW) {		// 쓰레드의 상태가 NEW 상태이면.
				target.start();
			}
			
			// 쓰레드의 상태가 종료 상태이면. 
			if(state == Thread.State.TERMINATED) {
				break;
			}
			
			// NEW 상태도 아니고, TERMINATED 상태도 아닐 때 
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				
			}
		}
	}
}
