package com.shinhan.buy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shinhan.member.MemberDTO;
import com.shinhan.recipe.RecipeDTO;
import com.shinhan.util.DBUtil;

public class BuyDao {
	
	Connection conn;
	Statement st;
	ResultSet rs;
	PreparedStatement pst;

	// 레시피 구매
	public int buyFood(int food, String mId) {
		int result = 0;
		
		String sql = "INSERT INTO BUY "
					   + "VALUES(BUY_SEQ.NEXTVAL, (SELECT M_CODE FROM MEMBER WHERE M_ID = ?), ?, DEFAULT)";
		
		conn = DBUtil.dbConnection();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, mId);
			pst.setInt(2, food);
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		
		return result;
	}

	// 구매내역 조회
	public List<BuyDTO> selectAll(String mId) {
		List<BuyDTO> buyList = new ArrayList<BuyDTO>();
		
		String sql = "SELECT B.B_NO, "
					   + "R.R_NAME, "
					   + "R.R_FOOD, "
					   + "RC.RC_CONTENT, "
					   + "R_PRICE "
					   + "FROM BUY B JOIN RECIPE R ON B.R_NO = R.R_NO "
					   + "JOIN MEMBER M ON B.M_CODE = M.M_CODE "
					   + "JOIN RECIPE_CONTENT RC ON R.R_NO = RC.R_NO "
					   + "WHERE M.M_ID = ? "
					   + "ORDER BY 1 DESC";
		
		conn = DBUtil.dbConnection();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, mId);
			
			rs = pst.executeQuery();
			
			while (rs.next()) {
				BuyDTO buyDTO = mekeBuy(rs);
				
				buyList.add(buyDTO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		
		return buyList;
	}

	private BuyDTO mekeBuy(ResultSet rs) throws SQLException {
		BuyDTO buyDTO = new BuyDTO();
		
		buyDTO.setB_no(rs.getInt("b_no"));
		buyDTO.setR_name(rs.getString("r_name"));
		buyDTO.setR_food(rs.getString("r_food"));
		buyDTO.setRc_content(rs.getString("rc_content"));
		buyDTO.setR_price(rs.getInt("r_price"));
		
		return buyDTO;
	}
	
	// 특정 번호의 구매내역 조회
		public List<BuyDTO> selectBno(int bNo) {
			List<BuyDTO> buyList = new ArrayList<BuyDTO>();
			
			String sql = "SELECT * "
						   + "FROM BUY B JOIN RECIPE R ON B.R_NO = R.R_NO "
						   + "JOIN MEMBER M ON B.M_CODE = M.M_CODE "
						   + "JOIN RECIPE_CONTENT RC ON R.R_NO = RC.R_NO "
						   + "WHERE B.B_NO = ?";

			conn = DBUtil.dbConnection();

			try {
				pst = conn.prepareStatement(sql);
				pst.setInt(1, bNo);
				
				rs = pst.executeQuery();

				while (rs.next()) {
					BuyDTO buyDTO = mekeBuy(rs);

					buyList.add(buyDTO);
				}

			} catch (SQLException e) {
				e.printStackTrace();

			} finally {
				DBUtil.dbDisconnect(conn, pst, rs);
			}

			return buyList;
		}

	// 구매내역 삭제
	public int deleteFood(int food, String mId) {
		int result = 0;
		
		String sql = "DELETE FROM BUY "
					   + "WHERE B_NO = (SELECT B.B_NO "
					   + "FROM BUY B JOIN MEMBER M ON B.M_CODE = M.M_CODE "
					   + "WHERE M.M_ID = ? AND B.B_NO = ?)";
		
		conn = DBUtil.dbConnection();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, mId);
			pst.setInt(2, food);
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		
		return result;
	}
}