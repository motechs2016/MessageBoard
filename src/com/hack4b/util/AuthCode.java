package com.hack4b.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 验证码工具类(备用)
 * @author lejie
 *
 */
public class AuthCode{
	// 用于获取四位随机数
	private char mapTable[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	// 生成验证码,并返回随机生成的数字
	public String getEnsure(int width, int height, OutputStream os) {
		if (width <= 0)
			width = 60;
		if (height <= 0)
			height = 20;

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 获取图形上下文
		Graphics g = image.getGraphics();

		// 设定背景色
		g.setColor(new Color(0xDCCCCC));
		g.fillRect(0, 0, width, height);

		// 画边框
		g.setColor(Color.black);
		g.drawRect(0, 0, width - 1, height - 1);

		// 取随机产生的认证码
		String strEnsure = "";

		// 4代表4位验证码
		for (int i = 0; i < 4; ++i) {
			strEnsure += mapTable[(int) (mapTable.length * Math.random())];
		}

		// 将认证码显示到图象中
		g.setColor(Color.red);
		g.setFont(new Font("Atlantic Inline", Font.PLAIN, 14));

		// 画的具体坐标
		String str = strEnsure.substring(0, 1);
		g.drawString(str, 8, 14);
		str = strEnsure.substring(1, 2);
		g.drawString(str, 20, 15);
		str = strEnsure.substring(2, 3);
		g.drawString(str, 35, 18);
		str = strEnsure.substring(3, 4);
		g.drawString(str, 45, 15);

		// 释放图形上下文
		g.dispose();

		try {
			// 输出图象到页面
			ImageIO.write(image, "JPEG", os);
		} catch (IOException e) {
			return "";
		}

		return strEnsure; // 返回生成的随机数
	}
}
