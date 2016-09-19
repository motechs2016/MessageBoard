package com.hack4b.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hack4b.model.User;
import com.sun.net.httpserver.Filter.Chain;

/**
 * 登录功能过滤器
 * @author lejie
 *
 */
//@WebFilter("/admin/*")
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)arg0;
		HttpServletResponse resp = (HttpServletResponse)arg1;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if(user==null){  //如果用户没有登录
			resp.sendRedirect("admin/login.jsp");
		}
		String uri = req.getRequestURI();  //获取请求路径
		if(uri.contains("login.jsp")){
			arg2.doFilter(req, resp);
		}
		
		arg2.doFilter(req, resp);
	}
	@Override
	public void destroy() {
		
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	
	}

}
