package com.shinhan.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/memberInsert.do")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("memberInsert.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDTO member = makeMember(request);
		
		MemberService mService = new MemberService();
		int result = mService.memberInsert(member);
		
		request.setAttribute("signUp", result);
		response.sendRedirect("../index.jsp");
	}

	private MemberDTO makeMember(HttpServletRequest request) {
		MemberDTO member = new MemberDTO();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		member.setM_id(id);
		member.setM_pwd(pw);
		member.setM_phone(phone);
		member.setM_address(address);
		
		// System.out.println("회원가입 member : " + member);
		
		return member;
	}
}