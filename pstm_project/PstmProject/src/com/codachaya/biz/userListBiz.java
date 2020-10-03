package com.codachaya.biz;


import java.util.ArrayList;
import java.util.List;

import com.codachaya.dao.PayingDao;
import com.codachaya.dto.LessonDto;
import com.codachaya.dto.ReceiptinfoDto;

public class userListBiz {

	PayingDao pao = new PayingDao();

	public List<ReceiptinfoDto> selectTrainid(int userid) {

		return pao.selectTrainid(userid);
	}
	
	public List<LessonDto> selectClassContent(ArrayList<Integer> idList){
		
		return pao.selectClassContent(idList);
	}


	

}
