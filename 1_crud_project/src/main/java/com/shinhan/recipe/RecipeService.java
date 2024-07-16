package com.shinhan.recipe;

import java.util.List;
import java.util.function.Predicate;

import com.shinhan.recipe_content.RecipeContentDAO;
import com.shinhan.recipe_content.RecipeContentDTO;

public class RecipeService {
	RecipeDAO recipeDAO = new RecipeDAO();
	RecipeContentDAO recipeContentDAO = new RecipeContentDAO();

	// 전체 레시피 조회
	public List<RecipeDTO> selectAll() {
		return recipeDAO.selectAll();
	}
	
	// 레시피 판매순 조회
	public List<RecipeDTO> rankAll() {
		return recipeDAO.rankAll();
	}

	// 레시피 검색
	public List<RecipeDTO> selectRname(String rName) {
		return recipeDAO.selectRname(rName);
	}
	
	// 특정 레시피 번호 정보 조회
		public List<RecipeDTO> selectRno(int rNo) {
			return recipeDAO.selectRno(rNo);
		}
}