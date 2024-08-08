package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Objects;

public class EqualsHashCodeTest {

	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setNum(1);
		p1.setName("홍길동");
		
		Person p2 = new Person();
//		p2.setNum(2);
//		p2.setName("일지매");
		p2.setNum(1);
		p2.setName("홍길동");
		
		Person p3 = p1; // Heap영역에 p3의 새로운 객체가 생성되는 것이 아니라 p1의 주소값이 p3에 들어와서, p1객체를 참조하게 됨
		
		
		// == 비교는 참조값(주소값) 비교이기 때문에 값이 같다고 true가 출력되지 않음
		System.out.println(p1 == p2);
		System.out.println(p1 == p3);
		System.out.println();
		
		// equals => == 과 동일 ==> equals도 참조값을 비교하는 것.
		// String 비교할 때 값 비교해주는 이유는 => String에 대해서는 equals()를 재정의해놨기 때문.
		// 그래서 우리도 아래에  equals()를 참조값이 아닌 값들을 비교하도록 재정의해줌
		System.out.println(p1.equals(p2));
		System.out.println(p1.equals(p3));
		System.out.println();
		
		HashSet<Person> testSet = new HashSet<Person>();
		testSet.add(p1);
		testSet.add(p2);
		System.out.println("Set의 개수 : " + testSet.size());
		// p1과 p2 안에 들어있는 값들은 동일하지만, p1과  p2모두 HashSet에 들어갈 수 있는 이유
		// 
		
		System.out.println("p1: " + p1.hashCode());
		System.out.println("p2: " + p2.hashCode());
		System.out.println("p3: " + p3.hashCode());
		System.out.println();
		System.out.println("p1: " + p1);
		System.out.println("p2: " + p2);
		System.out.println("p3: " + p3);
		
	}

}

class Person extends Object{
	private int num;
	private String name;
	
	public Person() {
	}
	

	@Override
	public boolean equals(Object obj) {
		
		 // 참조값이 같은지 검사
		 if(this == obj) {
			 return true;
		 }
		 
		 // obj가 null이 아닌지 검사
		 if(obj == null) {
			 return false;
		 }
		 
		 // 같은 유형의 클래스인지 검사
		 // getClass() : Class 정보를 가져오는 메서드
		 if(this.getClass() != obj.getClass()) {
			 return false;
		 }
		
		 // 위의 세 가지 조건에 걸리지 않으면
		 Person that = (Person)obj;  // 매개변수의 값을 현재 객체 유형으로 형 변환한다.
		 return this.num == that.num && Objects.equals(this.name, that.name);
		 // Objects.equals(객체1, 객체2) ==> 객체1과 객체2가 같으면 true 반환
	}
	
	
	@Override
	public int hashCode() {
		// num, name 값이 같으면 hash 값도 같도록
		return Objects.hash(num, name);
	}



	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
