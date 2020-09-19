package com.codachaya.controller;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;
import java.util.List;


public class Util {

	private String toDates;

	public String getToDates() {
		return toDates;
	}

	public void setToDates(String mdate) {
		// mdate의 형식을 yyyy-MM-dd hh:mm:00 변환
		// Timestamp 를 쓰려면 밑에처럼 만들어 줘야댐
		String m = mdate.substring(0, 4) + "-" + mdate.substring(4, 6) + "-" + mdate.substring(6, 8) + " "
				+ mdate.substring(8, 10) + ":" + mdate.substring(10) + ":00";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일 HH시mm분"); // 날짜를 원하는 포맷으로 파싱 y,M,d,H,m 는 사용되는 패턴
		Timestamp tm = Timestamp.valueOf(m); // 현재 시간 구함 SQL TIMESTAMP 값으로 식별 할 수있는 날짜 valueOf : Number객체로 변환 데이터베이스
		toDates = sdf.format(tm); // 둘이 합침
	}

	public static String isTwo(String msg) {

		return (msg.length() < 2) ? "0" + msg : msg;
	}

	public static String fontColor(int date, int dayOfWeek) {
		String color = "";

		if ((dayOfWeek - 1 + date) % 7 == 0) {
			color = "blue";

		} else if ((dayOfWeek - 1 + date) % 7 == 1) {
			color = "red";
		} else {
			color = "black";
		}

		return color;
	}
	
	
}
