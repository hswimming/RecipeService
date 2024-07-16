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

@WebServlet("/recipe/BestRecipe")
public class RecipeBestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		RecipeService rService = new RecipeService();
		List<RecipeDTO> bestRecipe = rService.rankAll();

		JSONObject bestList = new JSONObject();
		JSONArray bestArr = new JSONArray();

		for (RecipeDTO recipe : bestRecipe) {
			JSONObject obj = new JSONObject();

			obj.put("r_no", recipe.getR_no());
			obj.put("r_name", recipe.getR_name());
			obj.put("r_food", recipe.getR_food());
			obj.put("r_price", recipe.getR_price());
			obj.put("r_date", recipe.getR_date().toString());
			obj.put("rc_content", recipe.getRc_content());

			bestArr.add(obj);
		}

		bestList.put("bestList", bestArr);
		String json = bestList.toJSONString();

		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}