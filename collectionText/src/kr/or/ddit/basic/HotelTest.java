package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * 문제) 호텔 객실을 관리하는 프로그램을 작성하시오.

	조건1)  호텔 객식을 나타내는 Room클래스는 방번호(int), 방종류, 투숙객이름 필드로 구성되어 있고			     
        방번호와 방종류는 다음과 같다.
        - 201~209 : 싱글룸
        - 301~309 : 더블룸
        - 401~409 : 스위트룸
	
	조건2) 전체 객실 관리는 Map을 이용한다.
       (Map의 key값은 방번호로 하고 value값은 Room의 인스턴스로 한다.)
       '호텔 객실 관리용 클래스'의 생성자에서 방번호와 방종류를 초기화한다.
 */
public class HotelTest {
	private Map<Integer, Room> hotelMap;
	private Scanner scan;
	
	// 생성장 ==> 호텔 객실을 초기화 한다.
	/*
	 * - 201~209 : 싱글룸
	 * - 301~309 : 더블룸
	 * - 401-409 : 스위트룸
	 */
	
	public HotelTest() {
		hotelMap = new HashMap<Integer, Room>();
		scan = new Scanner(System.in);
		
		// 객실 초기화
		for(int i = 2; i <= 4; i++) {
			String type = ""; 	// 방종류가 저장 될 변수
			switch(i) {
			case 2 : type = "싱글룸"; break;
			case 3 : type = "더블룸"; break;
			case 4 : type = "스위트룸"; break;
			}
			
			for(int j = 1; j <= 9; j++) {
				int num = i * 100 + j;
				hotelMap.put(num, new Room(num, type));
			}
		}
		
	}
	public static void main(String[] args) {
		new HotelTest().hotelTestStart();
	}
	private void hotelTestStart() {
		System.out.println();
		System.out.println("*********************************************");
		System.out.println("    호텔문을 열었습니다. 어서오십시요.");
		System.out.println("*********************************************");
		System.out.println();
		
		while(true) {
			int choice = displayMenu();
			switch(choice) {
			case 1 :			// 체크인
				checkIn();		
				break;
			case 2 :			// 체크아웃
				checkOut();
				break;
			case 3 :			// 객실 상태
				displayRoomState();
				break;
			case 4 :			// 종료
				System.out.println();
				System.out.println("*********************************************");
				System.out.println("    호텔문을 닫았습니다.");
				System.out.println("*********************************************");
				break;
			default :
				System.out.println("작업 번호를 잘못 입력했습니다.");
			}
				
		}
	}
	
	// 체크 아웃 메서드
	private void checkOut() {
		System.out.println("----------------------------------------------");
		System.out.println("체크아웃 작업");
		System.out.println("----------------------------------------------");
		System.out.println("체크아웃 할 방 번호를 입력하세요.");
		System.out.print("방번호 입력 >> ");
		
		int num = scan.nextInt();
		
		// 입력한 값이 Map 의 key값에 없으면 잘못 입력한 방 번호이다.
		if(!hotelMap.containsKey(num)) {
			System.out.println(num + "호 객실은 존재하지 않습니다  .");
			return;
		}
		
		// 입력한 방번호의 객실에 손님이 없는 지 검사
		if(hotelMap.get(num).getName()==null) {
			System.out.println(num + "호 객실에는 체크인한 손님이 없습니다.");
			return;
		}
		
		// 입력한 방 번호에 투숙한 손님 이름 구하기
		String name = hotelMap.get(num).getName();
		
		// 체크아웃 작업 ==> 해당 방 번호의 객실의 투숙객 이름을 null 로 변경하기
		hotelMap.get(num).setName(null);
		
		System.out.println(num + "호 객실의 " + name + "님이 체크아웃을 완료했습니다.");
		
	}
	// 객실 상태 출력하기
	private void displayRoomState() {
		System.out.println();
		
		System.out.println("----------------------------------------------");
		System.out.println("		현재 객실 상태");
		System.out.println("----------------------------------------------");
		System.out.println("방 번호	 방 종류	 투숙객 이름");
		System.out.println("----------------------------------------------");
		
		// 방번호를 순서대로 나오게 하기 위해서 방 번호 (Map의 key 값)만
		// List에 저장한 후 정렬하여 사용한다.
		
		ArrayList<Integer> numList = new ArrayList<Integer>(hotelMap.keySet());
		
		Collections.sort(numList);		// 방 번호를 오름차순으로 정렬
		
		// List의 방 번호를 차례로 꺼내와 Map에서 해당 방 번호와 같이 저장 된
		// Value값(Room객체)를 구해서 출력한다.
		for(int num : numList) {
			Room r = hotelMap.get(num);
			
			System.out.print(r.getNum() + "\t"); 
			System.out.print(r.getType() + "\t\t");
			System.out.println(r.getName() == null ? "-" : r.getName());
		}
		System.out.println("----------------------------------------------");
		System.out.println();
	}
	// 체크 인 메서드
	private void checkIn() {
		System.out.println("----------------------------------------------");
		System.out.println("체크인 작업");
		System.out.println("----------------------------------------------");
		System.out.println("* 201~209 : 싱글룸");
		System.out.println("* 301~309 : 더블룸");
		System.out.println("* 401~409 : 스위트룸");
		System.out.println("----------------------------------------------");
		System.out.println("방 번호 입력 >> ");
		int num = scan.nextInt();
		
		
		// 입력한 값이 Map 의 key값에 없으면 잘못 입력한 방 번호이다.
		if(!hotelMap.containsKey(num)) {
			System.out.println(num + "호 객실은 존재하지 않습니다  .");
			return;
		}
		
		// 해당 객실에 손님이 있는지 검사
		if(hotelMap.get(num).getName()!=null) {
			System.out.println(num + "호 객실에는 이미 손님이 있습니다.");
			return;
		}
		
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 >> ");
		String name = scan.next();
		
		// 입력 받은 손님 이름을 해당 객실의 투수객 명단에 저장한다.
		hotelMap.get(num).setName(name);
		
		System.out.println(name + "씨가 " + num + "호 객실에 체크인 되었습니다.");
		
	}
	
	// 메뉴 출력 및 작업 번호를 입력 받아 반환하는 메서드
	private int displayMenu() {
		System.out.println("-----------------------------------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
		System.out.println("-----------------------------------------------------------");
		System.out.print("선택>>	");
		
		return scan.nextInt();
	}
}

// 호텔 객실을 나타내는 Room 클래스는 방번호(int), 방종류, 투숙객이름 필드로 구성
class Room { 
	private int num;
	private String type;
	private String name;
	public Room() {
		super();

	}
	
	public Room(int num, String type) {
		super();
		this.num = num;
		this.type = type;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}