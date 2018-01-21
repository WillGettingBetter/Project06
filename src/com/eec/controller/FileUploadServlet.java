package com.eec.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.eec.entity.FounderFile;
import com.eec.services.FounderFileServices;
import com.eec.services.impl.FounderFileServicesImpl;

@SuppressWarnings("serial")
public class FileUploadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		FounderFileServices services = new FounderFileServicesImpl();
		FounderFile founderFile = new FounderFile();

		String tempPath = getServletContext().getRealPath("temp"); // 文件临时路径
		System.out.println(tempPath);

		/*String filePath = getServletContext().getRealPath("fileUp"); // 文件存放的路径
		System.out.println(filePath);*/
		String filePath ="E:\\Picture\\Image";
		try {
			DiskFileItemFactory diskFactory = new DiskFileItemFactory(); // 工厂类
			// threshold 极限、临界值，即硬盘缓存 1M
			diskFactory.setSizeThreshold(4 * 1024);
			// repository 贮藏室，即临时文件目录
			diskFactory.setRepository(new File(tempPath));

			ServletFileUpload upload = new ServletFileUpload(diskFactory);
			// 设置允许上传的最大文件大小 4M
			upload.setSizeMax(4 * 1024 * 1024);
			// 解析HTTP请求消息头
			List<FileItem> fileItems = upload.parseRequest(request);
			@SuppressWarnings("rawtypes")
			Iterator iter = fileItems.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.isFormField()) // 处理文本
				{
					// 处理乱码
					// new String(item.getString().getBytes("ISO8859-1"),
					// "utf-8")
					System.out.println("处理表单文本字段内容 ...");
					// request.getParameter("username")
					/*
					 * System.out.println("字段名字是：" + item.getFieldName());
					 * System.out.println("字段值是：" + item.getString());
					 */
					if ("filename".equals(item.getFieldName())) {
						founderFile.setFileName(new String(item.getString()
								.getBytes("ISO-8859-1"), "UTF-8"));
					}
					if ("filedes".equals(item.getFieldName())) {
						founderFile.setFileDes(new String(item.getString()
								.getBytes("ISO-8859-1"), "UTF-8"));
					}

				} else {
					// 以下是处理文件域
					System.out.println("处理上传的文件 ...");
					// 此时的文件名包含了完整的路径，得注意加工一下
					String filename = item.getName();
					System.out.println("完整的文件名：" + filename);
					// int index = filename.lastIndexOf("\\");
					// filename = filename.substring(index + 1,
					// filename.length());
					//
					filename = System.currentTimeMillis() + filename;
					long fileSize = item.getSize(); // 取出文件的大小
					// // 文件名不能为空 并且大小不为0
					if ("".equals(filename) && fileSize == 0) {
						System.out.println("文件名为空 ...");
						return;
					}
					founderFile.setFilePic("fileUp/" + filename);
					//
					File uploadFile = new File(filePath + "/" + filename); // 真是的存放路径
					item.write(uploadFile);

					int temp = services.insertPicToDB(founderFile);
					if (temp > 0) {
						request.getRequestDispatcher("hint.jsp").forward(
								request, response);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("使用 fileupload 包时发生异常 ...");
			e.printStackTrace();
		}

	}

}
