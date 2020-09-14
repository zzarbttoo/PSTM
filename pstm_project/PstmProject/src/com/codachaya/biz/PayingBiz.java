package com.codachaya.biz;

import java.util.List;


import com.codachaya.dao.PayingDao;
import com.codachaya.dto.UserDto;

public class PayingBiz {

	private PayingDao payingdao = new PayingDao();

	public List<UserDto> selectTrainerList() {

		System.out.println("DEBUG");

		return payingdao.selectTrainerList();

	}

	public UserDto selectTrainerOne(int trainerUserId) {

		return payingdao.selectTrainerOne(trainerUserId);

	}
	
	public UserDto selectNormalUserOne(int normalUser) {
		
		return payingdao.selectNormalUserOne(normalUser);
		
	}

	public List<UserDto> selectTrainerPaging(int offset, int count) {

		System.out.println("biz" + offset + "," + count);
		return payingdao.selectTrainerPaging(offset, count);

	}

	public List<UserDto> selectNormalUserPaging(int offset, int count) {

		return payingdao.selectNormalUserPaging(offset, count);
	}

	public int getTrainerCount() {

		return payingdao.getTrainerCount();

	}

	public int getNormalUserCount() {

		return payingdao.getNormalUserCount();

	}

}
