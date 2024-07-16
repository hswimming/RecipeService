package com.shinhan.member;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.RepaintManager;

@WebServlet("/member/plusMoney.do")
public class MemberMoneyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberService mService = new MemberService();
		
		MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
		
		int money = mService.checkMoney(member.getM_id());
		int plusMoney = Integer.parseInt(request.getParameter("plusMoney"));
		int totalMoney = mService.plusMoney(money + plusMoney, member.getM_id());
		
		member.setM_money(totalMoney);
		
//		System.out.println("money : " + money);
//		System.out.println("plusMoney : " + plusMoney);
//		System.out.println("totalMoney : " + totalMoney);
		
		request.getRequestDispatcher("memberMoney.jsp").forward(request, response);
	}
}