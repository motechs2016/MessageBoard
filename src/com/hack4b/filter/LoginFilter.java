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
@WebFilter("/admin/*")
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		String uri = req.getRequestURI();
		if(!uri.contains("login.jsp")){  //白名单
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("user");
			if(user==null){  //用户没有登录
				resp.sendRedirect("login.jsp");
			}
		}
		chain.doFilter(request, response);
	}
	@Override
	public void destroy() {
		
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	
	}

}
