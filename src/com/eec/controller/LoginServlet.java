package com.eec.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eec.dao.FounderUserDao;
import com.eec.dao.impl.FounderUserDaoImpl;
import com.eec.entity.FounderUser;
import com.eec.services.LoginServices;
import com.eec.services.impl.LoginServicesImpl;
import com.eec.util.MD5Util;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(getServletContext().getInitParameter(
				"EncodingMethod"));
		response.setCharacterEncoding(getServletContext().getInitParameter(
				"EncodingMethod"));

		String flag = request.getParameter("flag");
		if ("login".equals(flag)) {
			String useryzm = request.getParameter("useryzm");
			System.out.println(useryzm);
			HttpSession session = request.getSession();
			String sessionyzm = (String) (session.getAttribute("checkKey"));
			System.out.println(sessionyzm + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			FounderUser user = null;
			FounderUser result_User = null;
			if (sessionyzm.equals(useryzm)) {
				String username = request.getParameter("username");
				String userpsw = request.getParameter("password");

				user = new FounderUser(-1, username, userpsw, null, null, null);
				LoginServices login = new LoginServicesImpl();
				result_User = login.judgeLoginState(user);
			}
			if (result_User != null) {
				/* 成功 */
				request.getSession().setAttribute("user", result_User);
				request.getRequestDispatcher("/page/home.jsp").forward(request,
						response);
			} else {
				response.sendRedirect("login.jsp");
			}
		} else if ("exit".equals(flag)) {
			/* 退出操作 */
			request.getSession().removeAttribute("user");
			response.sendRedirect("login.jsp");
		} else if ("register".equals(flag)) {
			/* 注册 */
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			int userid = Integer.parseInt(request.getParameter("userid"));
			String sex = request.getParameter("sex");
			String usertel = request.getParameter("usertel");

			String userbirth = request.getParameter("userbirth");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date = null;
			FounderUser user = null;
			try {
				date = sdf.parse(userbirth);
				java.sql.Date time = new java.sql.Date(date.getTime());
				user = new FounderUser(userid, username, MD5Util.MD5(password),
						sex, usertel, time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			LoginServices loginServices = new LoginServicesImpl();
			boolean bool = loginServices.registerByUser(user);
			if (bool) {
				System.out.println("成功插入数据");
			} else {
				System.out.println("失败");
			}
			response.sendRedirect("login.jsp");
		} else if ("showall".equals(flag)) {
			FounderUserDao dao = new FounderUserDaoImpl();
			List<FounderUser> userlist = dao.getAllUser();
			request.setAttribute("userlist", userlist);
			request.getRequestDispatcher("/page/showinfo.jsp").forward(request,
					response);
		} else if ("check".equals(flag)) {
			String username = request.getParameter("username");
			System.out.println(username);
			FounderUserDao dao = new FounderUserDaoImpl();
			PrintWriter pw = response.getWriter();
			FounderUser user = dao.getUserByName(username);
			if (user != null) {
				pw.write("ok");
			} else {
				pw.write("no");
			}
			pw.flush();
			pw.close();
		}
		if ("regis".equals(flag)) {
			String username = request.getParameter("username");
			String userpsw = request.getParameter("password");
			String userid = request.getParameter("userid");
			String usersex = request.getParameter("usersex");
			String usertel = request.getParameter("usertel");
			String userbirth = request.getParameter("userbirth");

			System.out.println(username + "\t" + userpsw + "\t" + userid + "\t"
					+ usersex + "\t" + usertel + "\t" + userbirth);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date = null;
			FounderUser user = null;
			try {
				date = sdf.parse(userbirth);
				java.sql.Date time = new java.sql.Date(date.getTime());
				int id = Integer.parseInt(userid);
				user = new FounderUser(id, username, MD5Util.MD5(userpsw),
						usersex, usertel, time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			LoginServices loginServices = new LoginServicesImpl();
			boolean bool = loginServices.registerByUser(user);
			PrintWriter out = response.getWriter();
			if (bool) {
				out.write("Ok");
			} else {
				out.write("No");
			}
			
		} else if ("checkId".equals(flag)) {
			String userid = request.getParameter("userid");
			System.out.println(userid + "~~~~~~~~~~~");
			int id = Integer.valueOf(userid);
			FounderUserDao dao = new FounderUserDaoImpl();
			boolean bool = dao.getUserById(id);
			PrintWriter out = response.getWriter();
			if (bool) {
				out.write("OK");
			} else {
				out.write("No");
			}
		}
	}

}
