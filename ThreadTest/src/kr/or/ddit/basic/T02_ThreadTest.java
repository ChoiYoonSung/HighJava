package kr.or.ddit.basic;

public class T02_ThreadTest {
	public static void main(String[] args) {
		// 멀티 쓰레드 프로그램

		// 방법1 : Thread클래스를 상속한 class의 인스턴스를 생성한 후
		// 이 인스턴스의 start()메서드를 호출한다.

		MyThread1 mt1 = new MyThread1();
		mt1.start();

		// 방법2 : Runnable 인터페이스를 구현한 class의 인스턴스를 생성한 후
		// 이 인스턴스를 Thread객체의 인스턴스를 생성할 때 생성자의 매개변수로 넘겨준다
		// 이때 생성된 Thread객체의 인스턴스의 start()메서드를 호출한다.
		MyThread2 r1 = new MyThread2();
		Thread mt2 = new Thread(r1);
		mt2.start();

		/* 방법3 : 익명클래스를 이용하는 방법
		 * Runnable인터페이스를 구현한 익명클래스  Thread 인스턴스를 생성할 때
		 * 매개변수로 넘겨준다. 
		 */

		 Thread th3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0; i<=200; i++) {
					System.out.print("@\n");
					
					try {
						//Thread.sleep(시간) >> 주어진 시간동안 작업을 잠시 멈춘다.
						//시간은 밀리세컨드 단위를 사용함
						// 즉, 1000은 1초를 의미
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		th3.start();
		System.out.println("main메서드 작업 끝");
	}
}

class MyThread1 extends Thread {
	@Override
	public void run() {
		for (int i = 1; i < 200; i++) {
			System.out.print("*");
			try {
				// Thread.sleep(시간) => 주어진 시간동안 잠시 작업을 멈춘다.
				// 시간은
				// 즉, 1000은 1초를 의미
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}

class MyThread2 implements Runnable {
	@Override
	public void run() { // Thread 객체에서 실행하기 위한 중요한 역할로 run()이 있음
		for (int i = 1; i < 200; i++) {
			System.out.print("$");
			try {
				// Thread.sleep(시간) => 주어진 시간동안 잠시 작업을 멈춘다.
				// 시간은
				// 즉, 1000은 1초를 의미
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
