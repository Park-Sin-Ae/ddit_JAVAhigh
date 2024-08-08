package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

//문제) 이름, 전화번호, 주소를 멤버로 갖는 Phone클래스를 작성한다.
//Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.
//
//(Map의 구조는 key값으로 '사람의 이름'을 사용하고 value값으로는 'phone'클래스의 인스턴스로 한다.)
//
//        아래 메뉴의 기능을 구현하시오. (삭제, 검색 기능에서는 '이름'을 입력 받아 처리한다.)


/*

실행예시)

1. 전화번호 등록
2. 전화번호 수정
3. 전화번호 삭제
4. 전화번호 검색
5. 전화번호 전체 출력
0. 프로그램 종료
----------------
 번호 입력 >> 1 
 
 새롭게 등록할 전화번호 정보를 입력하세요.
 이  름 >> 홍길동
 전화번호 >> 010-1234-5678
 주  소 >> 대전시 중구 오류동
 
 '홍길동'씨의 전화번호 등록이 완료되었습니다 ... 

1. 전화번호 등록
2. 전화번호 수정
3. 전화번호 삭제
4. 전화번호 검색
5. 전화번호 전체 출력
0. 프로그램 종료
----------------
 번호 입력 >> 1 
 
  새롭게 등록할 전화번호 정보를 입력하세요.
  이  름 >> 홍길동

  '홍길동'씨는 이미 등록된 사람입니다... 
  
1. 전화번호 등록
2. 전화번호 수정
3. 전화번호 삭제
4. 전화번호 검색
5. 전화번호 전체 출력
0. 프로그램 종료
----------------
 번호 입력 >> 5
 
------------------------------------
  번호     이  름        전화번호                         주 소      
------------------------------------
1  홍길동       010-1234-5678  대전시 중구 오류동
~~~     
------------------------------------
  출력완료
  
1. 전화번호 등록
2. 전화번호 수정
3. 전화번호 삭제
4. 전화번호 검색
5. 전화번호 전체 출력
0. 프로그램 종료
----------------
 번호 입력 >> 0
 
 프로그램을 종료합니다 ...

*/
public class PhoneBookTest {
	private HashMap<String, Phone> phoneBookMap;
	private Scanner scan;
	
	// 생성자
	public PhoneBookTest() {
		phoneBookMap = new HashMap<String, Phone>();
		scan = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		new PhoneBookTest().phoneBookStart();
//		test.phoneBookStart();
		
//		PhoneBookTest test = new PhoneBookTest();
		
	}
	
	// 시작 메서드
	public void phoneBookStart() {
		System.out.println("++++++++++++++++++++++++");
		System.out.println("전 화 번 호 관 리 프 로 그 램");
		System.out.println("++++++++++++++++++++++++");
		System.out.println();
		
		while(true) {
			int choice = displayMenu();
			switch(choice) {
			case 1 : 			// 등록
				insert();
				break;
			case 2 : 			// 수정
				update();
				break;
			case 3 : 			// 삭제
				delete();
				break;
			case 4 : 			// 검색
				search();
				break;
			case 5 : 			// 전체 출력
				printPhoneBook();
				break;
			case 0 : 			// 종료
				System.out.println();
				System.out.println("프로그램을 종료합니다.");
				return;
			default : 
				System.out.println();
				System.out.println("작업 번호를 잘못 입력했습니다.");
				System.out.println("다시 선택하세요");
			}
		}
	}
	
	// 자료를 검색하는 메서드
	private void search() {
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력하세요.");
		System.out.println("이름 >> ");
		String name = scan.next();
		
		// 등록 여부 검사
		if(!phoneBookMap.containsKey(name)) {
			System.out.println("'" + name + "'씨는 등록되지 않은 사람입니다.");
			System.out.println("검색 작업 종료");
			return;
		}
		
		Phone p = phoneBookMap.get(name);
		
		System.out.println(name + "씨 전화번호 정보");
		System.out.println("==============================");
		System.out.println("이 	름 : " + p.getName());
		System.out.println("전화번호 : " + p.getTel());
		System.out.println("주  	소 : " + p.getAddr());
		System.out.println("==============================");
		System.out.println();
		
	}

