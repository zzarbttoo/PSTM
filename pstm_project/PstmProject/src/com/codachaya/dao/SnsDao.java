package com.codachaya.dao;

import org.apache.ibatis.session.SqlSession;

import com.codachaya.dto.NaverDto;

public class SnsDao extends SqlMapConfig {

	private String namespace = "snsmapper.";

	public int insert(NaverDto dto) {

		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert(namespace + "insert", dto);

			if (res > 0) {
				session.commit();
			}

		} catch (Exception e) {
			System.out.println("ERROR 3. 4.");
			e.printStackTrace();
		} finally {
			session.close();
		}

		return res;
	}

	public NaverDto naverLogin(String id) {
		SqlSession session = null;
		NaverDto dto = new NaverDto();

		try {
			session = getSqlSessionFactory().openSession(true);
			dto = session.selectOne(namespace + "naverLogin", id);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return dto;
	}

}
