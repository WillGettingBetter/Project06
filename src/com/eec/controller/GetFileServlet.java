package com.eec.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eec.entity.AuxiliaryEntity;
import com.eec.entity.FounderFile;
import com.eec.entity.FounderMessage;
import com.eec.entity.FounderUser;
import com.eec.services.FounderFileServices;
import com.eec.services.impl.FounderFileServicesImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@SuppressWarnings("serial")
public class GetFileServlet extends HttpServlet {

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
		if ("getInfo".equals(flag)) {
			int currentPage = Integer.parseInt(request
					.getParameter("currentPage"));
			int pageCount = Integer.parseInt(request.getParameter("pageCount"));
			int fileId = Integer.parseInt(request.getParameter("fileid"));
			String fileName = request.getParameter("filename");
			String fileDes = request.getParameter("filedes");
			double filePriceMin = Double.parseDouble(request
					.getParameter("filepricemin"));
			double filePriceMax = Double.parseDouble(request
					.getParameter("filepricemax"));

			AuxiliaryEntity ae = new AuxiliaryEntity();
			ae.setCurrentPage(currentPage);
			ae.setPageCount(pageCount);
			ae.setFileId(fileId);

			// 依赖services
			FounderFileServices ffs = new FounderFileServicesImpl();
			List<FounderFile> fileList = ffs.getFileByAE(currentPage,
					pageCount, fileId, fileName, fileDes, filePriceMin,
					filePriceMax);
			Gson gson = new Gson();
			String strList = gson.toJson(fileList);
			PrintWriter out = response.getWriter();
			out.write(strList);
			out.flush();
			out.close();
		} else if ("gettotalpages".equals(flag)) {
			/* 得到总页数 */
			int pageCount = Integer.parseInt(request.getParameter("pageCount"));
			int fileId = Integer.parseInt(request.getParameter("fileid"));
			String fileName = request.getParameter("filename");
			String fileDes = request.getParameter("filedes");
			double filePriceMin = Double.parseDouble(request
					.getParameter("filepricemin"));
			double filePriceMax = Double.parseDouble(request
					.getParameter("filepricemax"));
			// 依赖services层
			FounderFileServices services = new FounderFileServicesImpl();
			int totalPages = services.getTotalPages(pageCount, fileId,
					fileName, fileDes, filePriceMin, filePriceMax);
			PrintWriter out = response.getWriter();
			out.write(totalPages + "");
			out.flush();
			out.close();
		} else if ("gotogoods".equals(flag)) {
			// 得到商品详细信息
			int fileid = Integer.parseInt(request.getParameter("fileid"));
			/* 进入业务层，services */
			FounderFileServices services = new FounderFileServicesImpl();
			FounderFile founderfile = services.getFileInfo(fileid);
			request.setAttribute("fileInfo", founderfile);
			request.getRequestDispatcher("page/fileinfo.jsp").forward(request,
					response);
		} else if ("writeMessage".equals(flag)) {
			int fileid = Integer.parseInt(request.getParameter("fileid"));
			String messageContent = request.getParameter("messageContent");
			/* 插入留言信息到数据库中 */
			FounderUser user = (FounderUser) (request.getSession()
					.getAttribute("user"));

			FounderMessage fm = new FounderMessage(-1, user.getUserid(),
					user.getUsername(), fileid, messageContent, null);
			/* 找业务层，插入数据库 */
			FounderFileServices services = new FounderFileServicesImpl();
			boolean result = services.insertMsg(fm);
			PrintWriter out = response.getWriter();
			if (result) {
				out.write("success");
			} else {
				out.write("fail");
			}
			out.flush();
			out.close();
		} else if ("getMessage".equals(flag)) {
			/* 从数据库中提取留言信息并写到前端页面中 */
			int fileid = Integer.parseInt(request.getParameter("fileid"));
			/* 到业务层 */
			FounderFileServices services = new FounderFileServicesImpl();
			List<FounderMessage> fmList = services.getFMById(fileid);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			String str = gson.toJson(fmList);
			PrintWriter out = response.getWriter();
			out.write(str);
			out.flush();
			out.close();
		} else if ("getPages".equals(flag)) {
			int fileid = Integer.parseInt(request.getParameter("fileid"));
			int currentPage = Integer.parseInt(request
					.getParameter("currentPage"));
			int pageCount = Integer.parseInt(request.getParameter("pageCount"));

			FounderFileServices services = new FounderFileServicesImpl();
			List<FounderMessage> fmList = services.getPageResultByData(fileid,
					currentPage, pageCount);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			String str = gson.toJson(fmList);
			PrintWriter out = response.getWriter();
			out.write(str);
			out.flush();
			out.close();
		} else if ("getTotalPages".equals(flag)) {
			/* 获取总页数 */
			int fileid = Integer.parseInt(request.getParameter("fileid"));
			int pageCount = Integer.parseInt(request.getParameter("pageCount"));
			/* 找到业务层 */
			FounderFileServices services = new FounderFileServicesImpl();
			int totalPages = services.getTotalPages(fileid, pageCount);
			PrintWriter out = response.getWriter();
			out.write(totalPages + "");
			out.flush();
			out.close();
		}
	}

}
