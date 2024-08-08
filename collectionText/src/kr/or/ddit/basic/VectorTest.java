package kr.or.ddit.basic;

import java.util.Vector;

public class VectorTest {
	public static void main(String[] args) {
		// 객체 생성
		Vector v1 = new Vector();
		
		System.out.println("처음 크기 : " + v1.size());		//0
		
		// 데이터 추가하기1 : add(추가할 데이터)
		// 반환 값 : 추가 성공(true), 추가 실패(false)
		
		
		v1.add("aaaa"); 		// vector는 배열이므로 바로 저장한다.
		v1.add(new Integer(111));// 기본자료형을 객체화 시 wrapperClass로 감싼다.
		v1.add(123);			// 오토박싱, 오토 언박싱이 지원된다.
		v1.add('a');
		v1.add(true);
		
		boolean r = v1.add(3.14);
				
		System.out.println("현재의 크기 : " + v1.size());	// 5
		System.out.println("반환값 : " + r);				// true
		System.out.println("v1 => " + v1.toString());
		
		
		// 데이터 추가하기2: addElement(추가할데이터)
		// ==> 이전 버전의 프로그램과의 호환성을 위해서 나아 있는 메서드
		v1.addElement("CCCC");
		System.out.println("v1 => " + v1);
		
		// 데이터 추가하기3 : add(index, 데이터)
		// ==> 'index' 번째에 '데이터'를 끼워 넣는다.
		// ==> 'index'는 0부터 시작한다., 반환값은 없다.
		v1.add(1, "KKKK");
		System.out.println("v1 => " + v1);
		System.out.println();
		
		// 데이터 꺼내오기 : get(index)
		// ==> 'index' 번째의 데이터를 꺼내서 반환한다.
		System.out.println("0번째 데이터 : " + v1.get(0));
		
		// 명시적으로 부모의 데이터가 자식 데이터에 넣을때는 형변환을 해줘야 한다.
		// 반대로 자식데이터를 부모데이터에 넣을때는 자동형변환이 된다.
		int iTemp = (int) v1.get(2);
		System.out.println("2번째 데이터 : " + iTemp);
		
		// 데이터 수정하기 : set(index, 새로운데이터)
		// ==> 'index' 번째의 데이터를  '새로운데이터'로 변경한다.
		// ==> 반환값 : 변경되기 전의 원래의 데이터
		String sTemp = (String) v1.set(0, "zzZ");
		System.out.println("v1 => : " + v1);
		System.out.println("원래의 데이터 : " + sTemp);
		System.out.println();
		
		// 데이터 삭제하기1 : remove(index)
		// ==> 'index'번째의 데이터를 삭제한다.
		// ==> 반환값 : 삭제된 데이터
		v1.remove(0);
		System.out.println("삭제 후 v1 => : " + v1);
		
		sTemp = (String) v1.remove(0);
		System.out.println("삭제 후 v1 => : " + v1);
		System.out.println("삭제된 데이터  : " + sTemp);
		System.out.println();
		
		// 데이터 삭제하기2 : remove(삭제할데이터)
		// ==> '삭제할데이터'를 찾아서 삭제한다.
		// ==> '삭제할데이터'가 여러개이면 이들 중에서 제일 첫번째로 찾아진 자료를 삭제한다.
		// ==> 반환값 : 삭제성공(true), 삭제실패(false)
		// ==> '삭제할데이터'가 '정수형'이거나 'char형'일 경우에는 반드시 객체로 변환해서 사용해야 한다.
		v1.remove("CCCC");
		System.out.println("CCCC 삭제 후  v1 => " + v1);
		
		
		//오버로딩    : 메서드 이름만 같고 매개변수의 개수가 다르거나 매개변수의 타입이 다를때 구별한다(반환값은 상관없음)
		//오버라이딩 : 부모가 가지고 있는 메서드를 재정의한다.
		
//		v1.remove(123); // 정수는 index로 저장되기때문에 객체화 시켜줘야한다.
//		v1.remove(new Integer(123)); // 자바 1.9이상에서는 사용 불가 (비추천:오류날수있음)
		v1.remove(Integer.valueOf(123));
		System.out.println("123 삭제 후 v1 => " + v1);
		
//		v1.remove('a');
		v1.remove(Character.valueOf('a'));
		System.out.println("'a' 삭제 후 v1 => " + v1);
		
		v1.remove(true);
		v1.remove(3.14); // 실수형은 정수형 데이터로 반환 될 일 없으니 그냥 사용해도 된다.
		System.out.println("삭제 후 v1 => " + v1);		
		
		// 전체 데이터 삭제하기 :  clear()
		v1.clear();
		System.out.println("clear 삭제 후 v1 => " + v1);
		System.out.println("-----------------------------");
		//------------------------------------------------
		
		/*
		 제네릭타입(Generic Type) ==> 클래스 내부에서 사용할 데이터의 타입을 객체를 생성할 때
		 외부에서 지정해 주는 기법으로 객체를 선언 할 때 괄호('< >') 안에 그 객체의 내부에서 사용할
		 데이터의 타입을 정해주는 것을 말한다.
		 
		 - 이런식으로 선언하게 되면 그 데이터 타입 이외의 다른 종류의 데이터들을 저장할 수 없다.
		 - 이 떄 제네릭으로 선언 될 수 있는 데이터 타입은 '클래스형' 이어야 한다.
		 - 그래서 int는 Integer, bollean은 Boolean, char는 Character 등으로 대체해서 사용해야 한다.
		 - 제네틱 타입으로 선언하게 되면 데이터를 꺼내 올 때 
		 */
		Vector<Integer> v2 = new Vector<Integer>(); //int형 자료만 저장할 수 있는 Vector
	      Vector<String> v3 = new Vector<String>(); // String형 자료만 저장할 수 있는 Vector
	      
	      v3.add("안녕하세요");
	      // v3.add(100); -- 오류 : 지정한 제네릭 타입과 다른 종류의 데이터를 저장할 수 없다.
	      
	      String sTemp2 = v3.get(0); // 형변환을 해주지 않아도 됨
	      
	      Vector<Vector> vv = new Vector<Vector>();
	      Vector<Vector<Vector>> vvv = new Vector<Vector<Vector>>();
	      System.out.println(vv);
	      System.out.println("--------------------------------------");

		
		v3.clear();
		System.out.println("v3의 size : " + v3.size());
		
		v3.add("AAA");
		v3.add("BBB");
		v3.add("CCC");
		v3.add("DDD");
		v3.add("EEE");
		v3.add("FFF");
		
		Vector<String> v4 = new Vector<String>();
		v4.add("BBB");
		v4.add("EEE");
		
		System.out.println("v3 => " + v3);
		System.out.println("v4 => " + v4);
		
		// 데이터 삭제하기3: removeAll(Collection 객체)
		// ==> 벡터의 데이터들 중에서 'Collection객체'가 가지고 있는 모든 데이터들을 삭제한다.
		// ==> 반환값 : 성공(true), 실패(false)
		v3.removeAll(v4); // v3의 데이터들 중에서 v4가 가지고 있는 데이터들을 모두 삭제한다.
		System.out.println("삭제 후 v3 => " + v3);
		System.out.println("--------------------------------");
		
		v3.clear();
		v3.add("AAA");
		v3.add("BBB");
		v3.add("CCC");
		v3.add("DDD");
		v3.add("EEE");
		v3.add("FFF");
		
		
		for(int i = 0; i < v3.size(); i++) {
			System.out.println(i + "번째 데이터 : " + v3.get(i));
		}
		System.out.println("--------------------------------");
		
		// 향상된 for문
		for (String str : v3) {
			System.out.println(str);
		}
		System.out.println("--------------------------------");
	}
}
