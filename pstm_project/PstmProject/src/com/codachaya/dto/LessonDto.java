package com.codachaya.dto;

public class LessonDto {
	private int userid;
	private String classcontent;
	private String priceinfo;
	private String studentid;
	private int studentcount;
	
	public LessonDto() {
		
	}
	
	public LessonDto(int userid, String classcontent, String priceinfo, String stdentid, int studentcount) {
		this.userid = userid;
		this.classcontent = classcontent;
		this.priceinfo = priceinfo;
		this.studentid = stdentid;
		this.studentcount = studentcount;
	}
	
	public LessonDto(int userid, String studentid, int studentcount) {
		this.userid = userid;
		this.studentid = studentid;
		this.studentcount = studentcount;
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

	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String stdentid) {
		this.studentid = stdentid;
	}

	public int getStudentcount() {
		return studentcount;
	}

	public void setStudentcount(int studentcount) {
		this.studentcount = studentcount;
	}
	
	  
	
}
