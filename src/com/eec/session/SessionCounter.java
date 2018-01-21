package com.eec.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounter implements HttpSessionListener {

	private static int activeSessions = 0;

	@Override
	// session����ʱִ��
	public void sessionCreated(HttpSessionEvent arg0) {
		HttpSession session=arg0.getSession();
		session.setMaxInactiveInterval(2);
		System.out.println("����һ�λỰ");
		activeSessions++;
	}

	@Override
	// session����ʱִ��
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("����һ�λỰ");
		if (activeSessions > 0) {
			activeSessions--;
		}
	}

	// ͳ�����ߵ�session����������������
	public static int getActiveSessions() {
		return activeSessions;
	}

}
