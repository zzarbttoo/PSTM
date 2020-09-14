package com.codachaya.dao;

import org.apache.ibatis.session.SqlSession;

import com.codachaya.dto.UserDto;

public class UserDao extends SqlMapConfig {

	private String namespace = "usermapper.";

	// 일반회원, 강사 로그인
	public UserDto login(String id) {

		UserDto dto = new UserDto();
		SqlSession session = null;

		try {
			session = getSqlSessionFactory().openSession(true);
			dto = session.selectOne(namespace + "login", id);

		} catch (Exception e) {
			System.out.println("ERROR 3. 4.");
			e.printStackTrace();
		} finally {
			session.close();
			System.out.println("5. DB 종료");
		}

		return dto;
	}

	// 일반회원 회원가입
	public int insertNormalUser(UserDto dto) {

		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert(namespace + "insertNormalUser", dto);

			if (res > 0) {
				session.commit();
			}

		} catch (Exception e) {
			System.out.println("ERROR 3. 4.");
			e.printStackTrace();
		} finally {
			session.close();
			System.out.println("5. DB 종료");
		}

		return res;
	}

	// 트레이너 회원가입
	public int insertTrainer(UserDto dto) {

		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert(namespace + "insertTrainer", dto);

			if (res > 0) {
				session.commit();
			}

		} catch (Exception e) {
			System.out.println("ERROR 3. 4.");
			e.printStackTrace();
		} finally {
			session.close();
			System.out.println("5. DB 종료");
		}

		return res;
	}

	// 유효성 검사 ID 중복 확인
	public UserDto idCheck(String id) {

		SqlSession session = null;
		UserDto dto = new UserDto();

		try {
			session = getSqlSessionFactory().openSession(true);
			dto = session.selectOne(namespace + "idCheck", id);
		} catch (Exception e) {
			System.out.println("ERROR 3.4");
			e.printStackTrace();
		} finally {
			session.close();
			System.out.println("5. DB 종료");
		}

		return dto;
	}

}
