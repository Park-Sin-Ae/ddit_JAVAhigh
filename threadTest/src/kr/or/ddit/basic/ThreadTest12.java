package kr.or.ddit.basic;


// 3개의 쓰레드가 각각 알파벳을 A~Z까지 출력하는데
// 출력을 끝낸 순서대로 결과를 나타내는 프로그램을 작성
public class ThreadTest12 {
	public static void main(String[] args) {
		DisplayCharacter[] disArr = new DisplayCharacter[] {
				new DisplayCharacter("홍길동"),
				new DisplayCharacter("이순신"),
				new DisplayCharacter("강감찬")
		};
		
		for(DisplayCharacter dis : disArr) {
			dis.start();
		}
		
		for(DisplayCharacter dis : disArr) {
			try {
				dis.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println();
		System.out.println("경기 결과");
		System.out.println("순위 : " + DisplayCharacter.strRank);
	}
	
}

// A~Z까지 출력하는 쓰레드
class DisplayCharacter extends Thread {
	public static String strRank = ""; // 출력한 순서대로 이름이 저장 될 변수
	private String name;
	
	// 생성자
	public DisplayCharacter(String name) {
		this.name = name;
	}
	@Override
	public void run() {
		for(char c = 'A'; c <= 'Z'; c++) {
			System.out.println(name + " 의 출력문자 : " + c);
			
			try {
				// 난수값으로 일시 정지 시간을 설정한다.ㅐ
				Thread.sleep((int)(Math.random() * 400));
				
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println(name + " 출력 끝");
		
		// 출력을 끝낸 순서대로 이름을 배치한다.
		strRank += name + " ";
	}
	
}
