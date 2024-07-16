package com.shinhan.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shinhan.member.MemberDTO;

@WebFilter("*.do")
public class LoginCheckFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		if (!req.getRequestURI().endsWith("login.do") 
			&& !req.getRequestURI().endsWith("memberInsert.do") 
			&& !req.getRequestURI().endsWith("memberIdCheck.do")) {
			
			session.setAttribute("lastRequest", req.getRequestURI());
			
			MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
			
			if (member == null) {
				String path = req.getServletContext().getContextPath();
				
				rep.sendRedirect(path + "/auth/login.do");
				return;
			}
		}
		
		chain.doFilter(request, response);
	}
}