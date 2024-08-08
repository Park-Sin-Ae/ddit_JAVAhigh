package kr.or.ddit.basic;

/*
 * Thread 클래스의 stop() 메서드를 호출하면 쓰레드가 바로 멈춘다.
 * 이 때 사용하던 자원을 정리하지 못하고 쓰레드가 중료되어 프로그램에 영향을 줄 수있다.
 * 그래서 stop() 메서드는 베추천으로 되어 있다.
 */
public class ThreadTest11 {
	public static void main(String[] args) {
		/*
		ThreadStopTest01 th1 = new ThreadStopTest01();
		th1.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
//		th1.stop();
		th1.setStop(true);	// 더 안전한 방법
		*/
		
		// interrupt() 메서드를 이용한 쓰레드 멈추기
		ThreadStopTest02 th2 = new ThreadStopTest02();
		th2.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		th2.interrupt();
	}
}

// 쓰레드를 멈추게하는 연습용 쓰레드
class ThreadStopTest01 extends  Thread {
	private boolean stop;
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	@Override
	public void run() {
		while(!stop) {
			System.out.println("쓰레드 실행 중");
		}
		System.out.println("자원정리");
		System.out.println("쓰레드 종료");
	}
}


// interrupt()메서드를 이용하여 쓰레드를 멈추게 하기
class ThreadStopTest02 extends Thread {
	@Override
	public void run() {
		// 방법1 ==> interrupt() 메서드와 sleep()메서드를 이용하는 방법
//		try {
//			while(true) {
//				System.out.println("실행중..");
//				
//				Thread.sleep(1);	// 일시 정지 상태에서 interrupt() 메서드가 실행되면
//									// InterruptedException 이 발생한다.
//			}
//		} catch (InterruptedException e) {
//			// TODO: handle exception
//		}
		
		// 방법2
		while(true) {
			System.out.println("Thread 실행중");
			
			// interrupt()메서드가 호출되었는지 검사한다.
			
			// 검사방법1 ==> Thread의 인스턴스 메서드인 isInterrupted()를 이용하여 검사하기
			// isInterrupted()메서드는 interrupt()메서드가 호출되면 true를 반환한다.
//			if(this.isInterrupted()) {
//				break;
//			}
			
			// 검사방법2 ==> Thread의 정적 메서드인 interrupted()를 이용하기
			// interrupted() 메서드도 interrupt()메서드가 호출되면 true를 반환한다.
			if(Thread.interrupted()) {
				break;
			}
		}
		
		System.out.println("자원 정리");
		System.out.println("쓰레드 종료");
	}
}