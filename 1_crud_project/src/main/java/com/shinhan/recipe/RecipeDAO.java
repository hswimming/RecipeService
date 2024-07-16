package com.shinhan.recipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shinhan.recipe_content.RecipeContentDAO;
import com.shinhan.recipe_content.RecipeContentDTO;
import com.shinhan.util.DBUtil;

public class RecipeDAO {
	
	Connection conn;
	Statement st;
	ResultSet rs;
	PreparedStatement pst;
	
	// 전체 레시피 조회
	public List<RecipeDTO> selectAll() {
		List<RecipeDTO> recipeList = new ArrayList<RecipeDTO>();
		
		String sql = "SELECT * FROM RECIPE R JOIN RECIPE_CONTENT RC ON (R.R_NO = RC.R_NO)";
		
		conn = DBUtil.dbConnection();
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while (rs.next()) {
				RecipeDTO recipeDTO = makeRecipe(rs);
				
				recipeList.add(recipeDTO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		
		return recipeList;
	}

	private RecipeDTO makeRecipe(ResultSet rs) throws SQLException {
		RecipeDTO recipeDTO = new RecipeDTO();

		recipeDTO.setR_no(rs.getInt("r_no"));
		recipeDTO.setR_name(rs.getString("r_name"));
		recipeDTO.setR_food(rs.getString("r_food"));
		recipeDTO.setRc_content(rs.getString("rc_content"));
		recipeDTO.setR_price(rs.getInt("r_price"));
		recipeDTO.setR_date(rs.getDate("r_date"));
		
		return recipeDTO;
	}

	// 레시피 판매순 조회 (베스트 3)
	public List<RecipeDTO> rankAll() {
		List<RecipeDTO> recipeList = new ArrayList<RecipeDTO>();
		
		String sql = "SELECT * FROM ( SELECT R.R_NO, "
					   + "R.R_NAME, "
					   + "R.R_FOOD, "
					   + "RC.RC_CONTENT, "
					   + "R.R_PRICE, "
					   + "R.R_DATE "
					   + "FROM RECIPE R JOIN RECIPE_CONTENT RC ON R.R_NO = RC.R_NO "
					   + "JOIN BUY B ON R.R_NO = B.R_NO "
					   + "GROUP BY R.R_NO, R.R_NAME, R.R_FOOD, RC.RC_CONTENT, R.R_PRICE, R.R_DATE "
					   + "ORDER BY COUNT(B.R_NO) DESC ) "
					   + "WHERE ROWNUM <= 3";

		conn = DBUtil.dbConnection();

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				RecipeDTO recipeDTO = makeRecipe(rs);
				
				recipeList.add(recipeDTO);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}

		return recipeList;
	}
	
	// 레시피 이름 검색
	public List<RecipeDTO> selectRname(String rName) {
		List<RecipeDTO> recipeList = new ArrayList<RecipeDTO>();
		
		String sql = "SELECT * FROM RECIPE R JOIN RECIPE_CONTENT RC ON (R.R_NO = RC.R_NO) WHERE R_NAME LIKE '%' || ? || '%'";
		
		conn = DBUtil.dbConnection();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, rName);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				RecipeDTO recipeDTO = makeRecipe(rs);
				
				recipeList.add(recipeDTO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		
		return recipeList;
	}
	
	// 특정 레시피 번호 정보 조회
		public List<RecipeDTO> selectRno(int rNo) {
			List<RecipeDTO> recipeList = new ArrayList<RecipeDTO>();
			
			String sql = "SELECT * FROM RECIPE R JOIN RECIPE_CONTENT RC ON (R.R_NO = RC.R_NO) "
						   + "WHERE R.R_NO = ?";
			
			conn = DBUtil.dbConnection();
			
			try {
				pst = conn.prepareStatement(sql);
				pst.setInt(1, rNo);
				
				rs = pst.executeQuery();
				
				while (rs.next()) {
					RecipeDTO recipeDTO = makeRecipe(rs);
					
					recipeList.add(recipeDTO);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			} finally {
				DBUtil.dbDisconnect(conn, pst, rs);
			}
			
			return recipeList;
		}
}