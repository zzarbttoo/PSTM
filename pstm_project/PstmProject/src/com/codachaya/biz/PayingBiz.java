package com.codachaya.biz;

import java.util.List;

import com.codachaya.dao.PayingDao;
import com.codachaya.dto.UserDto;

public class PayingBiz {
	
	
	private PayingDao payingdao = new PayingDao();
	
	public List<UserDto> selectTrainerList(){
		
		System.out.println("DEBUG");
		
		return payingdao.selectTrainerList();
		
	}
	
	public UserDto selectTrainerOne(int trainerUserId) {
		
		
		return payingdao.selectTrainerOne(trainerUserId);
		
	}
	
}
