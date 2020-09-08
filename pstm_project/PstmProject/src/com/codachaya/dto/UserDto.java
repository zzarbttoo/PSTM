package com.codachaya.dto;

public class UserDto {

	
	private int userid;
	private String username;
	private String password;
	private String password_key;
	private String phone;
	private String addr;
	private String detailaddr;
	private String usertype;
	private String gender;
	private int height;
	private String uploadimg;
	private String originimg;
	private String career;
	private String mycomment;
	private String admit;
	
	public UserDto() {
		
		
	}
	
	public UserDto(int userid, String username, String password, String password_key, String phone, String addr,
			String detailaddr, String usertype, String gender, int height, String uploadimg, String originimg,
			String career, String mycomment, String admit) {
		
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.password_key = password_key;
		this.phone = phone;
		this.addr = addr;
		this.detailaddr = detailaddr;
		this.usertype = usertype;
		this.gender = gender;
		this.height = height;
		this.uploadimg = uploadimg;
		this.originimg = originimg;
		this.career = career;
		this.mycomment = mycomment;
		this.admit = admit;
		
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword_key() {
		return password_key;
	}

	public void setPassword_key(String password_key) {
		this.password_key = password_key;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getDetailaddr() {
		return detailaddr;
	}

	public void setDetailaddr(String detailaddr) {
		this.detailaddr = detailaddr;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
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

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getMycomment() {
		return mycomment;
	}

	public void setMycomment(String mycomment) {
		this.mycomment = mycomment;
	}

	public String getAdmit() {
		return admit;
	}

	public void setAdmit(String admit) {
		this.admit = admit;
	}
	
	
	
	
	
	
}
