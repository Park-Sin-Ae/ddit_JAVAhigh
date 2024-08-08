package kr.or.ddit.basic;

public class ThreadTest03 {
	public static void main(String[] args) {
		// 쓰레드가 수행되는 시간을 체크해 보자
		Thread th = new Thread(new MyRunner02());
		
		// 현재시간 계산 (currentTimeMillis => 1970년 1월 1일 0시 00분 00초 부터 경과한 시간)
		// 밀리세컨드(1/1000) 단위로 반환한다.
		long startTime = System.currentTimeMillis();
		
		th.start();
		
		try {
			th.join();	// 현재 위치에서 대상이 되는 스레드(변수 th)가 끝날 때 까지 기다린다.
		} catch (InterruptedException e) {
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("경과 시간 : " + (endTime - startTime));
	}
}

class MyRunner02 implements Runnable {
	@Override
	public void run() {
		long sum = 0L;
		
		for(long i = 1L; i <= 1_000_000_000L; i++) {
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
}