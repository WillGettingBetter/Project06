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

		String tempPath = getServletContext().getRealPath("temp"); // �ļ���ʱ·��
		System.out.println(tempPath);

		/*String filePath = getServletContext().getRealPath("fileUp"); // �ļ���ŵ�·��
		System.out.println(filePath);*/
		String filePath ="E:\\Picture\\Image";
		try {
			DiskFileItemFactory diskFactory = new DiskFileItemFactory(); // ������
			// threshold ���ޡ��ٽ�ֵ����Ӳ�̻��� 1M
			diskFactory.setSizeThreshold(4 * 1024);
			// repository �����ң�����ʱ�ļ�Ŀ¼
			diskFactory.setRepository(new File(tempPath));

			ServletFileUpload upload = new ServletFileUpload(diskFactory);
			// ���������ϴ�������ļ���С 4M
			upload.setSizeMax(4 * 1024 * 1024);
			// ����HTTP������Ϣͷ
			List<FileItem> fileItems = upload.parseRequest(request);
			@SuppressWarnings("rawtypes")
			Iterator iter = fileItems.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.isFormField()) // �����ı�
				{
					// ��������
					// new String(item.getString().getBytes("ISO8859-1"),
					// "utf-8")
					System.out.println("������ı��ֶ����� ...");
					// request.getParameter("username")
					/*
					 * System.out.println("�ֶ������ǣ�" + item.getFieldName());
					 * System.out.println("�ֶ�ֵ�ǣ�" + item.getString());
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
					// �����Ǵ����ļ���
					System.out.println("�����ϴ����ļ� ...");
					// ��ʱ���ļ���������������·������ע��ӹ�һ��
					String filename = item.getName();
					System.out.println("�������ļ�����" + filename);
					// int index = filename.lastIndexOf("\\");
					// filename = filename.substring(index + 1,
					// filename.length());
					//
					filename = System.currentTimeMillis() + filename;
					long fileSize = item.getSize(); // ȡ���ļ��Ĵ�С
					// // �ļ�������Ϊ�� ���Ҵ�С��Ϊ0
					if ("".equals(filename) && fileSize == 0) {
						System.out.println("�ļ���Ϊ�� ...");
						return;
					}
					founderFile.setFilePic("fileUp/" + filename);
					//
					File uploadFile = new File(filePath + "/" + filename); // ���ǵĴ��·��
					item.write(uploadFile);

					int temp = services.insertPicToDB(founderFile);
					if (temp > 0) {
						request.getRequestDispatcher("hint.jsp").forward(
								request, response);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("ʹ�� fileupload ��ʱ�����쳣 ...");
			e.printStackTrace();
		}

	}

}
