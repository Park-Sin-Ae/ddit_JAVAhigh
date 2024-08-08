package kr.or.ddit.basic;

/*
 * 	1 ~ 20억까지의 합계를 구하는 프로그램을 하나의 쓰레드가 단독으로 처리할 떄와
 * 	여러개의 쓰래드가 협력해서 처리할 때의 경과 시간을 비교해 보자
 * 
 */

public class ThreadTest04 {
	public static void main(String[] args) {
		// 단득으로 처리하는 쓰레드
		SumThread sm = new SumThread(1L, 2_000_000_000L);
		
		// 협력해서 처리할 쓰레드들
		SumThread[] smArr = new SumThread[] {
			new SumThread(1L, 500_000_000L),
			new SumThread(500_000_000L, 1_000_000_000L),
			new SumThread(1_000_000_000L, 1_500_000_000L),
			new SumThread(1_500_000_000L, 2_000_000_000L)
		};
		
		// 단독으로 처리하기
		long startTime = System.currentTimeMillis();
		
		sm.start();
		try {
			sm.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		long endTime = System.currentTimeMillis();
		
		System.out.println("단독으로 처리했을 때의 경과 시간 : " + (endTime - startTime));
		
		System.out.println();
		System.out.println();
		
		// 여러 쓰레드가 협력해서 처리하기
		startTime = System.currentTimeMillis();
		
		for(int i = 0; i < smArr.length; i++) {
			smArr[i].start();
		}
		
		for(SumThread th : smArr) {
			 try {
				th.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		endTime = System.currentTimeMillis();
		
		System.out.println("여러 쓰레드가 협력해서 처리한 경과 시간 : " + (endTime - startTime));
	}
	
	
}

class SumThread extends Thread {
	private long start;
	private long end;
	
	// 생성자에서 초기화 시키기
	public SumThread(long start, long end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	public void run() {
		long sum = 0L;
		for(long i = start; i <= end; i++) {
			sum += i;
		}
		System.out.println(start + "부터" + end + "까지의 합계 : " + sum);
	}
}
