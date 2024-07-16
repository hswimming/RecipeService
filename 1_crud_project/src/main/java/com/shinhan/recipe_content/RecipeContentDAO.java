package com.shinhan.recipe_content;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shinhan.util.DBUtil;

public class RecipeContentDAO {
	Connection conn; // DB 연결
	Statement st; // SQL 문장 받기
	ResultSet rs; // 결과 반환
	PreparedStatement pst; // 바인딩 변수 지원
	
	// 전체 레시피 내용 조회
	public List<RecipeContentDTO> selectAll() {
		List<RecipeContentDTO> recipeContentList = new ArrayList<RecipeContentDTO>();
		
		String sql = "SELECT * FROM RECIPE_CONTENT";
		
		conn = DBUtil.dbConnection();
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while (rs.next()) {
				RecipeContentDTO recipeContentDTO = makerecipeContent(rs);
				
				recipeContentList.add(recipeContentDTO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		
		return recipeContentList;
	}

	private RecipeContentDTO makerecipeContent(ResultSet rs) throws SQLException {
		RecipeContentDTO recipeContentDTO = new RecipeContentDTO();
		
		recipeContentDTO.setRc_content(rs.getString("rc_content"));
		recipeContentDTO.setR_no(rs.getInt("r_no"));
		
		return recipeContentDTO;
	}
}