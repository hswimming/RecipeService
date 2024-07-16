package com.shinhan.buy;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shinhan.member.MemberDTO;
import com.shinhan.member.MemberService;

@WebServlet("/buy/buyDelete.do")
public class BuyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
		
		int bNo = Integer.parseInt(request.getParameter("bNo")); // 삭제하려는 구매번호
		
		MemberService mService = new MemberService();
		int money = mService.checkMoney(member.getM_id()); // 현재 잔액
		
		BuyService bService = new BuyService();
		List<BuyDTO> buyList = bService.selectBno(bNo); // 구매번호의 정보
		
		for (BuyDTO buy : buyList) {
			int price = buy.getR_price(); // 구매했던 가격
			
			int result = bService.deleteFood(bNo, member.getM_id());
			String message = "0";
			
			if (result == 1) {
				// 삭제 성공 시 현재 잔액 + 구매했던 가격 (환불)
				int totalMoney = mService.plusMoney(money + price, member.getM_id());
				member.setM_money(totalMoney);
				
				message = "구매 취소";
				response.getWriter().append(message);
				
			} else {
				message = "오류";
				response.getWriter().append(message);
			}
		}
	}
}