package com.shinhan.recipe;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/recipe/recipeSearch.do")
public class RecipeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 서버 실행 시 판매순 높은 순서대로 메인화면에 레시피 보여주기
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("recipe_search");

		RecipeService rService = new RecipeService();
		String message = "";

		// 키워드 없이 버튼만 눌렀을때
		if (search == "") {
			List<RecipeDTO> recipeAll = rService.selectAll();
			request.setAttribute("recipeAll", recipeAll);

		} else {
			List<RecipeDTO> searchRecipe = rService.selectRname(search);

			// 입력한 키워드 존재 확인
			if (searchRecipe.size() != 0) {
				request.setAttribute("searchRecipe", searchRecipe);

			} else {
				message = "조건에 해당하는 정보가 없습니다";
				request.setAttribute("message", message);
			}
		}

		request.getRequestDispatcher("recipeList.jsp").forward(request, response);

	}
}