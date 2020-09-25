package com.codachaya.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
<<<<<<< HEAD
import com.codachaya.dto.DietinfoDto;
import com.codachaya.dto.NctinfoDto;
=======

import com.codachaya.dto.DietinfoDto;
>>>>>>> 8bde238fb9daed1806e948a0bcc3fa737d90f5b1
import com.codachaya.dto.UserDto;

public class DietinfoDao extends SqlMapConfig{

	private String namespace = "dietinfomapper.";
	
	public List<DietinfoDto> selectList(int dailyinfoid){
		SqlSession session = null;
		
		List<DietinfoDto> list = new ArrayList<DietinfoDto>();
		session = getSqlSessionFactory().openSession(true);
		list = session.selectList(namespace + "selectList",dailyinfoid);
		
		session.close();
		
		return list;
		
	}
	
	
	public int insert(DietinfoDto dto) {
		SqlSession session = null;
		int res = 0;
		
		session = getSqlSessionFactory().openSession(true);
		res = session.insert(namespace + "insert", dto);
		session.close();
		
		
		return res;
	}
	
<<<<<<< HEAD
	public int delete(int dailyinfoid) {
		SqlSession session = null;
		
		int res = 0;
		session = getSqlSessionFactory().openSession(true);
		res = session.delete(namespace + "delete", dailyinfoid);
		session.close();
		
		return res;
	}
=======
>>>>>>> 8bde238fb9daed1806e948a0bcc3fa737d90f5b1
}

