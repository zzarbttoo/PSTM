package com.codachaya.dto;

import java.util.Date;

public class ReviewDto {

	private int reviewid;
	private int userid;
	private String reviewtitle;
	private String reviewcontent;
	private String uploadimg;
	private String originimg;
	private Date regdate;
	private int trainnerrid;

	public ReviewDto() {
		
	}
	public ReviewDto(int reviewid, int userid, String reviewtitle, String reviewcontent, String uploadimg, String originimg, Date regdate, int trainnerrid) {
		
	}
	public int getReviewid() {
		return reviewid;
	}
	public void setReviewid(int reviewid) {
		this.reviewid = reviewid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getReviewtitle() {
		return reviewtitle;
	}
	public void setReviewtitle(String revewtitle) {
		this.reviewtitle = revewtitle;
	}
	public String getReviewcontent() {
		return reviewcontent;
	}
	public void setReviewcontent(String reviewcontent) {
		this.reviewcontent = reviewcontent;
	}
	public String getUploadimg() {
		return uploadimg;
	}
	public void setUploadimg(String uploadimg) {
		this.uploadimg = uploadimg;
	}
	public String getOriginimg() {
		return originimg;
	}
	public void setOriginimg(String originimg) {
		this.originimg = originimg;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getTrainnerrid() {
		return trainnerrid;
	}
	public void setTrainnerrid(int trainnerrid) {
		this.trainnerrid = trainnerrid;
	}
	
}
