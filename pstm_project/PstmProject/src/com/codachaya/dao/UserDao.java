package com.codachaya.dao;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.codachaya.dto.LessonDto;
import com.codachaya.dto.UserDto;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UserDao extends SqlMapConfig {

	private String namespaceLesson = "lessonmapper.";
	private String namespace = "usermapper.";

	// 일반회원, 강사 로그인
	public UserDto login(String id) {

		UserDto dto = new UserDto();
		UserDto duo = new UserDto();
		duo.setId(id);

		SqlSession session = null;

		try {
			session = getSqlSessionFactory().openSession(true);
			dto = session.selectOne(namespace + "login", duo);

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

	public List<UserDto> selectPayingUserList(int trainerid) {

		SqlSession session = null;
		List<UserDto> payingUserList = new ArrayList<UserDto>();
		LessonDto trainerLessonDto = new LessonDto();
		String jsonUserdata = null;
		ArrayList<Integer> keyIntList = new ArrayList<Integer>();
		Map<String, Object> param = new HashMap<String, Object>();

		try {
			// 신청한 user을 jsonString 형태로 받아옴
			session = getSqlSessionFactory().openSession(true);

			trainerLessonDto = session.selectOne(namespaceLesson + "selectOne", trainerid);

			jsonUserdata = trainerLessonDto.getStudentid();
			System.out.println("jsonUserdata:" + jsonUserdata);
		} catch (Exception e) {
			System.out.println("ERROR");
			e.printStackTrace();
		} 

		// biz단에서 실행해야 하나 biz가 없으므로 여기에 작성
		// jsonObject 생성

		JsonElement element = JsonParser.parseString(jsonUserdata);
		JsonObject userJsonObject = element.getAsJsonObject();

		// userJsonObject에서 키값만 추출
		Iterator useridkeyiter = userJsonObject.keySet().iterator();

		while (useridkeyiter.hasNext()) {
			String key = useridkeyiter.next().toString();
			System.out.println("key:" + key);
			keyIntList.add(Integer.parseInt(key));
			System.out.println("keyIntList"+keyIntList);
		}
		System.out.println("리스트 사이즈:" + keyIntList.size());

		try {
			// Usermapper에서 해당 usernum을 가진 userdto만 꺼내온다
			// System.out.println("param1:" +param);
			param.put("keyIntList", keyIntList);
			 System.out.println("param2:" +param);
			payingUserList = session.selectList(namespace + "keylistuser", param);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			System.out.println("5. DB 종료");
		}

		return payingUserList;

	}

}