	// 자료를 삭제하는 메서드
	private void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요.");
		System.out.println("이 름 >> ");
		String name =scan.next();
		
		// 등록 여부 검사
		if(!phoneBookMap.containsKey(name)) {
			System.out.println("'" + name + "'씨는 등록되지 않은 사람입니다.");
			System.out.println("삭제 작업 종료.");
			return;
		}
		
		phoneBookMap.remove(name);
		
		System.out.println(name + "씨의 전화번호 정보를 삭제했습니다.");
	}
	

	// 자료를 수정하는 메서드
	private void update() {
		System.out.println();
		System.out.println("수정할 전화번호 정보르 입력하세요");
		System.out.println("이 름 >> ");
		String name = scan.next();
		
		if(!phoneBookMap.containsKey(name)) {		// 해당 이름이 없으면 수정 작업을 못한다.
			System.out.println("'" + name + "'씨의 전화번호 정보가 없습니다.");
			System.out.println("수정 작업 종료");
			return;
		}
		
		System.out.print("새로운 전화번호 >> ");
		String newTel = scan.next();
		
		scan.nextLine();	// 입력 버퍼 비우기
		
		System.out.print("새로운 주소 >> ");
		String newAddr = scan.nextLine();
		
		// 같은 key값에 새로운 전화번호(value)룰 저장한다. ==> 수정 작업
		phoneBookMap.put(name, new Phone(name, newTel ,newAddr));
		
		System.out.println(name + "씨의 전화번호 정보를 변경했습니다.");
	}

	// 전체 자료를 출력하는 메서드
	private void printPhoneBook() {
		System.out.println();
		
		Set<String> phoneKeySet = phoneBookMap.keySet();
		
		System.out.println();
		System.out.println("---------------------------------");
		System.out.println("번호	이름	전화번호	주소");
		System.out.println("---------------------------------");
		
		if(phoneKeySet.size() == 0 ) {
			System.out.println("등록 된 사람이 하나도 없습니다.");
		} else {
			int num = 0;			// 번호를 나타내는 변수
			for(String key : phoneKeySet) {	// key 값 개수만큼 반복
				num++;
				Phone p = phoneBookMap.get(key);	// key 값을 이용하여 Phone 객체 구하기
				
				
				System.out.println(" " + num + "\t" + p.getName() + "\t" 
							+ p.getTel() + "\t" + p.getAddr());
				
			}
		}
		System.out.println("---------------------------------------");
		System.out.println("출력 끝");
	}

	// 새로운 전화번호 정보를 등록하는 메서드
	private void insert() {
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.print("이 름 >> ");
		String name = scan.next();
		
		// 이미 등록 된 사람인지 검사하기 ==> key값에 입력 받은 이름이 있는 지 검사
		if(phoneBookMap.containsKey(name)) {		// if(phoneBookMap.containsKey(name))==true 생략 가능
			System.out.println("'" + name+ "'" + "씨는 이미 등록된 사람입니다.");
			return;
		}
		
		System.out.print("전 화 번 호 >> ");
		String tel = scan.next();
		
		scan.nextLine();	// Enter 값을 없애준다.(입력 버퍼 비우기)
		
		System.out.print("주 소 >> ");
		String addr = scan.nextLine();
		
//		Phone p = new Phone(name, tel, addr);
//		phoneBookMap.put(name, p);
		
		phoneBookMap.put(name , new Phone(name, tel, addr)); 	// 이렇게 짧게 줄여서 사용 가능
		
		System.out.println(name + " 전화번호 등록 완료!!!");
	}
	
	// 메뉴를 출력하고 작업 번호를 입력 받아 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("0. 프로그램 종료");
		System.out.println("----------------");
		System.out.println("번호 입력 >> ");
		
		return scan.nextInt();
	}
}

// 이름, 전화번호, 주소를 멤버로 갖는 Phone 클래스를 작성한다.
class Phone {
	private String name;
	private String tel;
	private String addr;
	public Phone(String name, String tel, String addr) {
		super();
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}
