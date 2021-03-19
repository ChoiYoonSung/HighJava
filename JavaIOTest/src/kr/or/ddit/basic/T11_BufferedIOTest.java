package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 성능향상을 위한 보조스트림 예제
 * (문자기반의 Buffered 스트링 사용 예제)
 * @author PC-06
 *
 */
public class T11_BufferedIOTest {
	public static void main(String[] args) {
		// 입출력 성능 향상을 위해서 버퍼를 이용하는 보조 스트림
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		
		try {
			fos = new FileOutputStream("D:/D_Other/bufferTest.txt");
			// 버퍼의 크기를 지정하지 않으면 기본적으로 버퍼의 크기가 8192byte(8Kb)로 설정된다.
			// 버퍼의 크기가 5인 스트림 생성
			bos = new BufferedOutputStream(fos, 5);
			for (int i = '1'; i <= '9'; i++) {
				bos.write(i);
			}
			
			bos.flush(); // 작업을 종료하기 전 버퍼에 남아있는 데이터를 출력시키는 작업
						 // 최근 버전에서는 .close시 자동으로 호출된다.
						 // 그러나 확실히 하기 위해선 .flush()메서드를 사용하는 것을 추천한다.
			bos.close();
			fos.close();
			System.out.println("작업 종료");
			
		}catch (IOException e) {
			e.printStackTrace();// TODO: handle exception
		}
	}
}
