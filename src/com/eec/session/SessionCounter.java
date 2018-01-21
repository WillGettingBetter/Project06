package com.eec.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounter implements HttpSessionListener {

	private static int activeSessions = 0;

	@Override
	// session创建时执行
	public void sessionCreated(HttpSessionEvent arg0) {
		HttpSession session=arg0.getSession();
		session.setMaxInactiveInterval(2);
		System.out.println("增加一次会话");
		activeSessions++;
	}

	@Override
	// session销毁时执行
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("销毁一次会话");
		if (activeSessions > 0) {
			activeSessions--;
		}
	}

	// 统计在线的session个数，即在线人数
	public static int getActiveSessions() {
		return activeSessions;
	}

}
