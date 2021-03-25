package kr.or.ddit.basic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RaceHorse {

	static int strRank = 1;

	public static void main(String[] args) {
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
		
		System.out.println("경기 시작");
		for (int i = 0; i < horse.length; i++) {
			horse[i].start();
		}

		for (Horse hs : horse) {
			try {
				hs.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("경기끝 ....");
		System.out.println("======================================================");
		System.out.println();
		System.out.println(" 경기 결과 ");

		for (Horse hs : horse) {
			System.out.println(hs.getName1() + " " + hs.getRank() + "등");
		}
	}
}

class Horse extends Thread implements Comparable<Horse>{
   private String name;
   int rank;
   
   public Horse(String name) {
      this.name = name;
   }
   
   public String getName1() {
      return name;
   }

   public void setName1(String name) {
      this.name = name;
   }

   public int getRank() {
      return rank;
   }

   public void setRank(int rank) {
      this.rank = rank;
   }


	@Override
	public void run() {
		String dstnSt = "";
		String dstnEn = "";
		String dstn;
		for(int i = 0; i < 50; i++) {
			dstnSt += "-";
			dstn = "";
			for (int j = 0; j < (49-i); j++) {
				if(j == 0) {
					continue;
				}
				dstn += "-";
				dstnEn = dstn;
			}
			if(i%5 == 0) {
				System.out.println(name + "\t: " + dstnSt + ">" + dstnEn);
			}
			try {
				Thread.sleep((int) (Math.random() * 250)+20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		System.out.println(name + " 끝");

		setRank(RaceHorse.strRank);
		RaceHorse.strRank++;
	}

	@Override
	public int compareTo(Horse hor) {
		if (rank > hor.getRank()) {
			return 1;
		} else {
			return -1;
		}
	}
}