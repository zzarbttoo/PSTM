package com.codachaya.dto;

public class CertificationDto {
	private int userid;
	private String certifiurlmod;
	private	String certifiurl;
	
	public CertificationDto() {
		
	}
	public CertificationDto(int userid, String certifiurlmod, String certifiurl) {
		this.userid = userid;
		this.certifiurlmod = certifiurlmod;
		this.certifiurl = certifiurl;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getCertifiurlmod() {
		return certifiurlmod;
	}
	public void setCertifiurlmod(String certifiurlmod) {
		this.certifiurlmod = certifiurlmod;
	}
	public String getCertifiurl() {
		return certifiurl;
	}
	public void setCertifiurl(String certifiurl) {
		this.certifiurl = certifiurl;
	}
	
	
	
}
