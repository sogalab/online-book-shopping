package com.fanyongliang.book;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ValidateCode {
	/**
	 * 创建logger控制台日志显示对象
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(UserLoginController.class);

	@RequestMapping("code")
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//创建空白图片
		BufferedImage image = new BufferedImage(100, 30,
				BufferedImage.TYPE_INT_RGB);
		//获取图片画笔
		Graphics g = image.getGraphics();
		Random r = new Random();
		//设置画笔颜色
		g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		//绘制矩形的背景
		g.fillRect(0, 0, 100, 30);
		//调用自定义的方法，获取长度为5的字母数字组合的字符串
		String number = getNumber(5);
		logger.info("本次验证码(" + new Date() + "):" + number);
		HttpSession session = request.getSession();
		session.setAttribute("validatecode", number);
		g.setColor(new Color(0, 0, 0));
		g.setFont(new Font(null, Font.BOLD, 24));
		//设置颜色字体后，绘制字符串
		g.drawString(number, 5, 25);
		//绘制干扰线
		for (int i = 0; i < 40; i++) {
			g.setColor(new Color(r.nextInt(255), r.nextInt(255),
					r.nextInt(255), r.nextInt(255)));
			g.drawLine(r.nextInt(100), r.nextInt(30), r.nextInt(100),
					r.nextInt(30));
		}
		response.setContentType("image/jpeg");
		OutputStream ops = response.getOutputStream();
		ImageIO.write(image, "jpeg", ops);
		ops.close();
	}

	private String getNumber(int size) {
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String number = "";
		Random r = new Random();
		for (int i = 0; i < size; i++) {
			number += str.charAt(r.nextInt(str.length()));
		}
		return number;
	}

}