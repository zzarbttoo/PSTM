package com.codachaya.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.codachaya.dto.NctinfoDto;

public class NctinfoDao extends SqlMapConfig{

	private String namespace = "nctinfomapper.";
	
	public List<NctinfoDto> selectList(int dietid){
		SqlSession session = null;
		
		List<NctinfoDto> list = new ArrayList<NctinfoDto>();
		session = getSqlSessionFactory().openSession(true);
		list = session.selectList(namespace + "selectList",dietid);
		
		session.close();
		
		return list;
	}
	
	public int insert(NctinfoDto dto) {
		SqlSession session = null;
		int res = 0;
		
		session = getSqlSessionFactory().openSession(true);
		res = session.insert(namespace + "insert",dto);
		session.close();
		
		return res;
	}
	
}
