package com.codachaya.dto;

import java.util.Date;

public class ReceiptinfoDto {

	private int receiptid;
	private String imp_uid;
	private int userid;
	private int trainerid;
	private Date buydate;
	private Date startdate;
	private int duration;
	private String purchasetype;
	private String checkright;
	private String refund;
	private int price;

	public ReceiptinfoDto() {

	}

	public ReceiptinfoDto(int receiptid, String imp_uid, int userid, int trainerid, Date buydate, Date startdate,
			int duration, String purchasetype, String checkright, String refund, int price) {

		this.receiptid = receiptid;
		this.imp_uid = imp_uid;
		this.userid = userid;
		this.trainerid = trainerid;
		this.buydate = buydate;
		this.startdate = startdate;
		this.duration = duration;
		this.purchasetype = purchasetype;
		this.checkright = checkright;
		this.refund = refund;
		this.price = price;

	}

	public ReceiptinfoDto(String imp_uid, int userid, int trainerid, Date startdate, int duration, String purchasetype,
			String isRight, int price) {

		this.imp_uid = imp_uid;
		this.userid = userid;
		this.trainerid = trainerid;
		this.startdate = startdate;
		this.duration = duration;
		this.purchasetype = purchasetype;
		this.checkright = isRight;
		this.price = price;

	}

	public int getReceiptid() {
		return receiptid;
	}

	public void setReceiptid(int receiptid) {
		this.receiptid = receiptid;
	}

	public String getImp_uid() {
		return imp_uid;

	}

	public void setImp_uid(String imp_uid) {
		this.imp_uid = imp_uid;
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

	public String getCheckright() {
		return checkright;
	}

	public void setCheckright(String checkright) {
		this.checkright = checkright;
	}

	public String getRefund() {
		return refund;
	}

	public void setRefund(String refund) {
		this.refund = refund;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
