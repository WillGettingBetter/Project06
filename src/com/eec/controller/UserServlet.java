package com.eec.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eec.entity.FounderUser;
import com.eec.services.UserServices;
import com.eec.services.impl.UserServicesImpl;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class UserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String flag = request.getParameter("flag");
		if ("deleteuser".equals(flag)) {
			/**
			 * 进入删除用户界面
			 */
			UserServices userServices = new UserServicesImpl();
			List<FounderUser> userlist = userServices.getAllUser();
			request.setAttribute("userlist", userlist);
			request.getRequestDispatcher("/page/deleteUser.jsp").forward(
					request, response);
		} else if ("dodelete".equals(flag)) {
			/**
			 * 删除用户操作
			 */

		} else if ("getResult".equals(flag)) {
			/**
			 * 执行分页技术
			 */
			int currentPage = Integer.parseInt(request
					.getParameter("currentPage"));
			int pageCount = Integer.parseInt(request.getParameter("pageCount"));
			UserServices userServices = new UserServicesImpl();
			List<FounderUser> userlist = userServices.getPageResult(
					currentPage, pageCount);
			Gson gson = new Gson();
			String str = gson.toJson(userlist);
			PrintWriter out = response.getWriter();
			out.write(str);
			out.flush();
			out.close();
		} else if ("getAllPage".equals(flag)) {
			/* 查询到所有用户信息 */
			int pageCount = Integer.parseInt(request.getParameter("pageCount"));
			UserServices userServices = new UserServicesImpl();
			List<FounderUser> userlist = userServices.getAllUser();
			int temp = 0;
			if (userlist.size() % pageCount == 0) {
				temp = userlist.size() / pageCount;
			} else {
				temp = userlist.size() / pageCount + 1;
			}
			PrintWriter out = response.getWriter();
			out.write(temp + "");
			out.flush();
			out.close();
		}
	}

}
