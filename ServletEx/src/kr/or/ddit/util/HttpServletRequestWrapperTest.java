package kr.or.ddit.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class HttpServletRequestWrapperTest extends HttpServletRequestWrapper{

	public HttpServletRequestWrapperTest(HttpServletRequest request) {
		super(request);	// HttpServletRequest 를 기반으로 동작
		
	}

}
