package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 기본타입 데이터 입출력 보조 스트림 예제
 * @author PC-06
 */
public class T13_DataIOStreamTest {
	public static void main(String[] args) {
		try {
			FileOutputStream fos = new FileOutputStream("d:/D_Other/test.dat");
			//DataOutputStream은 출력용 데이터를 자료형에 맞게 출력해 준다.
			DataOutputStream dos = new DataOutputStream(fos);
			
			dos.writeUTF("ㅁㄴㅇㄹ");		//문자열데이터(UTF-8) 출력
			dos.writeInt(12);			//정수형 출력
			dos.writeFloat(3.14f);		//실수형(float) 출력
			dos.writeDouble(3.14);		//실수형(double) 출력
			dos.writeBoolean(false);	//논리형 출력
			System.out.println("입력 완");
			System.out.println("========================");
			dos.close();
			
			//=========================
			//저장한 순서대로 출력하지 않으면 데이터가 깨진다.
			FileInputStream fis = new FileInputStream("d:/D_Other/test.dat");
			DataInputStream dis = new DataInputStream(fis);
			
			System.out.println("문자열 : " + dis.readUTF());
			System.out.println("정수형 : " + dis.readInt());
			System.out.println("실수형(Float) : " + dis.readFloat());
			System.out.println("실수형(Double) : " + dis.readDouble());
			System.out.println("논리형 : " + dis.readBoolean());
			System.out.println("출력 완");
			dis.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
