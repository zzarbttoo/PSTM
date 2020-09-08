package com.codachaya.dto;

public class DietinfoDto {

	private int dietid;
	private int dailyinfoid;
	private String uploading;
	private String originmg;
	private String result;
	private int eatencount;
	
	public DietinfoDto() {
		
	}
	
	public DietinfoDto(int dietid, int dailyinfoid, String uploading, String originmg, String result, int eatencount) {
		this.dietid = dietid;
		this.dailyinfoid = dailyinfoid;
		this.uploading = uploading;
		this.originmg = originmg;
		this.result = result;
		this.eatencount = eatencount;
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
	public String getUploading() {
		return uploading;
	}
	public void setUploading(String uploading) {
		this.uploading = uploading;
	}
	public String getOriginmg() {
		return originmg;
	}
	public void setOriginmg(String originmg) {
		this.originmg = originmg;
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
	
	
}
