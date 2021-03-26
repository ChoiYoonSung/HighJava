package kr.or.ddit.basic;

public class T18_WaitNotifyTest {
/*
	wait()메서드 => 동기화 영역에서 락을 풀고 wait-set영역(공유객체별 존재)으로 이동시킨다.
	notify() 또는 notifyAll() 메서드 => wait-set 영역에 있는 스레드를 깨워서 실행될 수 있도록 한다.
	 								(notify()는 하나, notifyAll()은 모두 깨운다.)
	=> wait()과 notify(), notifyAll() 메서드는 동기화 영역에서만 실행할 수 있고,
		Object클래스에서 제공하는 메서드 이다.
 */
	public static void main(String[] args) {
		WorkObject workobj = new WorkObject();
		
		Thread th1 = new ThreadA(workobj);
		Thread th2 = new ThreadB(workobj);
		
		th1.start();
		th2.start();
	}
}

//공통으로 사용할 객체
class WorkObject{
	public synchronized void methodA() {
		System.out.println("methodA()메서드 작업 중 ");
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public synchronized void methodB() {
		System.out.println("methodB()메서드 작업 중 ");
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}

class ThreadA extends Thread{
	private WorkObject workobj;
	
	public ThreadA(WorkObject workObject) {
		this.workobj = workObject;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			workobj.methodA();
		}
		System.out.println("ThreadA 종료");
	}
}

class ThreadB extends Thread{
	private WorkObject workobj;
	
	public ThreadB(WorkObject workObject) {
		this.workobj = workObject;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			workobj.methodB();
		}
		System.out.println("ThreadB 종료");
	}
}





