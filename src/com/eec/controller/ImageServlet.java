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
	// 定义验证码的使用的字母和数字
	private char[] charSeq = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
			.toCharArray();
	private int codeCount = 4;
	// 定义验证码的基线
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
		 * BufferedImage描述具有可以访问图像缓冲区的image Graphics类是所有图形上下文
		 */
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 得到描述图形的画笔
		Graphics g = image.getGraphics();
		// 将此图形上下文的当前颜色设置为指定颜色
		g.setColor(Color.white);
		//设置起点坐标，最后两个是宽和高
		g.fillRect(0, 0, width, height);
		// 设置边框的yans
		g.setColor(Color.black);
		g.drawRect(0, 0, width - 1, height - 1);
		// 生成验证码
		Random random = new Random();
		String randstr = "";
		for (int i = 1; i <= codeCount; i++) {
			// 随即获取验证码
			String rand = String
					.valueOf(charSeq[random.nextInt(charSeq.length)]);
			randstr = randstr + rand;

			g.setColor(Color.blue);
			g.setFont(new Font("宋体", Font.BOLD, 30));

			g.drawString(rand, codeX * i, codeY);
		}

		System.out.println("randstr=   " + randstr);
		// 把randstr放置到session中
		session.setAttribute("checkKey", randstr);
		session.setMaxInactiveInterval(30 * 60);

		// 设置干扰点
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);

			g.setColor(Color.GREEN);
			g.drawOval(x, y, 0, 0);
		}
		// 释放资源
		g.dispose();

		HttpServletResponse res = (HttpServletResponse) response;
		// 设定网页的到期时间，一旦过期则必须到服务器上重新调用
		res.setDateHeader("Expires", -1);
		// Cache-Control 指定请求和响应应遵循的缓存机制 no-cache指示请求或响应消息是不能缓存的
		res.setHeader("Cache-Control", "no-cache");
		// 用于设定禁止浏览器从本地缓存中调用页面内容，设定后一旦离开页面就无法从Cache中再调出
		res.setHeader("Pragma", "no-cache");

		ImageIO.write(image, "jpeg", response.getOutputStream());

	}

	public void init() throws ServletException {
		this.codeX = width / (codeCount + 2);
		this.codeY = height;
	}

}