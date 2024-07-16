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
import com.shinhan.recipe.RecipeDTO;
import com.shinhan.recipe.RecipeService;

@WebServlet("/buy/buyRecipe.do")
public class BuyRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
		
		BuyService bService = new BuyService();
		List<BuyDTO> buyList = bService.selectAll(member.getM_id());
		
		String message = "";
		
		if (buyList.size() != 0) {
			request.setAttribute("buyList", buyList);
			
		} else {
			message = "주문내역이 없습니다";
			request.setAttribute("message", message);
		}
		
		request.getRequestDispatcher("buyList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
		
		int rNo = Integer.parseInt(request.getParameter("rNo")); // 구매하려는 번호
		
		MemberService mService = new MemberService();
		int money = mService.checkMoney(member.getM_id()); // 현재 잔액
		
		RecipeService rService = new RecipeService();
		List<RecipeDTO> recipeList = rService.selectRno(rNo); // 구매하려는 번호의 정보
		
		for (RecipeDTO recipe : recipeList) {
			int price = recipe.getR_price(); // 판매중인 가격
			
			BuyService bService = new BuyService();
			String message = "0";
			
			// 현재 잔액이 판매중인 가격보다 클 경우에만 구매 가능
			if (price <= money) {
				// 현재 잔액 - 판매중인 가격 (차감)
				int totalMoney = mService.plusMoney(money - price, member.getM_id());
				member.setM_money(totalMoney);
				
				int buy = bService.buyFood(rNo, member.getM_id());
				
				message = "구매 완료";
				response.getWriter().append(message);
				
			} else {
				message = "잔액이 부족합니다";
				response.getWriter().append(message);
			}
		}
	}
}