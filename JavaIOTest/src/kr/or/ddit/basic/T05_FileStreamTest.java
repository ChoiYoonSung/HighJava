package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 파일 읽기 예제
 */
public class T05_FileStreamTest {
	public static void main(String[] args) throws IOException {
		//FileInputStream객체를 이용한 파일 내용 읽기
		FileInputStream fis = null; //변수 선언
				
		//방법1(파일정보를 문자열로 지정하기)
		fis = new FileInputStream("d:/D_Other/test.txt"); //생성
		
		//방법2(파일정보  File객체를 이용하여 지정하기)
//		File file = new File("d:/D_Other/test.txt");
//		fis = new FileInputStream(file); //생성
		
		int c; // 읽어온 데이터를 저장할 변수
		
		//읽어온 값이 -1이면 파일의 끝까지 읽었다는 의미
		while((c=fis.read()) != -1) {	
			//읽어온 자료 출력하기
			System.out.print((char)c);
		}
		fis.close(); // 작업 완료 후 스트림 닫기
		
		// FileNotFoundException(하위) < IOException(상위)
		// throws 메서드 종료 후 Exception을 넘겨줌
		// 예외가 발생한다고 해서 무조건 try-catch로 잡는것이 좋은 방법은 아니다.
	}
}
