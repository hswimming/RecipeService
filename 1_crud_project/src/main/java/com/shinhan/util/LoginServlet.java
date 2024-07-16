package com.shinhan.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shinhan.member.MemberDTO;
import com.shinhan.member.MemberService;

@WebServlet("/auth/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	List<String> user_list = new ArrayList<>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService mService = new MemberService();
		ServletContext app = getServletContext();
		
		// String path = request.getServletContext().getContextPath();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDTO member = mService.login(id, pw);
		String message = "1";
		
		if (member.getM_id().equals("-1")) {
			message = "-1"; // 계정 없음
			
		} else if (member.getM_id().equals("0")) {
			message = "0"; // 로그인 실패
			
		} else  {
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", member);
			
			message = "1"; // 로그인 성공
		}  
		
		response.getWriter().append(message);
	}
}