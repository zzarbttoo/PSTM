package com.codachaya.dto;

import java.util.Date;

public class DailyinfoDto {

	private int dailyinfoid;
	private int userid;
	private Date regdate;
	private String exercise;
	private int consumekcal;
	private String feedback;

	public DailyinfoDto() {

	}

	public DailyinfoDto(int dailyinfoid, int userid, Date regdate, String exercise, int consumekcal, String feedback) {
		this.dailyinfoid = dailyinfoid;
		this.userid = userid;
		this.regdate = regdate;
		this.exercise = exercise;
		this.consumekcal = consumekcal;
		this.feedback = feedback;
	}

	public int getDailyinfoid() {
		return dailyinfoid;
	}

	public void setDailyinfoid(int dailyinfoid) {
		this.dailyinfoid = dailyinfoid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getExercise() {
		return exercise;
	}

	public void setExercise(String exercise) {
		this.exercise = exercise;
	}

	public int getConsumekcal() {
		return consumekcal;
	}

	public void setConsumekcal(int consumekcal) {
		this.consumekcal = consumekcal;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	

}
