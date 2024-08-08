package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/*
 *	- 정렬과 관련된 interface는 Comparable, Comparator 이렇게 두 가지가 있다.
 *
 *	- Comparable은 Collection에 추가되는 데이터 자체에 정렬 기준을 넣고 싶을 때 구현하는 인터페이스이다.
 *	  (내부 정렬 기준을 만들 때 사용하는 interface)
 *	- Comparator는 외부에 별도로 정렬 기준을 구현하고 싶을 때 구현하는 인터페이스이다.
 *	  (외부 정렬 기준을 만들 때 사용하는 interface)
 *
 *	- Comparagble를 구현할 때는 comarTo()메서드를 재정의하고,
 *	  Comparator를 구현할 때는 compare()메서드를 재정의 해야 한다.
 *	  
 *  - String 클래스, Wrapper 클래스, Date 클래스, File 클래스 등에는 '내부 정렬 기준'이 구현되어 있다.
 *    (내부 정렬 기준은 보통 오름차순으로 처리되도록 구현되어 있다.)
 */

public class ListSortTest01 {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		
		System.out.println("정렬 전 >> " + list);
		
		// 정렬은 Collections.sort() 메서드를 이용하여 정렬한다.
		// (참고 : 배열은 Arrays.sort() 메서드를 이용한다.)
		// 이 sort() 메서드는 기본적으로 '내부 정렬 기준'으로 정렬한다.
		
		Collections.sort(list);		// 오름차순 정렬
		
		System.out.println("정렬 후 >> " + list);
		
		Collections.shuffle(list);	// 자료 섞기
		
		System.out.println("자료 섞기 후 >> " + list);
		
		// 외부 정렬 기준 클래스를 적용해서 정렬하기
		// 형식) Collections.sort(리스트, 외부정렬클래스의 인스턴스);
		Collections.sort(list, new Desc());
		System.out.println("내림차순 정렬 후 >> " + list);
	}
}

// 정렬 방식을 정해 주는 class 작성하기('외부 정렬 기준 클래스' 작성하기)
// ==> Comparator 인터페이스를 구현해서 작성한다.
class Desc implements Comparator<String> {

	// compare()메서드를 이용해서 정렬하고자 하는 기준을 정해 준다.
	// ==> 이 메서드의 매개변수는 서로 인접한 데이터라고 생각하면 된다.
	
	// compare()메서드의 반환값
	// 반환값이 '0'일 때		==> 두 값이 같다.
	// 반환값이 '양수'일 때	==> 앞, 뒤의 순서를 바꾼다.
	// 반환값이 '음수'일 때	==> 앞, 뒤의 순서를 바꾸지 않는다.
	
	// 예) 오름차순일 경우	==> 앞의 값이 크면 양수, 같으면 0, 앞의 값이 작으면 음수를 반환하도록 구현하면 된다.
	
	@Override
	public int compare(String str1, String str2) {
		// 내림차순으로 구현하기
		if(str1.compareTo(str2) > 0) {
			return -1;
		} else if(str1.compareTo(str2) < 0) {
			return 1;
		} else {			
			return 0;
		}
//		
//		return str1.compareTo(str2) * -1;
	}

	
	
	
}
