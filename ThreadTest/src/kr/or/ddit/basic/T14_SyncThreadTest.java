package kr.or.ddit.basic;

public class T14_SyncThreadTest {
	public static void main(String[] args) {
		
		ShareObject sObj = new ShareObject();
		
		WorkerThread th1 = new WorkerThread("1번 스레드", sObj);
		WorkerThread th2 = new WorkerThread("2번 스레드", sObj);
		
		th1.start();
		th2.start();
		
	}
}

class ShareObject{
	private int sum = 0;
	
	// 동기화 처리 영역이 넓어질수록 결과는 확실해지지만 영역이 넓을수록 성능이 저하될 수 있다.
	
	// 동기화 하는 방법 1 : 메서드 자체에 동기화 설정하기
	public /*synchronized*/ void add() {
		// 동기화 하는 방법 2 :동기화 블럭으로 설정
		synchronized (this) {
		for (int i = 0; i < 1000000000; i++) {}	//동기화까지 시간 벌기용
		
			int n = sum;
			n+= 10;
			sum = n;
		
		System.out.println(Thread.currentThread().getName() + "합계 : " + sum);
		}
	}
	
	public int getSum() {
		return sum;
	}
}

class WorkerThread extends Thread{
	ShareObject sObj;
	
	public WorkerThread(String name, ShareObject sObj) {
		super(name);
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			sObj.add();
			
		}
	}
	
}