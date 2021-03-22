package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyTest_buffered {
	public static void main(String[] args) {
		File file1 = new File("d:/D_Other/Tulips.jpg");
		File file2 = new File("d:/D_Other/복사본_Tulips.jpg");
		
		try {
			FileInputStream fis = new FileInputStream(file1); // 읽을파일
			FileOutputStream fos = new FileOutputStream(file2); // 복사할파일
			byte[] buffer = new byte[1024*1024];
			
			int c = 0;
			//파일 실행 (-1시 종료)
			while ((c = fis.read(buffer)) != -1) {
				fos.write(buffer,0,c);
			}
			
			System.out.println("복사 완료");
			
			fos.flush();
			//종료 반납
			fis.close();
			fos.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
