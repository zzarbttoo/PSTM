package com.codachaya.biz;

import java.util.List;

import com.codachaya.dao.ReviewDao;
import com.codachaya.dto.ReviewDto;

public class ReviewBiz {

	private ReviewDao reviewdao = new ReviewDao();

	public List<ReviewDto> selectReviewList() {

		return reviewdao.selectReviewList();
	}

	public List<ReviewDto> reviewsuch(ReviewDto dto) {

		System.out.println("biz" + dto.getReviewtitle());
		return reviewdao.reviewsuch(dto);
	}

	public ReviewDto selectOne(int reviewid) {

		return reviewdao.selectOne(reviewid);

	}

	public int insert(ReviewDto dto) {

		return reviewdao.insert(dto);

	}

	public int update(ReviewDto dto) {

		return reviewdao.update(dto);

	}
	

	public int delete(int reviewid) {

		return reviewdao.delete(reviewid);

	}
	
	public List<ReviewDto> selectReviewPaging(int offset, int count) {

		return reviewdao.selectReviewPaging(offset, count);
	}

	public int getselectReviewCount() {
		return reviewdao.getselectReviewCount();
	}


	public int getselectSearchCount(ReviewDto reviewDto) {
		System.out.println("getselectSearchCountBiz");
		return reviewdao.getselectSearchCount(reviewDto);

	}

	public List<ReviewDto> selectSearchReviewPaging(ReviewDto reviewDto, int offset, int count){

		return reviewdao.selectSearchReviewPaging(reviewDto, offset, count);

	}

}