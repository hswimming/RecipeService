package com.shinhan.buy;

import java.util.List;

public class BuyService {
	
	BuyDao buyDao = new BuyDao();

	// 레시피 구매
	public int buyFood(int food, String mId) {
		return buyDao.buyFood(food, mId);
	}

	// 구매내역 조회
	public List<BuyDTO> selectAll(String mId) {
		return buyDao.selectAll(mId);
	}

	// 구매내역 삭제
	public int deleteFood(int food, String mId) {
		return buyDao.deleteFood(food, mId);
	}
	
	// 특정 번호의 구매내역 조회
		public List<BuyDTO> selectBno(int bNo) {
			return buyDao.selectBno(bNo);
		}
}