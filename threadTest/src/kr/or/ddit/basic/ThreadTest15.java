package kr.or.ddit.basic;

public class ThreadTest15 {

	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();
		
		TestThread th1 = new TestThread("1번 쓰레드", sObj);
		TestThread th2 = new TestThread("2번 쓰레드", sObj);
		
		th1.start();
		th2.start();
	}
}


class ShareObject {
	private int sum = 0;
	
	// 동기화 처리하기
//	public synchronized void add() {	// 방법 1 ==> 메서드에 동기화 설정하기
	public void add() {
		synchronized(this) {	// 블록 안에를 처리하는 동안 rock 걸려있는 상태
		int n = sum;
		
		n += 10; 		// 10증가
		
		sum = n;
		
		System.out.println(Thread.currentThread().getName() + " 합계 : " + sum);
		}
	}
}

class TestThread extends Thread {
	private ShareObject sObj;
	
	
	// 생성자
	public TestThread(String name, ShareObject sObj) {
		super(name);	// 쓰레드의 name 설정할 때 사용하는 방법
		this.sObj = sObj;
	}
		
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			sObj.add();
		}
	}
}