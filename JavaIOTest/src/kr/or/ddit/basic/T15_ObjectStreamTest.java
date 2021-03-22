package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 객체 입출력 보조 스트림 예제(직렬화와 역직렬화)
 * @author PC-06
 *
 */
public class T15_ObjectStreamTest {
	public static void main(String[] args) {
		Member mem1 = new Member("홍길동1", 20, "대전");
		Member mem2 = new Member("홍길동2", 30, "경기");
		Member mem3 = new Member("홍길동3", 40, "서울");
		Member mem4 = new Member("홍길동4", 50, "대구");
		Member mem5 = new Member("홍길동5", 60, "부산");
		
		try {
			// 객체를 파일에 저장하기
			
			// 출력용 스트림 객체 생성
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("d:/D_Other/memObj.bin"))); 
			
			oos.writeObject(mem1);
			oos.writeObject(mem2);
			oos.writeObject(mem3);
			oos.writeObject(mem4);
			oos.writeObject(mem5);
			
			System.out.println("쓰기 작업 완료");
			oos.close();
			
			//====================================
			//저장할 객체를 읽어와 출력하기
			
			// 입력용 스트림 객체 생성
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("d:/D_Other/memObj.bin")));
			
			Object obj = null;
			
			try {
				while((obj = ois.readObject()) != null) {	// 역직렬화
					//마지막에 다다르면 FOR 예외 발생
					Member mem = (Member) obj;
					System.out.println("이름 : " + mem.getName());
					System.out.println("이름 : " + mem.getAge());
					System.out.println("이름 : " + mem.getAddr());
				}
				ois.close();
			} catch (ClassNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			// 더 이상 읽어올 객체가 없으면 예외발생
			System.out.println("출력 작업 끝");
		}
	}
	
}

/**
 * 회원정보 VO
 * @author PC-06
 *
 */
class Member implements Serializable{
	//자바는 Serializable 인터페이스를 구현한 클래스만 직렬화 할 수 있또록 제한하고 있음
	//구현 안하면 직렬화작업시 java.io.NotSerializableExcepion예외 발생
	
	/**
	 * transient => 직렬화가 되지 않을 멤버변수에 지정한다.
	 * 				(static 빌드도 직겨화가 되지 않는다.)
	 * 				직렬화가 되지 않는 멤버변수는 기본값으로 저장된다.
	 * 				(참조형 변수: null, 숫자형 변수 : 0)
	 */
	private String name;
	private transient int age;		// transient(일시적)
	private String addr;
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
	
	
	
}