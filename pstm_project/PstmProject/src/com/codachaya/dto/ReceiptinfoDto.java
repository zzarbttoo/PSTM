package com.codachaya.dto;

import java.util.Date;

public class ReceiptinfoDto {

	private int receiptid;
	private int userid;
	private int trainerid;
	private Date buydate;
	private Date startdate;
	private int duration;
	private String purchasetype;
	private int price;
	
	public ReceiptinfoDto() {
		
		
	}
	
	public ReceiptinfoDto(int receiptid, int userid, int trainerid, Date buydate, Date startdate, int duration,
			String purchasetype, int price) {
		this.receiptid = receiptid;
		this.userid = userid;
		this.trainerid = trainerid;
		this.buydate = buydate;
		this.startdate = startdate;
		this.duration = duration;
		this.purchasetype = purchasetype;
		this.price = price;
	}

	public int getReceiptid() {
		return receiptid;
	}

	public void setReceiptid(int receiptid) {
		this.receiptid = receiptid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getTrainerid() {
		return trainerid;
	}

	public void setTrainerid(int trainerid) {
		this.trainerid = trainerid;
	}

	public Date getBuydate() {
		return buydate;
	}

	public void setBuydate(Date buydate) {
		this.buydate = buydate;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getPurchasetype() {
		return purchasetype;
	}

	public void setPurchasetype(String purchasetype) {
		this.purchasetype = purchasetype;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	
	
	
	
	
	
	
	
}
