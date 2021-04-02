package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MultiChatClient {
	Scanner scan = new Scanner(System.in);
	private String nickName; // 대화명

	// 프로그램 시작
	public void startClient() {
		// 대화명 입력받기
		System.out.print("대화명 >> ");
		nickName = scan.next();

		Socket socket = null;

		try {
			socket = new Socket("192.168.43.40", 7777);

			System.out.println("서버에 연결되었습니다.");
			System.out.println("귓속말 방법 : [/w 상대방닉네임 내용]");
			
			// 송신용 스레드 생성
			ClientSender sender = new ClientSender(socket, nickName);
			// 수신용 스레드 생성
			ClientReceiver receiver = new ClientReceiver(socket);
			
			sender.start();
			receiver.start();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	// 메시지를 전송하는 스레드 (송신용 Thread)
	class ClientSender extends Thread {
		Socket socket;
		DataOutputStream dos;
		String name;
		Scanner scan = new Scanner(System.in);

		public ClientSender(Socket socket, String name) {
			this.socket = socket;
			this.name = name;

			try {
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				// 시작하자마자 자신의 대화명을 서버로 전송
				if (dos != null) {
					dos.writeUTF(name);
				}
				while (dos != null) {
					// 키보드를 입력받은 메시지를 서버로 전송
					dos.writeUTF(scan.nextLine());
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 메시지를 전송받는 스레드(수신용 Thread)
	class ClientReceiver extends Thread{
		Socket socket;
		DataInputStream dis;
		
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			
			try {
				dis = new DataInputStream(socket.getInputStream());
			}catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			while(dis != null) {
				try {
					// 서버로부터 수신한메시지 출력하기
					System.out.println(dis.readUTF());
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();}
			}
		}
	}
	
	public static void main(String[] args) {
		new MultiChatClient().startClient();
	}
}
