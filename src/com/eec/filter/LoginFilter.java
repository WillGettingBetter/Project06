package com.eec.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// �ж��Ƿ������û���¼
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		Object user = request.getSession().getAttribute("user");
		if (user != null) {
			arg2.doFilter(arg0, arg1);
		} else {
			/* û�е�¼��ȥ��¼ҳ�� */
			response.sendRedirect("../login.jsp");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}