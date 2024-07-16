package com.shinhan.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@WebServlet("/member/mypage.do")
public class MemberInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
		request.getRequestDispatcher("memberInfo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberService mService = new MemberService();
		
		MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
		
		String address = request.getParameter("address");
		int result = mService.adUpdate(address, member.getM_id());
		String message = "1";
		
		if (result == 1) {
			String changeAddress = mService.checkAddress(member.getM_id());
			member.setM_address(address);
			
			response.getWriter().append(changeAddress);
			
		} else {
			message = "주소 변경 실패";
			response.getWriter().append(message);
		}
	}
}