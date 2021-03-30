package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HorseRacing {
	static int strRank = 1;
	public static List<Horse> horse = new ArrayList<Horse>();
	
	public static void main(String[] args) {
		Waiting wait = new Waiting();
		PrintRace pr = new PrintRace();
		
		for (int i = 0; i < 10; i++) {
			horse.add(new Horse((i+1) + "번마"));
		}
		//경기 시작 전 카운트
		wait.start();
		try {
			wait.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for (int i = 0; i < horse.size(); i++) {
			horse.get(i).start();
		}
		pr.start();
		try {
			pr.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("\n\n\n");
		System.out.println("경기종료");
		System.out.println("==========================");
		System.out.println("경기 결과");
		System.out.println();
		Collections.sort(horse);
		for (int i = 0; i < horse.size(); i++) {
			System.out.print(horse.get(i).getHname() + "\t:\t");
			System.out.println(horse.get(i).getRank() + "등");
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
		for (int i = 0; i < 50; i++) {
			try {
				Thread.sleep((int)(Math.random()*500)); // 지연시간 추가 수정 필
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			setLoc(i);
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

//해결해야 할 것
//각각의 스레드가 출력함 -> 한 번에 각각의 스레드의 구동 현황을 출력
//1. 10개의 스레드 값을 출력 스레드로 값을 넘기기
//2. 출력 스레드 실행 -> 경마 스레드 실행 -> 종료 후 결과 출력

class PrintRace extends Thread{
	@Override
	public void run() {
		System.out.println("경기 시작");
		while(true) {
			for (int i = 0; i < 20; i++) {
				System.out.println();
			}
			int finishCnt = 0;
			for (int i = 0; i < HorseRacing.horse.size(); i++) {
				String course = "--------------------------------------------------";
				Horse horse = HorseRacing.horse.get(i);
				if(horse.getLoc() != 49) {
					System.out.print(horse.getHname() + "\t|\t");
					System.out.println(course.substring(0,horse.getLoc()) + "▶" + course.substring(horse.getLoc()));
				}else {
					System.out.print(horse.getHname() + "\t|\t");
					System.out.println(course.substring(0,horse.getLoc()) + "도착");
					finishCnt++;
				}
			}
			if(finishCnt == 10) {
				return;
			}
			try {
				Thread.sleep(100); //20fps
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}