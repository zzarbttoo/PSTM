package com.codachaya.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import org.apache.ibatis.session.SqlSession;

import com.codachaya.dto.ReviewDto;

public class ReviewDao extends SqlMapConfig {

	private String namespace="review-mapper.";
	
	public List<ReviewDto>selectList(){
		SqlSession session =null;
		List<ReviewDto>list=new ArrayList<ReviewDto>();
		session=getSqlSessionFactory().openSession(true);
		list=session.selectList(namespace+"selectList");
		session.close();
		
		return list;
	}
	public ReviewDto selectOne(int reviewid) {
		SqlSession session =null;
		
		ReviewDto dto=null;
		session=getSqlSessionFactory().openSession(true);
		dto=session.selectOne(namespace+"selectOne",reviewid);
		
		session.close();
		
		
		return dto;
	}
	public int insert(ReviewDto dto) {
		SqlSession session=null;
		int res=0;
		session =getSqlSessionFactory().openSession(true);
		res=session.insert(namespace+"insert",dto);
		session.close();
		
		return res;		
	}
	public int update(ReviewDto dto) {
		SqlSession session=null;
		int res=0;
		session=getSqlSessionFactory().openSession(true);
		res=session.update(namespace+"update",dto);
		session.close();
	
	return 0;
	}
	public int delete(int reviewid) {
		SqlSession session=null;
		int res=0;
		session=getSqlSessionFactory().openSession(true);
		reviewid=session.delete(namespace+"update",reviewid);
		session.close();
		
		return res;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
