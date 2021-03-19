package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문자 변환 보조 스트림 예제
 * @author PC-06
 *
 */
public class T09_FileEncodingTest {
	//인코딩을 이용하여 문자 깨짐을 방지
	/**
	 * Input Stream Reader 객체는 파일의 인코딩 방식을 지정할 수 있다.
	 * 형식) new InputStreamReader(바이트기반스트림객체, 인코딩 방식)
	 * 
	 * - 인코딩 방식
	 * 		한글 인코딩 방식은 크게 UTF-8 과 EUC-KR 방식 두가지로 나누어 볼 수 있다.
	 * 		원래 한글윈도우는 CP949방식을 사용했는데, 윈도우를 개바한 마이크로소프트에서
	 * 		EUC-KR 방식으로 확정하였기 때문에 MS949라고도 부른다.
	 * 		한글윈도우 메모장에서 말하는 ANSI 인코딩이란 CP949(Code Page 949)를 말한다.
	 * 		CP949는 EUC-KR의 확장이며, 하위 호환성이 있다.
	 * 		- MS949 => 윈도우의 기본 한글 인코딩 방식(ANSI 계열)
	 * 		- UTF-8 => 유니코드 UTF-8 인코딩 방식(영문자 및 숫자: 1byte, 한글: 3byte)
	 * 		- US-ASCII => 영문전용 인코딩 방식
	 * 
	 * 		ANSI는 영어를 표기하기 위해 만든 코드로 규격 자체에 한글이 없다가 나중에 EUC-KR, CP949 이라는 식으로 한글이 포함되게 되었다.
	 * 
	 * 참고)
	 * 	- ASCII => extended ASCII(ISO 8859-1) => 조합형(초성, 중성, 종성), 완성형(KSC5601)
	 * -----------------------------------------------------------------------------------------------
	 * 윈도우 계열 : CP949(확장완성형) - 일부문자(8822자) 추가함	|
	 * 유닉스 계열 : EUC-KR(확장 유닉스코드)					| => ANSI계열(EUC-KR)
	 * 
	 * 유니코드(UTF-8)
	 */
	public static void main(String[] args) {
		// 파일 인코딩을 이용하여 읽어오기
		FileInputStream fis = null;
		InputStreamReader isr = null;
		
		try {
			fis = new FileInputStream("d:/D_Other/test_ansi_1.txt");
			
			isr = new InputStreamReader(fis, "cp949");
			
			int c;
			
			while((c=isr.read()) != -1) {
				System.out.print((char)c);
			}
			
			System.out.println();
			System.out.println("출력 완료");
			
			isr.close(); // 보조스트림만 닫아도 된다.
		} catch (IOException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
}
