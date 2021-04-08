package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T12_DownloadServletTest extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String filename = "aaa.jpg";
		
		// 파일 다운로드 처리를 위한 응답헤더 정보 설정하기
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition" , "attachment; fileName=\"" + filename + "\"");
		
		/**
			Content-Disposition 헤어에 대해서
			
			1. response header에서 사용되는 경우 ex) 파일 다운로드
			
			Content-Disposition : inline(default)
			//파일 다운로드
			Content-Disposition: attatchment
			// filename.jpg
			Content-Disposition: attatchment; fileName="filename.jpg"
			
			2 muitlpart body를 위한 헤더정보로 사용되는 경우  ex) 파일 업로드
			- Content-Disposition : form-data;
			- Content-Disposition : form-data; name="fieldName"
			- Content-Disposition : form-data; name="fieldName"; filename
		 */
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
