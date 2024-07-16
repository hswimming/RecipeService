package com.shinhan.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/memberIdCheck.do")
public class MemberIdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		MemberService mService = new MemberService();
		int check = mService.checkId(id);
		
		String message = "1"; // 이미 사용중인 아이디
		
		if (check == 0) {
			message = "0"; // 사용 가능한 아이디
		} 
		
		response.getWriter().append(message);
	}
}