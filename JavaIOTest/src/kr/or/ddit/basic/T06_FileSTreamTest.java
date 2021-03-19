package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 파일 출력 예제
 */
public class T06_FileSTreamTest {
	public static void main(String[] args) {
		//파일에 출력하기
		FileOutputStream fout = null;
		
		try {
			fout = new FileOutputStream("d:/D_Other/out.txt");
			for (char ch = 'a'; ch <= 'z'; ch++) {
				fout.write(ch);
			}
			System.out.println("파일 쓰기 작업 완료");
			//쓰기작업 완료 후 스트림 닫기
			fout.close();
			
			//===================================
			
			//저장된 파일의 내용을 읽어와 화면에 출력하기
			FileInputStream fin = new FileInputStream("d:/D_Other/out.txt");
			int c;
			while((c=fin.read()) != -1) {
				System.out.print((char)c);
			}
			System.out.println();
			System.out.println("파일 출력 끝");
			fin.close();
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
