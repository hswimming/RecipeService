package com.shinhan.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shinhan.util.DBUtil;

public class MemberDAO {
	Connection conn; // DB 연결
	Statement st; // SQL 문장 받기
	ResultSet rs; // 결과 반환
	PreparedStatement pst; // 바인딩 변수 지원
	
	// 여러가지 sql문 가져와야 할 때
	// 사용 전에 setAutoCommit(false); // 자동 저장 되면 안됨
	// addBatch()
	// executeBatch()
	// commit()
	
	// 모든 사용자의 정보
	public List<MemberDTO> selectAll() {
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		
		String sql = "SELECT * FROM MEMBER";
		
		conn = DBUtil.dbConnection();
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while (rs.next()) {
				MemberDTO memberDTO = mekeMember(rs);
				
				memberList.add(memberDTO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		
		return memberList;
	}
	
	private MemberDTO mekeMember(ResultSet rs) throws SQLException {
		MemberDTO memberDTO = new MemberDTO();
		
		memberDTO.setM_code(rs.getInt("m_code"));
		memberDTO.setM_id(rs.getString("m_id"));
		memberDTO.setM_pwd(rs.getString("m_pwd"));
		memberDTO.setM_phone(rs.getString("m_phone"));
		memberDTO.setM_address(rs.getString("m_address"));
		memberDTO.setM_money(rs.getInt("m_money"));
		
		return memberDTO;
	}

	// 회원가입
	public int memberInsert(MemberDTO memberDTO) {
		int result = 0;
		
		String sql = "INSERT INTO MEMBER VALUES(MEMBER_SEQ.NEXTVAL, ?, ?, ?, ?, DEFAULT)";
		
		conn = DBUtil.dbConnection();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, memberDTO.getM_id());
			pst.setString(2, memberDTO.getM_pwd());
			pst.setString(3, memberDTO.getM_phone());
			pst.setString(4, memberDTO.getM_address());
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		
		return result;
	}
	
	// 로그인
	public MemberDTO login(String mId, String mPwd) {
		String sql = "SELECT * FROM MEMBER WHERE M_ID = ?";
		MemberDTO member = new MemberDTO();
		
		conn = DBUtil.dbConnection();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, mId);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				
				// 로그인 성공
				if (rs.getString("m_pwd").equals(mPwd)) {
					member.setM_code(rs.getInt("m_code"));
					member.setM_id(mId);
					member.setM_pwd(mPwd);
					member.setM_phone(rs.getString("m_phone"));
					member.setM_address(rs.getString("m_address"));
					member.setM_money(rs.getInt("m_money"));
					
				} else {
					member.setM_id("0"); // pass error
				} 
				
			} else {
				member.setM_id("-1"); // 계정 없음
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		
		return member;  
	}
	
	// 잔액 확인
	public int checkMoney(String mId) {
		int money = -1;
		
		String sql = "SELECT M_MONEY FROM MEMBER WHERE M_ID = ?";
		
		conn = DBUtil.dbConnection();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, mId);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				money = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		
		return money;
	}

	// 금액 충전
	public int plusMoney(int totalMoney, String mId) {
		String sql = "UPDATE MEMBER SET M_MONEY = ? WHERE M_ID = ?";
		
		conn = DBUtil.dbConnection();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, totalMoney);
			pst.setString(2, mId);
			
			rs = pst.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		
		return totalMoney;
	}

	// 주소 확인
	public String checkAddress(String mId) {
		String address = "";
		
		String sql = "SELECT M_ADDRESS FROM MEMBER "
				       + "WHERE M_ADDRESS = (SELECT M_ADDRESS FROM MEMBER WHERE M_ID = ?)";
		
		conn = DBUtil.dbConnection();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, mId);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				address = rs.getString(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		
		return address;
	}

	// 주소 수정
	public int adUpdate(String changeAd, String mId) {
		int result = 0;
		
		String sql = "UPDATE MEMBER SET M_ADDRESS = ? WHERE M_ID = ?";
		
		conn = DBUtil.dbConnection();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, changeAd);
			pst.setString(2, mId);
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			DBUtil.dbConnection();
		}
		
		return result;
	}
	
	// 아이디 중복 체크
	public int checkId(String mId) {
		String sql = "SELECT 1 FROM MEMBER WHERE M_ID = ?";
		conn = DBUtil.dbConnection();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, mId);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				return 1; // 중복
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			DBUtil.dbConnection();
		}
		
		return 0; // 가능
	}
}