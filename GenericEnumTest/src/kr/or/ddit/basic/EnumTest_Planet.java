package kr.or.ddit.basic;

public class EnumTest_Planet {
	public enum Planet{
		수성(2439), 
		금성(6052), 
		지구(6371), 
		화성(3390), 
		목성(69911), 
		토성(58232), 
		천왕성(25362), 
		해왕성(24622);
		
		private int radius;

		Planet(int radius) {
			this.radius = radius;
		}

		public int getRadius() {
			return this.radius;
		}

		public double getSize() {
			return 4 * Math.PI * radius * radius;
		}
	}
	

public static void main(String[] args) {
		
		System.out.println("--------------------------------");
		System.out.println("태양계 행성의 면적");
		System.out.println("--------------------------------");
		
		for(Planet planet : Planet.values()) {
			System.out.println(planet.name() + "의 면적 : " + planet.getSize());
		}
		System.out.println("--------------------------------");
	}
}
