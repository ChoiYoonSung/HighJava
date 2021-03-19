package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

public class T01_FileTest {
	public static void main(String[] args) throws IOException {	
		/**
		 * 파일 객체 만들기 연습
		 * 1. new File(String 파일 또는 경로)
		 * => 디렉토리와 디렉토리 사이 또는 디렉토리 파일명 사이의 구문분하는 '\' or '/'를 사용할 수 있다.
		 */
//		File file = new File("d:/D_Other/test.txt");
		File file = new File("d:\\D_Other\\test.txt"); //파일을 생성하는것이 아닌, 파일 객체를 생성하는 것
		System.out.println("파일명 : " + file.getName());
		System.out.println("파일 여부 : " + file.isFile());
		System.out.println("디렉토리 여부 : " + file.isDirectory());
		System.out.println("---------------------------------------");
		
		File file2 = new File("d:\\D_Other");
//		File file2 = new File("d:\\D_Other\\test.txt");
		System.out.print(file2.getName() + "은 ");
		if(file2.isFile()) {
			System.out.println("파일");
		}else if(file2.isDirectory()) {
			System.out.println("디렉토리(폴더)");
		}else {
			System.out.println("알수없는 형태");
		}
		System.out.println("---------------------------------------");
		
		//2. new File(File parent, String child)
		//=> 'parent'디렉토리 안에 있는 'child'파일 또는 디렉토리를 말한다.
		File file3 = new File(file2, "test.txt");
		System.out.println(file3.getName() + "의 용량 크기 : " + file3.length() + "bytes"); //length : 해당파일 크기, 단위는 bytes
		
		//3. new File(String parent(경로), String child(파일명.확장자)
		File file4 = new File("d:/D_Other", "test.txt");
		System.out.println("파일 경로 : " + file4.getAbsolutePath());	//절대경로 : 제일 기본(root)부터 시작하여 파일의 경로를 표기
		System.out.println("경로 : " + file4.getPath());				//경로
		System.out.println("표준 경로 : " + file4.getCanonicalPath());	//표준경로
		System.out.println("현재 클래스의 URL : " + T01_FileTest.class.getResource(" T01_FileTest.class"));
		
		System.out.println("---------------------------------------");
		
		System.out.println(T01_FileTest.class.getResource("").getPath());
		
		/**
		 * 디렉토리(폴터) 만들기
		 * 
		 * 1. mkdir() => File객체의 경로 중 마지막 위치의 디렉토리를 만든다.
		 * 			  => 중간의 경로가 모두 미리 만들어져 있어야 한다.
		 * 
		 * 2. mkdirs() => 중간의 경로가 없으면 중간의 경로도 새롭게 만든 후
		 * 				    마지막 위치의 디렉토리를 만들어 준다. (해당 경로에 디렉토리를 만들 수 있는 권한이 있어야 함)
		 * 		=> 위 두 메서드 모두 만들기를 성공하면 true, 실패하면 false 반환
		 */
		File file5 = new File("d:/D_Other/연습용");
		if(file5.mkdir()) {
			System.out.println(file5.getName() + " 만들기 성공");
		}else {
			System.out.println(file5.getName() + " 만들기 실패");	//기존에 존재하는 디렉토리(폴더)가 있을 시 만들어지지 않는다.(중복x)
		}
		System.out.println();
		
		File file6 = new File("d:/D_Other/test/java/src");
		if(file6.mkdirs()) {
			System.out.println(file6.getName() + " 만들기 성공");
		}else {			
			System.out.println(file6.getName() + " 만들기 실패");
		}
		
	}
}
