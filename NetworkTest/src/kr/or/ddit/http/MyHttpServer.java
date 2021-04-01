package kr.or.ddit.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.util.StringTokenizer;

public class MyHttpServer {
	private final int port = 80;
	private final String encoding = "UTF-8";

	/**
	 * 응답 헤더 생성하기
	 * 
	 * @param contentLength
	 * @param mimeType
	 * @return
	 */
	private byte[] makeResponseHeader(int contentLength, String mimeType) {
		String header = "HTTP/1.1 200 OK\r\n" 
				+ "Server: MyHTTPServer 1.0\r\n"
				+ "Content-length : " + contentLength + "\r\n" 
				+ "Content-type : " + mimeType + "; charset=" 
				+ this.encoding + "\r\n\r\n";

		return header.getBytes();
	}

	
	private byte[] makeResponseBody(String filePath) throws IOException {
		FileInputStream fis = null;
		byte[] data = null;
		try {
			File file = new File(filePath);
			data = new byte[(int) file.length()];

			fis = new FileInputStream(file);
			fis.read(data);
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
		return data;
	}
	
	/**
	 * 에러페이지 생성
	 * @param out
	 * @param statusCode
	 * @param errMsg
	 */
	private void makeErrorPage(OutputStream out, int statusCode, String errMsg) {
		String statusLine = "HTTP/1.1" + " " + statusCode + " " + errMsg;
		try {
			out.write(statusLine.getBytes());
			out.flush();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * HTTP 서버 시작
	 */
	public void startServer() {
		try (ServerSocket server = new ServerSocket(this.port)){
			while(true) {
				try {
					Socket socket = server.accept();
					
					// HTTP 요청처리를 위한 스레드 객체
					HttpHandler handler = new HttpHandler(socket);
					new Thread(handler).start();
				} catch (IOException e) {
					// TODO: handle exception
					System.err.println("커넥션 오류");
					e.printStackTrace();
				}catch (RuntimeException e) {
					// TODO: handle exception
					System.err.println("알 수 없는 오류");
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * HTTP 요청 처리를 위한 Runnable 객체
	 */
	private class HttpHandler implements Runnable{
		private final Socket socket;
		
		public HttpHandler(Socket socket) {
			this.socket = socket;
		}
		@Override
		public void run() {
			OutputStream out = null;
			BufferedReader br = null;
			
			try {
				out = new BufferedOutputStream(socket.getOutputStream());
				br = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				
				// 요청헤더 정보 파싱하기
				StringBuilder request = new StringBuilder();
				while(true) {
					String str = br.readLine();
					
					if(str.equals("")) break;	// emptyLine 체크
					
					 request.append(str + "\n");
				}
				
				System.out.println("요청헤더 :\n" + request.toString());
				
				String reqPath = "";
				
				// 요청페이지 정보 가져오기
				StringTokenizer st = new StringTokenizer(request.toString());
				while(st.hasMoreElements()) {
					String token = st.nextToken();
					if(token.startsWith("/")) {
						reqPath = token;
					}
				}
				
				// 상대경로(프로젝트 폴더 기준) 설정
				String fileName = "./WebContent" + reqPath;
				
				//해당 파일 이름을 이용하여 Content-type 정보 추출하기
				String contentType = URLConnection.getFileNameMap().getContentTypeFor(fileName);
				System.out.println("contentType => " + contentType);
				
				File file = new File(fileName);
				if(!file.exists()) {
					makeErrorPage(out, 404, "Not Found");
				}
				
				byte[] body = makeResponseBody(fileName);
				byte[] header = makeResponseHeader(body.length, contentType);
				// 읽어야 할 body의 크기를 같이 읽어옴
				
				// 요청헤더가 HTTP/1.0 이나 그 후의 버전을 지원할 경우 MIME 헤더를 전송한다.
				if(request.toString().indexOf("HTTP/") != -1) {
					out.write(header); // 응답헤더 보내기
					
				}
				
				System.out.println("응답헤더 :\n" + new String(header));
				
				out.write(body);
				out.flush();
				
			} catch (IOException e) {
				// TODO: handle exception
				System.out.println("입출력 오류");
				e.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (IOException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
			
		}
	}
	
	public static void main(String[] args) {
		new MyHttpServer().startServer();
	}
	
}
