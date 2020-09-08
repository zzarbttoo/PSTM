package com.codachaya.dto;

import java.util.Date;

public class exerciseDto {

	private int weightid;
	private int userid;
	private Date measuredate;
	private int weight;

	public exerciseDto() {

	}

	public exerciseDto(int weightid, int userid, Date measuredate, int weight) {

		this.weightid = weightid;
		this.userid = userid;
		this.measuredate = measuredate;
		this.weight = weight;
	}

	public int getWeightid() {
		return weightid;
	}

	public void setWeightid(int weightid) {
		this.weightid = weightid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Date getMeasuredate() {
		return measuredate;
	}

	public void setMeasuredate(Date measuredate) {
		this.measuredate = measuredate;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
