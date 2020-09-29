package com.codachaya.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.codachaya.dto.DailyinfoDto;

public class DailyinfoDao extends SqlMapConfig{

	private String namespace = "dailyinfomapper.";
	
	public List<DailyinfoDto> selectList(){
		SqlSession session = null;
		
		List<DailyinfoDto> list = new ArrayList<DailyinfoDto>();
		session = getSqlSessionFactory().openSession(true);
		list = session.selectList(namespace + "selectList");
		
		session.close();
		
		return list;
		
	}
	
	public int getLastDailyInfoId() {
		int result = 0;
		
		SqlSession session = null;
		
		session = getSqlSessionFactory().openSession(true);
		result = session.selectOne(namespace + "getLastDailyInfoId");
		session.close();
		
		return result;
	}
	
	public int insert(DailyinfoDto dto) {
		SqlSession session = null;
		int res = 0;
		
		session = getSqlSessionFactory().openSession(true);
		res = session.insert(namespace + "insert", dto);
		session.close();
		
		return res;
	}
	public int delete(int dailyinfoid) {
		SqlSession session = null;
		int res = 0;
		
		session = getSqlSessionFactory().openSession(true);
		res = session.delete(namespace + "delete",dailyinfoid);
		session.close();
		
		return res;
	}
	public int feedbackinsert(DailyinfoDto dto) {
		SqlSession session = null;
		int res = 0;
		
		session = getSqlSessionFactory().openSession(true);
		res = session.insert(namespace + "insert", dto);
		session.close();
		
		return res;
	}
	
}