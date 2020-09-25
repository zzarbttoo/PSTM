package com.codachaya.dto;

public class DietinfoDto {

	private int dietid;
	private int dailyinfoid;
	private String uploadimg;
	private String originimg;
	private String result;
	private int eatencount;
	private String timeeat;
	private int kcal;

	public DietinfoDto() {

	}

	public DietinfoDto(int dietid, int dailyinfoid, String uploadimg, String originimg, String result, int eatencount, String timeeat, int kcal) {
		this.dietid = dietid;
		this.dailyinfoid = dailyinfoid;
		this.uploadimg = uploadimg;
		this.originimg = originimg;
		this.result = result;
		this.eatencount = eatencount;
		this.timeeat = timeeat;
		this.kcal = kcal;
	}

	public int getDietid() {
		return dietid;
	}

	public void setDietid(int dietid) {
		this.dietid = dietid;
	}

	public int getDailyinfoid() {
		return dailyinfoid;
	}

	public void setDailyinfoid(int dailyinfoid) {
		this.dailyinfoid = dailyinfoid;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getEatencount() {
		return eatencount;
	}

	public void setEatencount(int eatencount) {
		this.eatencount = eatencount;
	}

	public String getTimeeat() {
		return timeeat;
	}

	public void setTimeeat(String timeeat) {
		this.timeeat = timeeat;
	}

	public int getKcal() {
		return kcal;
	}

	public void setKcal(int kcal) {
		this.kcal = kcal;
	}
	
	

}
