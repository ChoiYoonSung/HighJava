package kr.or.ddit.basic;

public class T11_DisplayChatacterTest {
/**
	3개(명)의 스레드가 각각 알파벳 대문자를 출력하는데 출력을 끝낸 순서대로 결과를 나타내는 프로그램 작성하기
 */
	// 숫자 저장용 변수 선언 (공용으로 사용하기 위한 변수)
	static String strRank = "";
	public static void main(String[] args) {
		DisplayCharacter[] disChar = new DisplayCharacter[] {
				new DisplayCharacter("a"),
				new DisplayCharacter("b"),
				new DisplayCharacter("c")
		};
		for (int i = 0; i < disChar.length; i++) {
			disChar[i].start();
		}
		
		// 아래의 스레드가 모두 종료될 때 까지 기다려 결과를 도출할 수 있도록 함
		for(DisplayCharacter dc : disChar) {
			try {
				dc.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		System.out.println("경기 끝");
		System.out.println("-------------");
		System.out.println();
		System.out.println("경기 결과");
		System.out.println("순위 : " + strRank);
	}
	
}

// 대문자 출력하는 스레드 클래스
class DisplayCharacter extends Thread{
	private String name;

	//생성자
	public DisplayCharacter(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for(char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.println(name + "의 출력문자 : " + ch);
			
			try {
				// sleep()메서드의 값을 200-500 사이의 난수로 함
				Thread.sleep((int)Math.random()*401 + 100);
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		System.out.println(name + " 출력 끝");
		T11_DisplayChatacterTest.strRank += name + " ";
	}
	
}
