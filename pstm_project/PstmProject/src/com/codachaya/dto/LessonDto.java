package com.codachaya.dto;

public class LessonDto {
	private int userid;
	private String classcontent;
	private String priceinfo;
	private String stdentid;
	
	public LessonDto() {
		
	}
	
	public LessonDto(int userid, String classcontent, String priceinfo, String stdentid) {
		this.userid = userid;
		this.classcontent = classcontent;
		this.priceinfo = priceinfo;
		this.stdentid = stdentid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getClasscontent() {
		return classcontent;
	}

	public void setClasscontent(String classcontent) {
		this.classcontent = classcontent;
	}

	public String getPriceinfo() {
		return priceinfo;
	}

	public void setPriceinfo(String priceinfo) {
		this.priceinfo = priceinfo;
	}

	public String getStdentid() {
		return stdentid;
	}

	public void setStdentid(String stdentid) {
		this.stdentid = stdentid;
	}
	
	
	
}
