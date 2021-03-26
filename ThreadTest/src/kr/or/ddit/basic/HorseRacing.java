package kr.or.ddit.basic;

public class HorseRacing {
	static int strRank = 1;

	public static void main(String[] args) {
		
		Waiting wait = new Waiting();
		Horse[] horse = new Horse[] {
				new Horse("1번마"),
				new Horse("2번마"),
				new Horse("3번마"),
				new Horse("4번마"),
				new Horse("5번마"),
				new Horse("6번마"),
				new Horse("7번마"),
				new Horse("8번마"),
				new Horse("9번마"),
				new Horse("10번마")
		};
		//경기 시작 전 카운트
		wait.start();
		try {
			wait.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//경기 시작
		System.out.println("경기 시작");
		for (int i = 0; i < horse.length; i++) {
			horse[i].start();
		}
		for(Horse hs : horse) {
			try {
				hs.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		System.out.println("경기종료");
		System.out.println("==========================");
		System.out.println("경기 결과");
		System.out.println();
		for (int i = 0; i < horse.length; i++) {
			System.out.print(horse[i].getHname() + " : ");
			System.out.println(horse[i].getRank());
		}
		System.out.println();
		System.out.println("==========================");
	}
}

class Waiting extends Thread{
	public void run() {
		try {
			System.out.println("========3========");
			Thread.sleep(1000);
			System.out.println("========2========");
			Thread.sleep(1000);
			System.out.println("========1========");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class PrintRace{
	static String Pname;
	static int dtsnt;
	
}

class Horse extends Thread implements Comparable<Horse>{
	private String Hname;
	int rank;
	int loc;


	public Horse(String name) {
		this.Hname = name;
	}
	
	public String getHname() {
		return Hname;
	}

	public void setHname(String name) {
		this.Hname = name;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLoc() {
		return loc;
	}
	
	public void setLoc(int loc) {
		this.loc = loc;
	}

	@Override
	public void run() {
		String dstnSt = "";
		String dstnEn = "";
		String dstn;
		for (int i = 0; i < 50; i++) {
			dstnSt += "-";
			dstn = "";
			for (int j = 0; j < (49 - i); j++) {
				if (j == 0) {
					continue;
				}
				dstn += "-";
				dstnEn = dstn;
			}
			if (i % 2 == 0) {
				System.out.println(Hname + "\t: " + dstnSt + ">" + dstnEn);
			}
			try {
				Thread.sleep((int)(Math.random()*500)+20); // 지연시간 추가 수정 필
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		setRank(HorseRacing.strRank);
		HorseRacing.strRank++;
	}
	
	public int compareTo(Horse hor) {
		if (rank > hor.getRank()) {
			return 1;
		} else {
			return -1;
		}
	}
}