package com.manager.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manager.entity.Manager;

public class ManagerLoginFilter implements Filter{
	private FilterConfig filterConfig;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig= filterConfig;
	}
	
	@Override
	public void destroy() {
		filterConfig= null;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req= (HttpServletRequest) request;
		HttpServletResponse resp= (HttpServletResponse) response;
		
		// get Session
		HttpSession session= req.getSession();
		// 判斷登入
		
		Object account= session.getAttribute("manager");
		
//		Manager manager= (Manager)session.getAttribute("manager");
//		Object account= manager.getAccount();
		
		if (account== null) {
			session.setAttribute("location", req.getRequestURI());
			resp.sendRedirect(req.getContextPath()+ "/html/manager_login.html");
			return;
		}else {
			chain.doFilter(request, response);
		}
		
	}
	
}
