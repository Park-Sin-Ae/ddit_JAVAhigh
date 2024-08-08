package kr.or.ddit.basic;

import java.util.Arrays;

/*
문제) 10마리의 말들이 경주하는 경마 프로그램 작성하기

말은 Horse라는 이름의 쓰레드 클래스로 구성하고,
이 클래스는 말이름(String), 등수(int), 현재위치(int) 를 멤버변수로 갖는다.

그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는
기능이 있다.( Comparable 인터페이스 구현)

경기 구간은 1~50구간으로 되어 있다.

경기 중 중간 중간에 각 말들의 위치를 나타내시오.
예)
01번말 --->------------------------------------	// 50칸
02번말 ----->----------------------------------
...
10번말 ----->----------------------------------
경기가 끝나면 등수 순으로 출력한다.

 */
public class ThreadTest13 {
	public static void main(String[] args) {
		Horse[] horseArr = new Horse[] {
				new Horse("01번 말"), new Horse("02번 말"), new Horse("03번 말"),
				new Horse("04번 말"), new Horse("05번 말"), new Horse("06번 말"),
				new Horse("07번 말"), new Horse("08번 말"), new Horse("09번 말"),
				new Horse("10번 말")
		};
		
		GameState gs = new GameState(horseArr);
		
		for(Horse h : horseArr) {
			h.start();
		}
		
		gs.start(); 	// 말의 현재위치 호출하는 메서드
		
		
		// 말들의 경기가 끝날때까지 기다려주는 반복문
		for(Horse h : horseArr) {
			try {
				h.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		// 말의 현재위치를 호출하는 쓰레드를 끝날때까지 기다려주는 코드
		try {
			gs.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		System.out.println();
		System.out.println("경기 끝");
		System.out.println();
		
		// 등수의 오름차순으로 정렬하기
		Arrays.sort(horseArr);
		
		System.out.println("  --- 경기 결과 ---  ");
		for(Horse h : horseArr) {
			System.out.println(h);
		}
	}
}

class Horse extends Thread implements Comparable<Horse>{
//	private static int currentRank = 0;	== 같은 뜻
	public static int currentRank;	// 현재 등수를 저장할 변수
	
	
	private String horseName;		// 말 이름
	private int rank;				// 등수
	private int location;			// 현재 위치
	
	// 생성자
	public Horse(String horseName) {
		super();
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "경주마 " + horseName + "는(은) " + rank 
				+ "등 입니다.";
	}
	
	
	// 현재 말이 달리기 
	@Override
	public void run() {
		for (int i = 1; i <= 50; i++) {
			location = i;		// 말의 현재 위치 저장
			
			try {
				Thread.sleep((int)(Math.random() * 500));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		// 한 마리의 말이 경주가 끝나면 현재 등수를 구해서 저장한다.
		currentRank++;
		rank = currentRank;
	}

	
	// 등수의 오름차순 정렬 기준 만들기
	@Override
	public int compareTo(Horse horse) {
		return Integer.compare(rank, horse.getRank());
	}
		
}
/*
	예)
	01번말 --->------------------------------------	// 50칸
	02번말 ----->----------------------------------
	...
	10번말 ----->----------------------------------
 */

// 경기 중 말의 현재 위치를 나타내는 쓰레드
class GameState extends Thread {
	private Horse[] horseArr;		// 경주에 참가하는 말들이 저장될 배열

	
	// 생성자
	public GameState(Horse[] horseArr) {
		super();
		this.horseArr = horseArr;
	}
	
	@Override
	public void run() {
		while(true) {
			// 모든 말들의 달리기가 종료되었는지 여부 검사
			if(Horse.currentRank == horseArr.length) {
				break;
			}
			for(int i = 1; i <= 10; i++) {
				System.out.println();
			}
			for(int i = 0; i < horseArr.length; i++) {
				System.out.print(horseArr[i].getHorseName() + " : ");
				
				for(int j = 1; j <= 50; j++) {
				if(horseArr[i].getLocation() == j) {	// 현재 위치 값 자리에 '>' 문자 출력하기
					System.out.print(">");
				}else {
					System.out.print("-");
				}
			}
				System.out.println();				// 줄 바꿈
		}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
}
