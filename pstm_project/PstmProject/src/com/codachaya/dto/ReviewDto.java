package com.codachaya.dto;

import java.util.Date;
import java.util.List;

public class ReviewDto {

	private int reviewid;
	private int userid;
	private String reviewtitle;
	private String reviewcontent;
	private String uploadimg;
	private String reviewstar;
	private Date regdate;
	private int trainerid;
	private String trainer;

	public ReviewDto() {
		
	}
	public ReviewDto(int reviewid, int userid, String reviewtitle, String reviewcontent, String uploadimg,String reviewstar, Date regdate, int trainerid, String trainer) {
		this.reviewid=reviewid;
		this.userid=userid;
		this.reviewtitle=reviewtitle;
		this.reviewcontent=reviewcontent;
		this.uploadimg=uploadimg;
		this.reviewstar=reviewstar;
		this.regdate=regdate;
		this.trainerid=trainerid;
		this.trainer=trainer;
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
	public void setReviewtitle(String reviewtitle) {
		this.reviewtitle = reviewtitle;
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
	public String getReviewstar() {
		return reviewstar;
	}
	public void setReviewstar(String reviewstar) {
		this.reviewstar=reviewstar;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getTrainerid() {
		return trainerid;
	}
	public void setTrainerid(int trainerid) {
		this.trainerid = trainerid;
	}
	public String getTrainer() {
		return trainer;
	}
	public void setTrainer(String trainer) {
		this.trainer=trainer;
	}
}
