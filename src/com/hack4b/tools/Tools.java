package com.hack4b.tools;

import java.util.regex.Pattern;

public class Tools {

	/**
	 * 处理用户提交的get页码参数，检测其合法性
	 * @param page
	 * @return
	 */
	public static String ProcessPage(String page){
		boolean f = Pattern.matches("[1-9]{1}[0-9]*", page);
		if(!f){
			return "1";
		}
		return page;
	}
	
}
