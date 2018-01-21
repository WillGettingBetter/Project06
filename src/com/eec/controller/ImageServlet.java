package com.eec.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class ImageServlet extends HttpServlet {

	private int width = 85;
	private int height = 50;
	// ������֤���ʹ�õ���ĸ������
	private char[] charSeq = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
			.toCharArray();
	private int codeCount = 4;
	// ������֤��Ļ���
	private int codeX;
	private int codeY;

	public ImageServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		/*
		 * BufferedImage�������п��Է���ͼ�񻺳�����image Graphics��������ͼ��������
		 */
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// �õ�����ͼ�εĻ���
		Graphics g = image.getGraphics();
		// ����ͼ�������ĵĵ�ǰ��ɫ����Ϊָ����ɫ
		g.setColor(Color.white);
		//����������꣬��������ǿ�͸�
		g.fillRect(0, 0, width, height);
		// ���ñ߿��yans
		g.setColor(Color.black);
		g.drawRect(0, 0, width - 1, height - 1);
		// ������֤��
		Random random = new Random();
		String randstr = "";
		for (int i = 1; i <= codeCount; i++) {
			// �漴��ȡ��֤��
			String rand = String
					.valueOf(charSeq[random.nextInt(charSeq.length)]);
			randstr = randstr + rand;

			g.setColor(Color.blue);
			g.setFont(new Font("����", Font.BOLD, 30));

			g.drawString(rand, codeX * i, codeY);
		}

		System.out.println("randstr=   " + randstr);
		// ��randstr���õ�session��
		session.setAttribute("checkKey", randstr);
		session.setMaxInactiveInterval(30 * 60);

		// ���ø��ŵ�
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);

			g.setColor(Color.GREEN);
			g.drawOval(x, y, 0, 0);
		}
		// �ͷ���Դ
		g.dispose();

		HttpServletResponse res = (HttpServletResponse) response;
		// �趨��ҳ�ĵ���ʱ�䣬һ����������뵽�����������µ���
		res.setDateHeader("Expires", -1);
		// Cache-Control ָ���������ӦӦ��ѭ�Ļ������ no-cacheָʾ�������Ӧ��Ϣ�ǲ��ܻ����
		res.setHeader("Cache-Control", "no-cache");
		// �����趨��ֹ������ӱ��ػ����е���ҳ�����ݣ��趨��һ���뿪ҳ����޷���Cache���ٵ���
		res.setHeader("Pragma", "no-cache");

		ImageIO.write(image, "jpeg", response.getOutputStream());

	}

	public void init() throws ServletException {
		this.codeX = width / (codeCount + 2);
		this.codeY = height;
	}

}