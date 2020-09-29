package com.codachaya.dto;

public class NaverDto {

	private int userid;
	private String id;
	private String name;
	private String usertype;
	private String gender;
	private int height;
	private String profile_image;
	private String admit;
	private String signout;

	public NaverDto() {

	}

	public NaverDto(int userid, String id, String name, String usertype, String gender, int height,
			String profile_image, String admit, String signout) {
		this.userid = userid;
		this.id = id;
		this.name = name;
		this.usertype = usertype;
		this.gender = gender;
		this.height = height;
		this.profile_image = profile_image;
		this.admit = admit;
		this.signout = signout;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	public String getAdmit() {
		return admit;
	}

	public void setAdmit(String admit) {
		this.admit = admit;
	}

	public String getSignout() {
		return signout;
	}

	public void setSignout(String signout) {
		this.signout = signout;
	}
	
	
	
	

}
