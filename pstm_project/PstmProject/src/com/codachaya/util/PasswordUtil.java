package com.codachaya.util;

import java.util.Random;

import sun.misc.BASE64Encoder;

public class PasswordUtil {
	
	private final int KEY_LENGTH = 64;
	
	public String getRandomKey() {
		
		String result = null;
		
		Random random = new Random();
		
		byte[] value = {};
		
		BASE64Encoder encoder = new BASE64Encoder();
		
		result = encoder.encode(value);
		
		return result;
	}

}
