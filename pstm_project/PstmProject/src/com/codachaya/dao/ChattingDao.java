package com.codachaya.dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.codachaya.dto.UserDto;

public class ChattingDao {
	
	private String namespaceUser = "usermapper.";
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

	public UserDto selectTrainerOne(int trainer) {

		SqlSession session = null;
		UserDto trainerDto = null;

		session = getSqlSessionFactory().openSession(true);
		trainerDto = session.selectOne(namespaceUser + "selectOne", trainer);

		session.close();

		return trainerDto;

	}

}
