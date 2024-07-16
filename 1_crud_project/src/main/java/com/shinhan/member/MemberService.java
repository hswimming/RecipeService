package com.shinhan.member;

import java.util.List;

public class MemberService {
	MemberDAO memberDAO = new MemberDAO();

	// 회원가입
	public int memberInsert(MemberDTO memberDTO) {
		return memberDAO.memberInsert(memberDTO);
	}
	
	// 로그인
	public MemberDTO login(String mId, String mPwd) {
		return memberDAO.login(mId, mPwd);
	}

	// 잔액 확인
	public int checkMoney(String mId) {
		return memberDAO.checkMoney(mId);
	}

	// 금액 충전
	public int plusMoney(int totalMoney, String mId) {
		return memberDAO.plusMoney(totalMoney, mId);
	}

	// 배송지 확인
	public String checkAddress(String mId) {
		return memberDAO.checkAddress(mId);
	}

	// 배송지 수정
	public int adUpdate(String changeAd, String mId) {
		return memberDAO.adUpdate(changeAd, mId);
	}
	
	// 아이디 중복 체크
	public int checkId(String mId) {
		return memberDAO.checkId(mId);
	}
}