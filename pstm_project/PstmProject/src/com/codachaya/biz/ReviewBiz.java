package com.codachaya.biz;

import java.util.List;

import com.codachaya.dao.ReviewDao;
import com.codachaya.dto.ReviewDto;
import com.codachaya.dto.UserDto;

public class ReviewBiz {
	
	private ReviewDao reviewdao=new ReviewDao();
	
	public List<ReviewDto>selectReviewList(){
		
		System.out.println("ReviewBiz selectList");
		
		return reviewdao.selectReviewList();
	}
	
	public List<ReviewDto>reviewsuch(ReviewDto dto){
		
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
	public List<ReviewDto>selectReviewPaging(int offset,int count){
		System.out.println("biz"+offset+","+count);
		return reviewdao.selectReviewPaging(offset, count);
	}
	
	public int getselectReviewCount() {
		return reviewdao.getselectReviewCount();
	}
	public int delete(int reviewid) {
		System.out.println();
		return reviewdao.delete(reviewid);
		
	}


	public UserDto selectNormalUserOne(int normalUserId) {
		// TODO Auto-generated method stub
		return reviewdao.selectReveiwOne(normalUserId);
	}
	public List<ReviewDto>selectReveiwPaging(int offset, int count){
		
		System.out.println("biz"+offset+","+count);
		return reviewdao.selectReviewPaging(offset,count);
		
	}



}
