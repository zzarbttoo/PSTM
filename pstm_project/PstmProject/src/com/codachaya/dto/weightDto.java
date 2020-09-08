package com.codachaya.dto;

public class weightDto {

	private int id;
	private int dailyinfoid;
	private String content;
	private int kcal;

	public weightDto() {

	}

	public weightDto(int id, int dailyinfoid, String content, int kcal) {

		this.id = id;
		this.dailyinfoid = dailyinfoid;
		this.content = content;
		this.kcal = kcal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDailyinfoid() {
		return dailyinfoid;
	}

	public void setDailyinfoid(int dailyinfoid) {
		this.dailyinfoid = dailyinfoid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getKcal() {
		return kcal;
	}

	public void setKcal(int kcal) {
		this.kcal = kcal;
	}

}
