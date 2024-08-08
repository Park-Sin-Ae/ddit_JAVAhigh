package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;


/*문제) 이름, 전화번호, 주소를 멤버로 갖는 Phone클래스를 작성한다.
	Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.

	(Map의 구조는 key값으로 '사람의 이름'을 사용하고 value값으로는 'phone'클래스의 인스턴스로 한다.)

        아래 메뉴의 기능을 구현하시오. (삭제, 검색 기능에서는 '이름'을 입력 받아 처리한다.)

- 추가 조건)
1) '6. 전화번호 저장' 메뉴를 추가하고 구현한다.
	(저장 파일명은 'phoneBookData.dat'로 한다.)
2) 프로그램이 시작될 때 저장된 파일이 있으면 그 데이터를 읽어와 Map에 셋팅한다.
3) 프로그램을 종료할 때 Map의 데이터가 변경되거나 추가 또는 삭제되면 저장한 후 종료되도록 한다.

*/
public class PhoneBookTest {
	private HashMap<String, Phone> phoneBookMap;
	private Scanner scan;
	private String fileName = "d:/d_other/phoneBookData.dat";
	
	// 데이터가 변경되었는지 여부를 나타내는 변수 선언
	// 데이터가 변경되면 이 변수는 true 값이 된다.
	private boolean dataChange;
	
	// 생성자
	public PhoneBookTest() {
//		phoneBookMap = new HashMap<String, Phone>();
		phoneBookMap = load();
		scan = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
//		new PhoneBookTest().phoneBookStart();
//		test.phoneBookStart();
		
		new PhoneBookTest().phoneBookStart();
		
	}
	
	// 파일에 저장된 전화번호 정보를 읽어오는 메서드
	private HashMap<String, Phone> load() {
		HashMap<String, Phone> pMap = null;		//읽어온 데이터가 저장될 변수
		
		// 읽어올 파일이 있는지 검사하기
		File file = new File(fileName);
		if(!file.exists()) {					// 저장된 파일이 ㅇ벗을때
			return new HashMap<String, Phone>();// 빈 Map 객체를 생성해서 반환
		}
		
		// 저장된 파일이 있을 때 처리되는 곳.
		ObjectInputStream oin = null;
		try {
			// 입력용 스트림 객체 생성
			oin = new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream(file)));
			
			// 파일에 저장된 자료를 읽어와 Map에 저장하기
			
			// 1) save()메서드에서 '방법1'로 저장했을 때
//			pMap = (HashMap<String, Phone>)oin.readObject();
			
			//------------------------------------------------
			
			// 2) save()메서드에서 '방법2'로 저장했을 때
			pMap = new HashMap<String, Phone>();
			Object obj = null;
			
			while((obj=oin.readObject()) != null) {
				// 읽어온 자료를 Map에 추가하기
				Phone p = (Phone)obj;
				pMap.put(p.getName(), p);
			}
			//------------------------------------------------
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(oin != null) try {oin.close();} catch (IOException e) {}
		}
		return pMap;
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
			case 6 : 			// 저장
				save();
				break;
			case 0 : 			// 종료
				System.out.println();
				
				// 데이터가 변경되었는지 확인 후 저장하기
				if(dataChange) save();
				
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
	
	// 자료를 저장하는 메서드
	private void save() {
		ObjectOutputStream oout = null;
		try {
			// 출력용 스트림 객체 생성
			oout = new ObjectOutputStream(
					new BufferedOutputStream(
							new FileOutputStream(fileName)
							)
					);
	
			// Map에 저장된 데이터 파일로 저장하기
			
			// 방법1 ==> Map객체 자체를 저장하기
//			oout.writeObject(phoneBookMap);
			//----------------------------------------------
			
			
			// 방법2 ==> Map에 저장 된 Phone객체를 하나씩 저장하기
			for(String key : phoneBookMap.keySet()) {
				Phone p = phoneBookMap.get(key);
				oout.writeObject(p);
			}
			oout.writeObject(null);
			//----------------------------------------------
			
			System.out.println("저장이 완료되었습니다.");
			
			dataChange = false;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 사용했던 스트림 닫기
			if(oout != null) try {oout.close();} catch (IOException e) {}
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
		dataChange = true;
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
		dataChange = true;
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
		
		dataChange = true;
	}
	
	// 메뉴를 출력하고 작업 번호를 입력 받아 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("6. 전화번호 저장");
		System.out.println("0. 프로그램 종료");
		System.out.println("----------------");
		System.out.println("번호 입력 >> ");
		
		return scan.nextInt();
	}
}

// 이름, 전화번호, 주소를 멤버로 갖는 Phone 클래스를 작성한다.
class Phone implements Serializable {
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
