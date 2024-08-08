package kr.or.ddit.basic;

// yield() 메서드 연습

public class ThreadTest10 {
	public static void main(String[] args) {
		YieldThread th1 = new YieldThread("1번 쓰레드");
		YieldThread th2 = new YieldThread("2번 쓰레드");
		
		th1.start();
		th2.start();
		
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("1111111 ------------------------------------");
		
		th1.work = false;
		
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("2222222 ------------------------------------");
		
		th1.work = true;
		
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("3333333 ------------------------------------");
		
		th1.stop = true;
		th2.stop = true;
	}
}


// yield()메서드 연습용 쓰레드
class YieldThread extends Thread {
	public boolean stop = false;
	public boolean work = true;
	
	
	// 생성자
	public YieldThread(String name) {
		// 쓰레드의 이름을 설정한다. 
		super(name); 	// 현재 클래스의 부모의 생성자를 호출할때 쓴다 (class YieldThread extends Thread(<=부모))
	}
	
	@Override
	public void run() {
		while(!stop) {
			
			if(work) {
				System.out.println(getName() + "작업 중...");
			}else {
				System.out.println(getName() + "양보...");
				Thread.yield();
			}
			
		}
	}
}

