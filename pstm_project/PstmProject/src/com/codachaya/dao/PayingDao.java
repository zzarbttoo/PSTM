package com.codachaya.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.codachaya.dto.UserDto;

public class PayingDao extends SqlMapConfig{
	
	
	private String namespaceUser = "usermapper.";
	private String namespaceReceiptinfo = "receiptinfomapper.";
	
	
	private SqlSessionFactory sqlSessionFactory;
	public SqlSessionFactory getSqlSessionFactory() {
		
		
		String resource = "com/codachaya/db/pstm-config.xml";
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSessionFactory;
		
		
	}
	
	public List<UserDto> selectTrainerList(){
		
		List<UserDto> trainerList = new ArrayList<UserDto>();
		
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession();
			trainerList = session.selectList(namespaceUser + "selectTrainerList");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			session.close();
			System.out.println(trainerList);
		}
		
		return trainerList;
		
		
	}

}
