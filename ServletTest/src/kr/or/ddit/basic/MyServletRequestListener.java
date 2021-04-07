package kr.or.ddit.basic;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyServletRequestListener implements ServletRequestListener, ServletRequestAttributeListener{

	// 생성자
	public MyServletRequestListener() {
		System.out.println("[MyServletRequestListener] 생성자 호출됨");
	}

	// ServletRequestListener
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("[MyServletRequestListener] "
				+ "requestDestroyed() 객체 생성되었음 =>" + sre.getServletRequest().toString());	
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("[MyServletRequestListener] "
				+ "requestInitialized() 객체 생성되었음 =>" + sre.getServletRequest().toString());	
	}
	
	// ServletRequestAttributeListener
	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		System.out.println("[MyServletRequestListener] "
				+ "attributeAdded() 호출됨 => " + srae.getName());
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		System.out.println("[MyServletRequestListener] "
				+ "attributeRemoved() 호출됨 => " + srae.getName());
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		System.out.println("[MyServletRequestListener] "
				+ "attributeReplaced() 호출됨 => " + srae.getName());
	}
	

}
