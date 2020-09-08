package com.codachaya.dto;

public class NctinfoDto {

	private int nctunfoid;
	private int dietid;
	private int kcal;
	private int carbohydrate;
	private int protein;
	private int fat;
	private String nctetc;

	public NctinfoDto() {

	}

	public NctinfoDto(int nctunfoid, int dietid, int kcal, int carbohydrate, int protein, int fat, String nctetc) {
		this.nctunfoid = nctunfoid;
		this.dietid = dietid;
		this.kcal = kcal;
		this.carbohydrate = carbohydrate;
		this.protein = protein;
		this.fat = fat;
		this.nctetc = nctetc;
	}

	public int getNctunfoid() {
		return nctunfoid;
	}

	public void setNctunfoid(int nctunfoid) {
		this.nctunfoid = nctunfoid;
	}

	public int getDietid() {
		return dietid;
	}

	public void setDietid(int dietid) {
		this.dietid = dietid;
	}

	public int getKcal() {
		return kcal;
	}

	public void setKcal(int kcal) {
		this.kcal = kcal;
	}

	public int getCarbohydrate() {
		return carbohydrate;
	}

	public void setCarbohydrate(int carbohydrate) {
		this.carbohydrate = carbohydrate;
	}

	public int getProtein() {
		return protein;
	}

	public void setProtein(int protein) {
		this.protein = protein;
	}

	public int getFat() {
		return fat;
	}

	public void setFat(int fat) {
		this.fat = fat;
	}

	public String getNctetc() {
		return nctetc;
	}

	public void setNctetc(String nctetc) {
		this.nctetc = nctetc;
	}

}
