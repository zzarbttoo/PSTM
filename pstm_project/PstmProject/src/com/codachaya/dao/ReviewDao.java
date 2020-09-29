package com.codachaya.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.codachaya.dto.ReviewDto;

import sun.reflect.generics.visitor.Reifier;

public class ReviewDao extends SqlMapConfig {

	private String nameReview = "reviewmapper.";

	// 글 목록 가져오기(검색)
	public List<ReviewDto> reviewsuch(ReviewDto dto) {

//		ArrayList<ReviewDto>list=new ArrayList<ReviewDto>();
//		String opt=(String)listOpt.get("opt");//검색옵션(제목, 내용 글쓴이 등..)
//		String Condition=(String)listOpt.get("condition");//검색 내용
//		int start=(Integer)listOpt.get("start");//현재 페이지 번호
//		return null;

		List<ReviewDto> reviewList = new ArrayList<ReviewDto>();

		SqlSession session = null;
		System.out.println("dao21:" + dto.getReviewtitle());

		try {
			session = getSqlSessionFactory().openSession();

			reviewList = session.selectList(nameReview + "reviewsuch", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return reviewList;
	}

	public List<ReviewDto> selectReviewList() {
		List<ReviewDto> reviewList = new ArrayList<ReviewDto>();
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession();
			reviewList = session.selectList(nameReview + "selectReviewList");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR 3. 4.");
			e.printStackTrace();
		} finally {
			session.close();
			System.out.println(reviewList);
		}
		return reviewList;
	}

	public ReviewDto selectOne(int reviewid) {
		SqlSession session = null;

		ReviewDto dto = null;
		session = getSqlSessionFactory().openSession(true);
		dto = session.selectOne(nameReview + "selectOne", reviewid);

		session.close();

		return dto;
	}

	public int insert(ReviewDto dto) {
		SqlSession session = null;
		int res = 0;
		session = getSqlSessionFactory().openSession(true);
		res = session.insert(nameReview + "insert", dto);
		session.close();

		return res;
	}

	public int update(ReviewDto dto) {
		SqlSession session = null;
		int res = 0;
		session = getSqlSessionFactory().openSession(true);
		res = session.update(nameReview + "update", dto);
		session.close();

		return res;
	}

	public int delete(int id) {
		SqlSession session = null;
		int res = 0;
		session = getSqlSessionFactory().openSession(true);
		res = session.delete(nameReview + "delete", id);
		session.close();

		return res;
	}

	public List<ReviewDto> selectReviewPaging(int offset, int count) {

		SqlSession session = null;
		List<ReviewDto> list = new ArrayList<ReviewDto>();
		session = getSqlSessionFactory().openSession(true);

		System.out.println("reviewDao.selectReviewPaging세션확인");

		HashMap<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("offset", offset);
		parameters.put("count", count);

		try {
			list = session.selectList(nameReview + "selectReviewPaging", parameters);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}

	public int getselectReviewCount() {
		SqlSession session = null;
		session = getSqlSessionFactory().openSession(true);

		int noOfRecords = 0;

		try {
			noOfRecords = session.selectOne(nameReview + "selectReviewCount");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			session.close();
		}
		return noOfRecords;
	}

	public int getselectSearchCount(ReviewDto reviewDto) {
		SqlSession session = null;
		session = getSqlSessionFactory().openSession(true);

		int noOfRecords = 0;

		try {
			noOfRecords = session.selectOne(nameReview + "selectSearchCount", reviewDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			session.close();
		}
		return noOfRecords;

	}

	public List<ReviewDto> selectSearchReviewPaging(ReviewDto reviewDto, int offset, int count) {

		SqlSession session = null;
		List<ReviewDto> list = new ArrayList<ReviewDto>();
		session = getSqlSessionFactory().openSession(true);


		HashMap<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("offset", offset);
		parameters.put("count", count);
		parameters.put("reviewTitle", reviewDto.getReviewtitle());
		parameters.put("trainerName", reviewDto.getTrainer());
		
		try {
			list = session.selectList(nameReview + "selectSearchReviewPaging", parameters);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		System.out.println("list"+ list);

		return list;

	}

}