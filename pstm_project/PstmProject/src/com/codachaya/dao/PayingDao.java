package com.codachaya.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.codachaya.dto.LessonDto;
import com.codachaya.dto.ReceiptinfoDto;
import com.codachaya.dto.UserDto;
import com.codachaya.dto.ReviewDto;

public class PayingDao extends SqlMapConfig {

	private String namespaceUser = "usermapper.";
	private String namespaceReceiptinfo = "receiptinfomapper.";
	private String namespaceLesson = "lessonmapper.";
	private String nameReview = "reviewmapper.";

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

	public List<UserDto> selectTrainerList() {

		List<UserDto> trainerList = new ArrayList<UserDto>();

		SqlSession session = null;

		try {
			session = getSqlSessionFactory().openSession();
			trainerList = session.selectList(namespaceUser + "selectTrainerList");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			session.close();
			System.out.println(trainerList);
		}

		return trainerList;

	}

	public UserDto selectTrainerOne(int trainer) {

		SqlSession session = null;
		UserDto trainerDto = null;

		session = getSqlSessionFactory().openSession(true);
		trainerDto = session.selectOne(namespaceUser + "selectOne", trainer);

		session.close();

		return trainerDto;

	}

	public ReviewDto selectReviewOne(int reviewId) {
		SqlSession session = null;
		ReviewDto reviewDto = null;

		session = getSqlSessionFactory().openSession(true);
		reviewDto = session.selectOne(nameReview + "selectOne", reviewId);

		session.close();
		return reviewDto;

	}

	public UserDto selectNormalUserOne(int normalUser) {

		SqlSession session = null;
		UserDto normalUserDto = null;

		session = getSqlSessionFactory().openSession(true);
		normalUserDto = session.selectOne(namespaceUser + "selectOne", normalUser);

		session.close();

		return normalUserDto;

	}

	public List<UserDto> selectTrainerPaging(int offset, int count) {

		SqlSession session = null;

		List<UserDto> list = new ArrayList<UserDto>();
		session = getSqlSessionFactory().openSession(true);

		System.out.println("세션 확인");

		HashMap<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("offset", offset);
		parameters.put("count", count);

		try {
			list = session.selectList(namespaceUser + "selectTrainerPaging", parameters);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;

	}

	public List<UserDto> selectNormalUserPaging(int offset, int count) {

		SqlSession session = null;

		List<UserDto> list = new ArrayList<UserDto>();
		session = getSqlSessionFactory().openSession(true);

		HashMap<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("offset", offset);
		parameters.put("count", count);

		try {
			list = session.selectList(namespaceUser + "selectNormalUserPaging", parameters);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;

	}

	public int getTrainerCount() {

		SqlSession session = null;
		session = getSqlSessionFactory().openSession(true);

		int noOfRecords = 0;

		try {
			noOfRecords = session.selectOne(namespaceUser + "selectTrainerCount");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			session.close();
		}

		return noOfRecords;

	}

	public int getNormalUserCount() {

		SqlSession session = null;
		session = getSqlSessionFactory().openSession(true);

		int noOfRecords = 0;

		try {
			noOfRecords = session.selectOne(namespaceUser + "selectNormalUserCount");
			System.out.println(noOfRecords);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			session.close();
		}

		return noOfRecords;

	}

	public int insertNewReceiptinfo(ReceiptinfoDto newReceiptinfoDto) {

		SqlSession session = null;
		session = getSqlSessionFactory().openSession(true);

		int newReceiptrowcount = 0;

		try {
			newReceiptrowcount = session.insert(namespaceReceiptinfo + "insert", newReceiptinfoDto);
			System.out.println(newReceiptrowcount);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return newReceiptrowcount;

	}

	public int selectIdReceipt(String imp_uid) {

		SqlSession session = null;
		ReceiptinfoDto receiptDto = null;

		session = getSqlSessionFactory().openSession(true);
		receiptDto = session.selectOne(namespaceReceiptinfo + "selectId", imp_uid);

		session.close();

		return receiptDto.getReceiptid();
	}

	public int insertLesson(LessonDto lesson) {

		SqlSession session = null;

		session = getSqlSessionFactory().openSession(true);

		int updateColnum = 0;

		updateColnum = session.insert(namespaceLesson + "insert", lesson);

		return updateColnum;
	}

	public int updateLessonStudents(LessonDto updatelessonDto) {

		SqlSession session = null;

		session = getSqlSessionFactory().openSession(true);

		int updateColnum = 0;

		updateColnum = session.update(namespaceLesson + "updatestudent", updatelessonDto);

		return updateColnum;

	}

	// trainer번호를 넣으면 강의가 나올 수 있도록(trainer : 강의 = 1:1)
	public LessonDto selectOneLessonDto(int trainerNum) {

		SqlSession session = null;
		LessonDto lessonDto = null;

		session = getSqlSessionFactory().openSession(true);
		lessonDto = session.selectOne(namespaceLesson + "selectOne", trainerNum);

		session.close();

		return lessonDto;

	}

	public List<ReceiptinfoDto> selectReceipt(int userid) {

		SqlSession session = null;
		List<ReceiptinfoDto> list = new ArrayList<ReceiptinfoDto>();

		try {
			session = getSqlSessionFactory().openSession(true);
			list = session.selectList(namespaceReceiptinfo + "userReceiptList", userid);

		} catch (Exception e) {
			System.out.println("ERROR 3. 4.");
			e.printStackTrace();
		} finally {
			session.close();
			System.out.println("5. DB 종료");
		}

		return list;
	}

	public List<ReceiptinfoDto> selectTrainid(int userid) {

		SqlSession session = null;
		List<ReceiptinfoDto> list = new ArrayList<ReceiptinfoDto>();


		try {
			session = getSqlSessionFactory().openSession(true);
			list = session.selectList(namespaceReceiptinfo + "selectTrainid", userid);

		} catch (Exception e) {
			System.out.println("selectTrainidERROR");
			e.printStackTrace();
		} finally {
			session.close();
			System.out.println("5. DB 종료");
		}
		

		return list;
	}
	
	public List<LessonDto> selectClassContent(ArrayList<Integer> idList){
		
		SqlSession session = null;
		List<LessonDto> list = new ArrayList<LessonDto>();
		
		try {
			System.out.println("idList:"+ idList);
			session = getSqlSessionFactory().openSession(true);
			list = session.selectList(namespaceLesson + "selectClass", idList);

		} catch (Exception e) {
			System.out.println("selectClassContentERROR");
			e.printStackTrace();
		} finally {
			session.close();
			System.out.println("5. db 종료");
		}
		
		return list;
	}


}
